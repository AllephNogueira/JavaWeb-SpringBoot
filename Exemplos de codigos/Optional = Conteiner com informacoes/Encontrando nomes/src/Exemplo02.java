import java.util.Optional;

public class Exemplo02 {
    public static void main(String[] args) {

        meuOptional user1 = new meuOptional();

        // Passando o dado

        //user1.setNome("Alleph");

        // Agora vamos ver se o usuario digitou algo.

        Optional<String> optionalNome = user1.getNome();

        optionalNome.ifPresent(System.out::println); // Só irá printar o nome se não for null

        String nome = optionalNome.orElse("Nome não disponível"); // Irá retornar "Nome não disponível" caso nome seja null
        System.out.println(nome);


    }
}
