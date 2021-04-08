public class Mensagem {
    private String idRemetente;
    private String idDestinario;
    private String msg;

    public Mensagem(String idRemetente, String idDestinario, String msg) {
        this.idRemetente = idRemetente;
        this.idDestinario = idDestinario;
        this.msg = msg;
    }

    public String getIdRemetente() {
        return idRemetente;
    }

    public String getIdDestinario() {
        return idDestinario;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return idRemetente + ":" + msg;
    }

}
