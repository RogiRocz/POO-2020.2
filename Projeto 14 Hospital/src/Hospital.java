import java.util.TreeMap;

class HospitalExcessao extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public HospitalExcessao(String msg) {
        super(msg);
    }
}

public class Hospital {
    private TreeMap<String, Paciente> pacientes;
    private TreeMap<String, Medico> medicos;

    public Hospital() {
        pacientes = new TreeMap<>();
        medicos = new TreeMap<>();
    }

    public void addPaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    public void addMedico(Medico medico) {
        medicos.put(medico.getId(), medico);
    }

    public void rmPaciente(String idPaciente) {
        try {
            for (Medico m : pacientes.get(idPaciente).getMedicos()) {
                m.rmPaciente(idPaciente);
            }
            pacientes.remove(idPaciente);
        } catch (Exception e) {
            throw new HospitalExcessao("fail: usuario nao encotrado");
        }
    }

    public void rmMedico(String idMedico) {
        try {
            for (Paciente p : medicos.get(idMedico).getPacientes()) {
                p.rmMedico(idMedico);
            }
            medicos.remove(idMedico);
        } catch (Exception e) {
            throw new HospitalExcessao("fail: usuario nao encontrado");
        }
    }

    public void vincular(String idMedico, String idPaciente) {
        Medico objMedico = medicos.get(idMedico);
        Paciente objPaciente = pacientes.get(idPaciente);

        if (objPaciente.getMedicos().isEmpty()) {
            objMedico.addPaciente(objPaciente);
            objPaciente.addMedico(objMedico);
            return;
        }

        for (Medico m : objPaciente.getMedicos()) {
            // Se nao tiver dois medicos com a mesma especialidade
            if (!m.getEspecialidade().equals(objMedico.getEspecialidade())) {
                objMedico.addPaciente(objPaciente);
                objPaciente.addMedico(objMedico);
                return;
            }
        }

        throw new HospitalExcessao("fail: ja existe um medico da especialidade " + objMedico.getEspecialidade());
    }

    public void sendMensagem(Mensagem msg) {
        // Lembrar de lançar excessoes
        if (medicos.containsKey(msg.getIdRemetente())) {
            medicos.get(msg.getIdRemetente()).sendMessage(msg);
        } else if (pacientes.containsKey(msg.getIdRemetente())) {
            pacientes.get(msg.getIdRemetente()).sendMessage(msg);
        } else {
            throw new HospitalExcessao("fail: usuario " + msg.getIdRemetente() + " nao encontrado");
        }
    }

    public String inbox(String id) {
        StringBuilder bld = new StringBuilder();
        // Lembrar de lançar excessoes
        if (medicos.containsKey(id)) {
            Medico objMedico = medicos.get(id);
            for (Mensagem msg : objMedico.getInbox()) {
                bld.append("[ ");
                bld.append(msg + " ");
                bld.append("]\n");
            }
            objMedico.clear();
        } else if (pacientes.containsKey(id)) {
            Paciente objPaciente = pacientes.get(id);
            for (Mensagem msg : objPaciente.getInbox()) {
                bld.append("[ ");
                bld.append(msg + " ");
                bld.append("]\n");
            }
            objPaciente.clear();
        } else {
            throw new HospitalExcessao("fail: usuario nao encontrado");
        }
        return bld.toString();
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        // Pacientes
        for (Paciente p : pacientes.values()) {
            bld.append("Pac: " + p + "\n");
        }

        // Medicos
        for (Medico m : medicos.values()) {
            bld.append("Med: " + m + "\n");
        }

        return bld.toString();
    }
}