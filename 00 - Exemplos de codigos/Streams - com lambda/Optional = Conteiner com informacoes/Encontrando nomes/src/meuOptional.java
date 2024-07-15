import java.util.Optional;

public class meuOptional{
    private String nome;


    public void setNome(String nome) {
        this.nome = nome;
    }

    //Vamos criar um método que pode retornar um valor null. Sem o Optional, ele pode causar erros indesejados, mas com o Optional, ele é mais seguro:

    public Optional<String> getNome() {
        // O nome pode ser null
        return Optional.ofNullable(nome);}
}
