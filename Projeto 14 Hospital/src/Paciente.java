import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class Paciente implements IPaciente, IChat {
    private TreeMap<String, Medico> medicos;
    private ArrayList<Mensagem> mensagens;
    private String idPaciente;
    private String diagnostico;

    public Paciente(String idPaciente, String diagnostico) {
        this.idPaciente = idPaciente;
        this.diagnostico = diagnostico;
        this.medicos = new TreeMap<>();
        this.mensagens = new ArrayList<>();
    }

    @Override
    public String getId() {
        return idPaciente;
    }

    @Override
    public String getDiagnostico() {
        return diagnostico;
    }

    @Override
    public void addMedico(Medico medico) {
        medicos.put(medico.getId(), medico);
    }

    @Override
    public void rmMedico(String idMedico) {
        medicos.remove(idMedico);
    }

    @Override
    public Collection<Medico> getMedicos() {
        return medicos.values();
    }

    @Override
    public void sendMessage(Mensagem msg) throws HospitalExcessao {
        try {
            medicos.get(msg.getIdDestinario()).addMessage(msg);
        } catch (Exception e) {
            throw new HospitalExcessao("fail: " + this.idPaciente + " nao conhece " + msg.getIdDestinario());
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
        bld.append(idPaciente + ":" + diagnostico + "\t Meds: ");
        bld.append("[ ");
        for (Medico m : medicos.values()) {
            bld.append(m.getId() + " ");
        }
        bld.append("]");

        return bld.toString();
    }
}