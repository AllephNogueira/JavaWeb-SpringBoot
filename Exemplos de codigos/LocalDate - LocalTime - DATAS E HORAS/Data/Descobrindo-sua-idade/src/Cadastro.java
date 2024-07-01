import java.time.LocalDate;

public class Cadastro {
    private String nome;
    private int anoNascimento;
    private int anoAtual = LocalDate.now().getYear();



    public Cadastro(String nome, int anoNascimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    public int idadeAtual() {
        return anoAtual - anoNascimento;
    }


    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }
}
