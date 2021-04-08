import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class Medico implements IMedico, IChat {
    private TreeMap<String, Paciente> pacientes;
    private ArrayList<Mensagem> mensagens;
    private String idMedico;
    private String especialidade;

    public Medico(String idMedico, String especialidade) {
        this.idMedico = idMedico;
        this.especialidade = especialidade;
        this.pacientes = new TreeMap<>();
        this.mensagens = new ArrayList<>();
    }

    @Override
    public String getId() {
        return idMedico;
    }

    @Override
    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public void addPaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    @Override
    public void rmPaciente(String idPaciente) {
        pacientes.remove(idPaciente);

    }

    @Override
    public Collection<Paciente> getPacientes() {
        return pacientes.values();
    }

    @Override
    public void sendMessage(Mensagem msg) throws HospitalExcessao {
        try {
            pacientes.get(msg.getIdDestinario()).addMessage(msg);
        } catch (Exception e) {
            throw new HospitalExcessao("fail: " + this.idMedico + " nao conhece " + msg.getIdDestinario());
        }

    }

    @Override
    public void addMessage(Mensagem msg) {
        mensagens.add(msg);
    }

    @Override
    public void clear() {
        this.mensagens.clear();
    }

    @Override
    public List<Mensagem> getInbox() {
        return mensagens;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(idMedico + ":" + especialidade + "\t Pacs: ");
        bld.append("[ ");
        for (Paciente p : pacientes.values()) {
            bld.append(p.getId() + " ");
        }
        bld.append("]");

        return bld.toString();
    }
}