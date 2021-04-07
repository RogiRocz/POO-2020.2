public class ContaPoupanca extends Conta {
    private float rendimento = (float) 0.01;

    public ContaPoupanca(int id, String idCliente) {
        super(id, idCliente);
        this.tipo = "CP";
    }

    public void atualizacaoMensal() {
        this.saldo *= (1 + rendimento);
    }

    @Override
    public String toString() {
        return super.toString() + ":" + tipo;
    }
}
