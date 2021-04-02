import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String op[];
        String e;
        Scanner read = new Scanner(System.in);
        AgendaPlus agenda = new AgendaPlus();

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
            case "show":
                System.out.println(agenda);
                break;
            case "add":
                String name = op[1];
                for (int i = 2; i < op.length; i++) {
                    String c[] = op[i].split(":");
                    String label = c[0];
                    String number = c[1];
                    if (Fone.validate(number)) {
                        // ME nota
                        agenda.addContact(name, new Fone(label, number));
                    } else {
                        System.out.println("fail: formato de numero invalido");
                    }
                }
                break;
            case "rm":
                String nameContact = op[1];
                if (!agenda.rmContact(nameContact)) {
                    System.out.println("fail: contato " + nameContact + " nao existe");
                }
                break;
            case "search":
                String pattern = op[1];
                for (Contact c : agenda.search(pattern)) {
                    if (c == null) {
                        System.out.println("Nao encontrado");
                        return;
                    }
                    System.out.println(c);
                }
                break;
            case "rmFone":
                String nameRemoveFone = op[1];
                int index = Integer.parseInt(op[2]);
                try {
                    agenda.getContact(nameRemoveFone).rmFone(index);
                } catch (Exception s) {
                    System.err.println("fail: erro na exclusao do fone");
                }
                break;
            case "star":
                String nameContactStar = op[1];
                if (agenda.getContact(nameContactStar) == null) {
                    System.out.println("fail: contato " + nameContactStar + " nao existe");
                }
                agenda.bookmark(nameContactStar);
                break;
            case "unstar":
                String nameContactUnStar = op[1];
                if (agenda.getContact(nameContactUnStar) == null) {
                    System.out.println("fail: contato " + nameContactUnStar + " nao existe");
                }
                agenda.unBookmark(nameContactUnStar);
                break;
            case "starred":
                for (ContactPlus c : agenda.getBookmarks()) {
                    System.out.println("@ " + c);
                }
                break;
            case "end":
                break;
            default:
                System.out.println("fail: comando desconhecido");
                break;
            }

        } while (!op[0].equals("end"));

        read.close();
    }
}
