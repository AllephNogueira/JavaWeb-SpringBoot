import java.text.SimpleDateFormat;
import java.util.Date;

public class Exemplo2 {
    public static void main(String[] args) {
        System.out.println("Exemplo 02");

        // Criando meu formato
        // Dia da semana, hora.
        String formatado = "E, k:m - a";
        // Iniciando a classe
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatado);
        // Passando a classe como uma nova data para dentro de uma variavel
        String date = simpleDateFormat.format(new Date());
        // Imprimindo a variavel
        System.out.println(date);

        //SAIDA
        // Exemplo 02
        //qua., 12:51 - PM

    }
}
