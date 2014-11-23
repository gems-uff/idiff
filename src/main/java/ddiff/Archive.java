package ddiff;

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

import ilcs.grain.LineGrain;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;


/**
 * Guarda a representacao do arquivo que deve ser comparado.
 * 
 * @author Eraldo
 * 
 */
public class Archive {

    /**
     * Archive representado.
     */
    private File arquivo = null;
    /**
     * Codigo hash do conteudo do arquivo.
     */
    private String hash = "";
    /**
     * Linhas do arquivo
     */
    private List<Line> linhas =  new ArrayList<Line>();
    /**
     * Id como referencia ao diretorio base de comparacao
     */
    private int id;
    /**
     * Armazena se ja foi localizado match do arquivo
     */
    private boolean match;
    /**
     * Path diretorio base comparacao
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
     * Getter para o hash do conteudo do arquivo
     * 
     * @return
     */
    public String getHash() {
        return hash;
    }

    /**
     * Construtor padrao
     * 
     * @param arquivo
     *            Archive que deve ser representado.
     * @param id
     * 
     * @param pathBaseComparacao
     */
    public Archive(File arquivo, int id, String pathBaseComparacao) {
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
        String mimeType = null;
        try {
            mimeType = Magic.getMagicMatch(getArquivo(), false).getMimeType();
        } catch (MagicParseException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MagicMatchNotFoundException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        } catch (MagicException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (null != mimeType) && (mimeType.startsWith("text"));
    }

    /**
     * Carrega as linhas contidas no arquivo.
     */
    private void carregarLinhas() {
        if (isArquivo() || isText()) {
            linhas = new ArrayList<Line>();
            String line = null;
            BufferedReader reader = null;
            try {
                int idStart = 0;
                reader = new BufferedReader(new FileReader(getArquivo()));
                while ((line = reader.readLine()) != null) {
                    linhas.add(new Line(line, linhas.size() + 1, idStart));
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
     * Gera um codigo hash SHA-1 com o conteudo do arquivo representado.
     * 
     * @return Codigo hash SHA-1
     */
    private void gerarHash() {
        if (isArquivo()) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA1");
                InputStream is = new FileInputStream(getArquivo());
                byte[] buffer = new byte[8192];
                String output = "";
                int read = 0;
                try {
                    while ((read = is.read(buffer)) > 0) {
                        digest.update(buffer, 0, read);
                    }
                    byte[] sha1Sum = digest.digest();
                    BigInteger bigInt = new BigInteger(1, sha1Sum);
                    output = bigInt.toString(16);
                } catch (IOException e) {
                    throw new RuntimeException(
                            "Nao foi possivel processar o arquivo.", e);
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        throw new RuntimeException(
                                "Nao foi possivel fechar o arquivo", e);
                    }
                }
                hash = output;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Algoritmo SHA1 nao foi encontrado.", e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Nao foi possivel carregar o arquivo.",
                        e);
            }
        } else {
            hash = getPathRelativo();
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
        if (!(obj instanceof Archive)) {
            return false;
        }

        Archive other = (Archive) obj;

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

    public void setLinhas(List<Line> linhas) {
        this.linhas = linhas;
    }

    public List<Line> getLinhas() {
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
     * Retorna o path relativo ao diretorio base de comparacao.
     * 
     * @return String com o path.
     */
    public String getPathRelativo() {
        return arquivo.getAbsolutePath().replace(pathBaseComparacao, "");
    }

    
    int tamanhoA = -1;
    /**
     * Calcula o tamanho das linhas sem match, desconsiderando os espacos em branco.
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
            linhas = new ArrayList<Line>();        
            tamanhoA = tamanho;
        }
        
        return tamanhoA;
//        return tamanho;
    }

    /**
     * Retorna as linhas sem match
     * @return Uma lista com as linhas sem match
     */
    private List<Line> getLinhasSemMatch() {
        List<Line> linhasSemMatch = new ArrayList<Line>();
        for (Line linha : getLinhas()) {
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
