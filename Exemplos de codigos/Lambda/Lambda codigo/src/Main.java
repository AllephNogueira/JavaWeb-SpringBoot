public class Main {
    public static void main(String[] args) {


        // Função lambda
        // Quando for expressao lambda precisamos criar uma interface.
            // Interface operação.
        Operacao soma = (parametro1, parametro2) -> { return parametro1 + parametro2; }; // Aqui esta nossa funçao lambda, passando os parametros
        // Aqui estamos declarando a interface Operação e chamando de soma.

        // Agora estamos chamando a nossa funçao que o nome é SOMA
        // E jogando para dentro de uma variavel.
        int resultado = soma.operar(10, 20);
        System.out.println(resultado);


    }
}