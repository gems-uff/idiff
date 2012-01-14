package diretorioDiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import algorithms.LineGrain;

/**
 * Guarda a representa��o do arquivo que deve ser comparado.
 * 
 * @author Eraldo
 * 
 */
public class Arquivo {

    /**
     * Arquivo representado.
     */
    private File arquivo = null;
    /**
     * C�digo hash do conte�do do arquivo.
     */
    private String hash = "";
    /**
     * Linhas do arquivo
     */
    private List<Linha> linhas =  new ArrayList<Linha>();
    /**
     * Id como refer�ncia ao diret�rio base de compara��o
     */
    private int id;
    /**
     * Armazena se j� foi localizado match do arquivo
     */
    private boolean match;
    /**
     * Path diret�rio base compara��o
     */
    private String pathBaseComparacao;
    private String contentType;

    /**
     * Getter para o arquivo.
     * 
     * @return retorna o arquivo
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * Getter para o hash do conte�do do arquivo
     * 
     * @return
     */
    public String getHash() {
        return hash;
    }

    /**
     * Construtor padr�o
     * 
     * @param arquivo
     *            Arquivo que deve ser representado.
     * @param id
     * 
     * @param pathBaseComparacao
     */
    public Arquivo(File arquivo, int id, String pathBaseComparacao) {
        this.arquivo = arquivo;
        this.id = id;
        this.pathBaseComparacao = pathBaseComparacao;
        gerarHash();


        contentType = URLConnection.getFileNameMap().getContentTypeFor(getArquivo().getAbsolutePath());
        if (contentType == null) {
            contentType = new MimetypesFileTypeMap().getContentType(getArquivo());
        }

        //carregarLinhas();
    }

    /**
     * 
     * @return
     */
    public boolean isText() {
        if (contentType.startsWith("text")) {
            return true;
        }

        if (contentType.equalsIgnoreCase("application/octet-stream")) {
            String lowerCase = getArquivo().getName().toLowerCase();
            if (lowerCase.endsWith(".java")) {
                return true;
            }

            if (lowerCase.endsWith(".form")) {
                return true;
            }

            if (lowerCase.endsWith(".properties")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Carrega as linhas contidas no arquivo.
     */
    private void carregarLinhas() {
        if (isArquivo() || isText()) {
            linhas = new ArrayList<Linha>();
            String line = null;
            BufferedReader reader = null;
            try {
                int idStart = 0;
                reader = new BufferedReader(new FileReader(getArquivo()));
                while ((line = reader.readLine()) != null) {
                    linhas.add(new Linha(line, linhas.size() + 1, idStart));
                    idStart = idStart + line.length() + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Gera um c�digo hash MD5 com o conte�do do arquivo representado.
     * 
     * @return C�digo hash MD5
     */
    private void gerarHash() {
        if (isArquivo()) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                InputStream is = new FileInputStream(getArquivo());
                byte[] buffer = new byte[8192];
                String output = "";
                int read = 0;
                try {
                    while ((read = is.read(buffer)) > 0) {
                        digest.update(buffer, 0, read);
                    }
                    byte[] md5sum = digest.digest();
                    BigInteger bigInt = new BigInteger(1, md5sum);
                    output = bigInt.toString(16);
                } catch (IOException e) {
                    throw new RuntimeException(
                            "N�o foi possivel processar o arquivo.", e);
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        throw new RuntimeException(
                                "N�o foi possivel fechar o arquivo", e);
                    }
                }
                hash = output;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Algoritmo MD5 n�o foi encontrado.", e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("N�o foi possivel carregar o arquivo.",
                        e);
            }
        }
    }

    boolean isArquivo() {
        return getArquivo().isFile();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Arquivo)) {
            return false;
        }

        Arquivo other = (Arquivo) obj;

        if (!isArquivo()) {
            if (other.isArquivo()) {
                return false;
            }

            return other.getPathRelativo().equals(getPathRelativo());
        }

        if (hash == null) {
            if (other.hash != null) {
                return false;
            }
        } else if (!hash.equals(other.hash)) {
            return false;
        }

        return true;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    /**
     * Seta se o arquivo esta com match localizado.
     * @param match true | false
     */
    public void setMatch(boolean match) {
        this.match = match;
    }

    /**
     * @return the match
     */
    public boolean isMatch() {
        return match;
    }

    /**
     * Retorna o path relativo ao diret�rio base de compara��o.
     * 
     * @return String com o path.
     */
    public String getPathRelativo() {
        return arquivo.getAbsolutePath().replace(pathBaseComparacao, "");
    }

    
    int tamanhoA = -1;
    /**
     * Calcula o tamanho das linhas sem match, desconsiderando os espa�os em branco.
     * 
     * @return tamanho as linhas
     */
    public int getTamanhoAtual() {
 
        if (tamanhoA < 0) {
            int tamanho = 0;
            carregarLinhas();
            for (LineGrain linha : getLinhasSemMatch()) {
                String line = linha.getGrain();

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        tamanho++;
                    }
                }
            }
            linhas = new ArrayList<Linha>();        
            tamanhoA = tamanho;
        }
        
        return tamanhoA;
//        return tamanho;
    }

    /**
     * Retorna as linhas sem match
     * @return Uma lista com as linhas sem match
     */
    private List<Linha> getLinhasSemMatch() {
        List<Linha> linhasSemMatch = new ArrayList<Linha>();
        for (Linha linha : getLinhas()) {
            if (!linha.isMatch()) {
                linhasSemMatch.add(linha);
            }
        }

        return linhasSemMatch;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public boolean isDirectory() {
        if (getArquivo() != null) {
            return getArquivo().isDirectory();
        }

        return false;
    }
}
