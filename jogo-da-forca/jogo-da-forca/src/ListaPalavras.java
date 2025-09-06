import java.util.ArrayList;
import java.util.Random;

public class ListaPalavras {

    private ArrayList<Palavra> ListaPalavras;

    public ListaPalavras() {
        this.ListaPalavras = new ArrayList<>();
    }

    public void AdicionarPalavra(Palavra palavra) {
        ListaPalavras.add(palavra);
    }

    public void setListaPalavras(ArrayList<Palavra> listaPalavras) {
        ListaPalavras = listaPalavras;
    }

    public Palavra SortearPalavra() {
        Random r = new Random();
        int aleatorio = r.nextInt(ListaPalavras.size());
        return ListaPalavras.get(aleatorio);
    }

}
