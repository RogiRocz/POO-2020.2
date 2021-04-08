import java.util.Collection;

public interface IMedico {
    String getId();

    String getEspecialidade();

    void addPaciente(Paciente paciente);

    void rmPaciente(String idPaciente);

    Collection<Paciente> getPacientes();

    String toString();
}