import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String idCliente;
    private ArrayList<Conta> contas;

    public Cliente(String idCliente) {
        this.idCliente = idCliente;
        this.contas = new ArrayList<>();
    }

    public void addConta(Conta c) {
        contas.add(c);
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {

    }

    public String getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("- " + idCliente + " ");
        bld.append("[ ");
        for (Conta c : getContas()) {
            bld.append(c.getId() + " ");
        }
        bld.append("]");

        return bld.toString();
    }
}
