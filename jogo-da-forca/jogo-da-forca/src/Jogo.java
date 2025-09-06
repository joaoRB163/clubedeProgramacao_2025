import java.util.Arrays;
import java.util.Scanner;

public class Jogo {

    private ListaTemas listaTemas = new ListaTemas();
    // dar um jeito melhor de preencher depois

    public void Iniciar() {
        this.listaTemas.adicionarTema(PreencherLista());
        Scanner ler = new Scanner(System.in);
        ListaErros listaErros = new ListaErros();
        ListaRespostas listaRespostas = new ListaRespostas();

        boolean dica = false;
        Tema sorteioTema = this.listaTemas.SortearTema();
        Palavra sorteioPalavra = sorteioTema.getListaPalavras().SortearPalavra();
        Boneco boneco = new Boneco();
        Tela tela = new Tela(sorteioPalavra);
        tela.PreencherVet();

        Resposta resposta = new Resposta(null, false);

        // precisa eliminar possíveis erros de entrada com caracteres especiais
        while (boneco.getChances() != 0) {
            tela.PrintarForca(listaErros, sorteioTema);
            System.out.println("\nDigite sua resposta: ");

            resposta.setResposta(ler.nextLine());
            if (listaRespostas.ContemResposta(resposta)) {
                System.out.println("Você já digitou essa resposta. Digite novamente.");

            } else {
                resposta.setVerificar(sorteioPalavra.Verificar(resposta, tela));
                if (resposta.isVerificar()) {
                    if (resposta.getPalavra().equals(sorteioPalavra.getPalavra())) {
                        tela.setVetPalavra(resposta.getPalavra().toCharArray());
                        tela.PrintarForca(listaErros, sorteioTema);
                        break;
                    } else {
                        for (int i = 0; i < sorteioPalavra.getPalavra().toCharArray().length; i++) {
                            tela.setVetPalavra(tela.CompletarPalavra(resposta, i));
                        }
                    }
                } else {
                    tela.MontarBoneco(boneco);
                    boneco.DescontarChance();
                    listaErros.AdicionarResposta(resposta.getPalavra());
                }
            }
            listaRespostas.AdicionarPalavra(resposta);

            if (boneco.getChances() < 4 && !dica) {
                dica = tela.PrintarDica(sorteioPalavra, sorteioPalavra.ReceberDica(resposta, boneco));
            }

            if (Arrays.equals(tela.getVetPalavra(), sorteioPalavra.getPalavra().toCharArray())) {
                System.out.println("O jogo terminou");
                break;
            }
        }
    }

    public Tema PreencherLista() {
        ListaPalavras listaPalavras = new ListaPalavras();
        String[] palavras = {
                "natacao",
                "golfe",
                "atletismo"
        };

        String[] dicas = {
                "Modalidade aquática, esporte olímpico.",
                "Usa-se um taco para acertar a bolinha.",
                "Engloba diversas modalidades, como saltos e arremessos."
        };

        for (int i = 0; i < palavras.length; i++) {
            Palavra Novapalavra = new Palavra(palavras[i], dicas[i]);
            listaPalavras.AdicionarPalavra(Novapalavra);
        }

        Tema novoTema = new Tema("Esporte", listaPalavras);
        return novoTema;
    }
}