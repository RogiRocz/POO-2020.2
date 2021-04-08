import java.util.Collection;

public interface IPaciente {
    String getId();

    String getDiagnostico();

    void addMedico(Medico medico);

    void rmMedico(String idMedico);

    Collection<Medico> getMedicos();

    String toString();
}