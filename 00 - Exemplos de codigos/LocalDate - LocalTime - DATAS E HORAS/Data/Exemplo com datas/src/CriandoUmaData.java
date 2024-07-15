import java.time.LocalDate;
import java.time.Month;

public class CriandoUmaData {

    public static void main(String[] args) {

        LocalDate hoje = LocalDate.now(); // Vai retornar a data de hoje(now)
        System.out.println(hoje);

        // Criando uma data de aniversario.
        LocalDate aniversarioAlice = LocalDate.of(2005, Month.SEPTEMBER, 15);
        System.out.println(aniversarioAlice);
    }
}
