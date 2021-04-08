import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Hospital hospital = new Hospital();

        do {
            e = read.nextLine();
            op = e.split(" ");

            try {
                switch (op[0]) {
                case "show":
                    System.out.println(hospital);
                    break;
                case "vinc":
                    String idMedicoVinc = op[1];
                    for (int i = 2; i < op.length; i++) {
                        String idPacienteVinc = op[i];
                        hospital.vincular(idMedicoVinc, idPacienteVinc);
                    }
                    break;
                case "msg":
                    String idRemetente = op[1];
                    String idDestinario = op[2];
                    StringBuilder mensagem = new StringBuilder();
                    for (int i = 3; i < op.length; i++) {
                        mensagem.append(op[i] + " ");
                    }
                    hospital.sendMensagem(new Mensagem(idRemetente, idDestinario, mensagem.toString()));
                    break;
                case "inbox":
                    String id = op[1];
                    System.out.println(hospital.inbox(id));
                    break;
                case "addPacs":
                    for (int i = 1; i < op.length; i++) {
                        String[] div = op[i].split("-");
                        hospital.addPaciente(new Paciente(div[0], div[1]));
                    }
                    break;
                case "addMeds":
                    for (int i = 1; i < op.length; i++) {
                        String[] div = op[i].split("-");
                        hospital.addMedico(new Medico(div[0], div[1]));
                    }
                    break;
                case "rmPacs":
                    for (int i = 1; i < op.length; i++) {
                        String idPaciente = op[i];
                        hospital.rmPaciente(idPaciente);
                    }
                    break;
                case "rmMeds":
                    for (int i = 1; i < op.length; i++) {
                        String idMedico = op[i];
                        hospital.rmMedico(idMedico);
                    }
                    break;
                case "end":
                    break;
                default:
                    System.out.println("fail: comando invalido");
                    break;
                }
            } catch (Exception s) {
                System.out.println(s.getMessage());
            }

        } while (!op[0].equals("end"));

        read.close();
    }
}