import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criando minha lista
        List<Integer> numeros1 = Arrays.asList(10,5,3,4,8,9,2,0,1,0,3,0,4,5,18,19,35);

        numeros1.stream()
                .filter(n -> n % 2 == 0)
                .sorted()
                .forEach(System.out::println);

        // Aqui criamos uma funçao lambda apenas para retornar os numeros pares.

        // Imagina agora que queremos criar um metodo.


        System.out.println("Lista de numeros dentro de uma classe.");


        List<Integer> numeros = Arrays.asList(30,25,80,40,109,180,200,100,1,95,42,63);
        ListaDeNumeros listaNumeros = new ListaDeNumeros(new ArrayList<>(numeros));

        listaNumeros.getNumero().stream()
                        .filter(n -> n % 2 == 0)
                            .sorted()
                                .forEach(System.out::println);
    }
}