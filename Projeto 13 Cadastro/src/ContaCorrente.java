public class ContaCorrente extends Conta {
    private float tarifaMensal = 20;

    public ContaCorrente(int id, String idCliente) {
        super(id, idCliente);
        this.tipo = "CC";
    }

    public void atualizacaoMensal() {
        this.saldo -= tarifaMensal;
    }

    @Override
    public String toString() {
        return super.toString() + ":" + tipo;
    }
}
