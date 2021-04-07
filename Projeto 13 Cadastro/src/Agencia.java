import java.util.TreeMap;

public class Agencia {
    private TreeMap<String, Cliente> clientes;
    private TreeMap<Integer, Conta> contas;
    private int nextIdConta;

    private Conta getConta(int id) {
        for (Conta c : contas.values()) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Agencia() {
        this.clientes = new TreeMap<>();
        this.contas = new TreeMap<>();
        this.nextIdConta = 0;
    }

    public void addCliente(String idCliente) {
        Cliente cliente = new Cliente(idCliente);
        Conta cc = new ContaCorrente(nextIdConta, idCliente);
        contas.put(nextIdConta, cc);
        nextIdConta++;
        Conta cp = new ContaPoupanca(nextIdConta, idCliente);
        contas.put(nextIdConta, cp);
        nextIdConta++;
        cliente.addConta(cc);
        cliente.addConta(cp);
        clientes.put(idCliente, cliente);
    }

    public void sacar(int idConta, float valor) {
        try {
            if (getConta(idConta).getSaldo() - valor >= 0) {
                getConta(idConta).sacar(valor);
            } else {
                System.out.println("fail: saldo insuficiente");
            }
        } catch (Exception e) {
            System.out.println("fail: usuario nao encontrado");
        }
    }

    public void depositar(int idConta, float valor) {
        try {
            getConta(idConta).depositar(valor);
        } catch (Exception e) {
            System.out.println("fail: usuario nao encontrado");
        }
    }

    public void transferir(int idEmissor, int idDestinatario, float valor) {
        try {
            if (getConta(idEmissor).getSaldo() - valor >= 0) {
                getConta(idEmissor).transferir(getConta(idDestinatario), valor);
            } else {
                System.out.println("fail: saldo insuficiente");
            }
        } catch (Exception e) {
            System.out.println("fail: usuario nao encontrado");
        }
    }

    public void atualizacaoMensal() {
        // Testando se chama cada conta certa
        for (Conta c : contas.values()) {
            c.atualizacaoMensal();
        }
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        // Clientes
        bld.append("Clientes: \n");
        for (Cliente cliente : clientes.values()) {
            bld.append(cliente + "\n");
        }
        // Contas
        bld.append("Contas: \n");
        for (Conta conta : contas.values()) {
            bld.append(conta + "\n");
        }

        return bld.toString();
    }
}
