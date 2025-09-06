public class Resposta extends Palavra {

    private String resposta;
    private boolean verificar;

    public Resposta(String resposta, boolean verificar) {
        super(null, null);
        this.resposta = resposta;
        this.verificar = verificar;
    }

    @Override
    public String getPalavra() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isVerificar() {
        return verificar;
    }

    public void setVerificar(boolean verificar) {
        this.verificar = verificar;
    }

}
