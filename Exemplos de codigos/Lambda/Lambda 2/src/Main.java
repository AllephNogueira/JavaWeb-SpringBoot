public class Main {
    public static void main(String[] args) {


        Operacao soma = ((a, b) -> a + b);

        int resultado = soma.operacao(10,20);
        System.out.println(resultado);
    }
}