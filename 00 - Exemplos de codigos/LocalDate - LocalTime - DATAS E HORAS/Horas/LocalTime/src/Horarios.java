import java.time.LocalTime;

public class Horarios {

    public static void main(String[] args) {
        LocalTime hoje = LocalTime.now();
        System.out.println(hoje);

        LocalTime aniversarioHoraAlice = LocalTime.of(22, 33, 15);
        System.out.println(aniversarioHoraAlice);
    }
}
