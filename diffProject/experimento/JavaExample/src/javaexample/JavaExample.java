package javaexample;

/**
 *
 * @author Sisi */
public class JavaExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public class Pessoa {

        private int codigo;
        private int nome;

        public Pessoa() {
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int val) {
            this.codigo = val;
        }

        public int getNome() {
            return nome;
        }

        public void setNome(int val) {
            this.nome = val;
        }
    }

    public class Aluno extends Pessoa {

        private String Turma;

        public Aluno() {
        }

        public String getTurma() {
            return Turma;
        }

        public void setTurma(String val) {
            this.Turma = val;
        }

        @Override
        public int getCodigo() {
            return 0;
        }

        @Override
        public void setCodigo(int val) {
        }

        @Override
        public int getNome() {
            return 0;
        }

        @Override
        public void setNome(int val) {
        }
    }

    public class Professor extends Pessoa {

        private String disciplina;
        private int cargaHoraria;

        public Professor() {
        }

        public int getCargaHoraria() {
            return cargaHoraria;
        }

        public void setCargaHoraria(int val) {
            this.cargaHoraria = val;
        }

        public String getDisciplina() {
            return disciplina;
        }

        public void setDisciplina(String val) {
            this.disciplina = val;
        }

        @Override
        public int getCodigo() {
            return 0;
        }
    }
}
