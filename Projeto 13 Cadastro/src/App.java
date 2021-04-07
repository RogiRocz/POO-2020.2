import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Agencia agencia = new Agencia();

        do {
            e = read.nextLine();
            op = e.split(" ");
            try {
                switch (op[0]) {
                case "show":
                    System.out.println(agencia);
                    break;
                case "add":
                    String idCliente = op[1];
                    agencia.addCliente(idCliente);
                    break;
                case "sacar":
                    int idContaSaque = Integer.parseInt(op[1]);
                    float valorSaque = Float.parseFloat(op[2]);
                    agencia.sacar(idContaSaque, valorSaque);
                    break;
                case "depositar":
                    int idContaDeposito = Integer.parseInt(op[1]);
                    float valorDeposito = Float.parseFloat(op[2]);
                    agencia.depositar(idContaDeposito, valorDeposito);
                    break;
                case "transf":
                    int idContaDe = Integer.parseInt(op[1]);
                    int idContaPara = Integer.parseInt(op[2]);
                    float valorTransf = Float.parseFloat(op[3]);
                    agencia.transferir(idContaDe, idContaPara, valorTransf);
                    break;
                case "update":
                    agencia.atualizacaoMensal();
                    break;
                case "end":
                    break;
                default:
                    System.out.println("fail: comando invalido");
                    break;
                }
            } catch (ContaException m) {
                System.out.println(m.getMessage());
            }
        } while (!op[0].equals("end"));

        read.close();
    }
}