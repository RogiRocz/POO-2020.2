class ContaException extends RuntimeException {
    public ContaException(String msg) {
        super(msg);
    }
}

public abstract class Conta {
    protected int id;
    protected float saldo;
    protected String idCliente;
    protected String tipo;

    protected Conta(int id, String idCliente) {
        this.id = id;
        this.idCliente = idCliente;
        this.saldo = 0;
    }

    public int getId() {
        return id;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void sacar(float valor) {
        this.saldo -= valor;
    }

    public void depositar(float valor) {
        this.saldo += valor;
    }

    public void transferir(Conta o, float valor) {
        this.sacar(valor);
        o.depositar(valor);
    }

    public abstract void atualizacaoMensal();

    @Override
    public String toString() {
        return id + ":" + idCliente + ":" + saldo;
    }
}
