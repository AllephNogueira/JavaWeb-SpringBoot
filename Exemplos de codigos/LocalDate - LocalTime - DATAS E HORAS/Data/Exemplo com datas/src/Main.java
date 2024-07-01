import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String pattern = "E, dd MMM yyyy HH:mm:ss z"; // Criando meu formato
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern); // Inciando
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);

    }
}