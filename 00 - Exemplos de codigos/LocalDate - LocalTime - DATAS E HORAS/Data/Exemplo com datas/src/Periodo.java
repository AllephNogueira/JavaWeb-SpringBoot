import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Periodo {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        System.out.println("Hoje é: " +hoje); // Dia de hoje

        LocalDate aniversarioAlice = LocalDate.of(2005, Month.SEPTEMBER, 15);
        System.out.println("Aniversario da alice: " + aniversarioAlice); // Dia do aniversario da alice

        int idade = aniversarioAlice.getYear() - hoje.getYear();
        System.out.println("Idade total da alice: " + idade); // Sua idade:

        Period periodo = Period.between(hoje, aniversarioAlice);
        System.out.println(periodo);

    }
}
