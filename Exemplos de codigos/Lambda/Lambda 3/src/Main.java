public class Main {
    public static void main(String[] args) {


        // 1 Chamando a interface que é obrigatoria criar (Calculo)
        // 2 Dando um nome para essa funcao (subtracao)
       Calculo subtracao = ((a, b) -> a - b);
       // 3 Adicionando subtracao que vai chamar o metodo soma que esta dentro da Interface calculo.
        // Colocando tudo dentro de uma variavel.
       int resultado = subtracao.soma(50,10);
       System.out.println(resultado);

       Calculo divisaoNumero = ((a, b) -> a /b);
       resultado = divisaoNumero.soma(50, 10);
       System.out.println(resultado);

    }
}