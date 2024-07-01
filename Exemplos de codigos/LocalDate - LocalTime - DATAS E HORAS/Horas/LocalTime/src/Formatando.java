import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatando {

    public static void main(String[] args) {
        LocalDateTime hoje = LocalDateTime.now();
        System.out.println(hoje);

        System.out.println("Observe os formatados sendo aplicado");

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        System.out.println(hoje.format(formatador));

    }
}
