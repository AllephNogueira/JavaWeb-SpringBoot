import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Fazendo uma lista
        List<String> nomes = Arrays.asList("Fernanda", "Valeria", "Alleph", "Livia", "Larissa");

        nomes.stream()
                .sorted() // 1 Colocando em ordem
                .limit(3) // Limitando para pegar apenas 3 nomes
                .filter(n -> n.startsWith("L")) // Pega somente oque começa com a letra L
                .map(n -> n.toUpperCase()) // Coloca tudo em maisuculo
                .forEach(System.out::println); // 2 Imprimir cada um dos nomes.

        // Agora imagina que queremos fazer uma operação intermediaria(Operação que vamos usar para algo futuramente)
        // Vamos querer pegar esses nomes e salvar
        System.out.println(nomes);

    }
}