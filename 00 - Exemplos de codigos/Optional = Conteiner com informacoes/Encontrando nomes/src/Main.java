import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Vamos imaginar que estou procurando por um nome
        Scanner leitura = new Scanner(System.in);

        List<String> nomes = Arrays.asList("Alleph", "Fernanda", "Criux", "Amora", "Anastacia", "Calopsita 01", "Calopsita 02", "Valeira", "Gabriel", "Gleyce", "Guilherme", "Lorenzo", "Davi", "Larissa", "Livia", "Jane");

        System.out.println("Lista de formatura de Alleph Nogueira");
        System.out.println("Digite seu nome: ");
        String nomeBuscado = leitura.nextLine();

        // Container com as informacoes.
        Optional<String> nomesEncontrados = nomes.stream()
                .filter(n -> n.toUpperCase().contains(nomeBuscado.toUpperCase()))
                .findFirst();

        // nomesEncontrados tem algo?
        if (nomesEncontrados.isPresent()) {
            System.out.println("Você se encontra na lista: ");
            System.out.println("Divirta-se em minha festa de formatura.");
        }else {
            System.out.println("Você não foi convidado.");
        }
    }
}