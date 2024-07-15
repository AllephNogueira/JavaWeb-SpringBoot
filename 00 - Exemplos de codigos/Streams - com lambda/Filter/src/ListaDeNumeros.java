import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaDeNumeros {
    private ArrayList<Integer> numero;

    public ListaDeNumeros(ArrayList<Integer> numero) {
        this.numero = numero;
    }

    public ArrayList<Integer> getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "" + numero;
    }
}
