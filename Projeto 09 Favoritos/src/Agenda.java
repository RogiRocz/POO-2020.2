import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    private List<Contact> contacts;
    private List<Contact> favorites;

    public Agenda() {
        contacts = new ArrayList<>();
        favorites = new ArrayList<>();
    }

    public void addContact(String name, Fone fone) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.addFone(fone);
                return;
            }
        }

        contacts.add(new Contact(name));
        addContact(name, fone);
        Collections.sort(contacts);
    }

    public boolean rmContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                contacts.remove(c);
                favorites.remove(c);
                return true;
            }
        }

        return false;
    }

    public List<Contact> search(String pattern) {

        ArrayList<Contact> aux = new ArrayList<>();
        for (Contact c : contacts) {
            String cToString = c.toString();
            if (cToString.indexOf(pattern) != -1) {
                aux.add(c);
            }
        }

        if (aux.isEmpty()) {
            aux = null;
        }

        return aux;
    }

    public Contact getContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    public boolean favoriteContact(String nameContact) {
        for (Contact c : contacts) {
            if (c.getName().equals(nameContact)) {
                c.setFavorite(true);
                favorites.add(c);
                Collections.sort(favorites);
                return true;
            }
        }

        // Nome de contato inexistente
        return false;
    }

    public boolean disfavorContact(String nameContact) {
        for (Contact c : contacts) {
            if (c.getName().equals(nameContact)) {
                c.setFavorite(false);
                favorites.remove(c);
                Collections.sort(favorites);
                return true;
            }
        }

        // Nome de contato inexistente
        return false;
    }

    public List<Contact> getFavorites() {
        return favorites;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (Contact c : contacts) {
            if (c.isFavorite()) {
                bld.append("@ " + c + "\n");
            } else {
                bld.append("- " + c + "\n");
            }
        }

        return bld.toString();
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Agenda agenda = new Agenda();

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
                        String serial[] = op[i].split(":");
                        String label = serial[0];
                        String number = serial[1];
                        if (Fone.validate(number)) {
                            agenda.addContact(name, new Fone(label, number));
                        } else {
                            System.out.println("fail: numero: " + number + " invalido");
                        }
                    }
                    break;
                case "rm":
                    String nameRemove = op[1];
                    agenda.rmContact(nameRemove);
                    break;
                case "search":
                    String pattern = op[1];
                    for (Contact c : agenda.search(pattern)) {
                        System.out.println(c);
                    }
                    break;
                case "rmFone":
                    String nameRemoveFone = op[1];
                    int index = Integer.parseInt(op[2]);
                    agenda.getContact(nameRemoveFone).rmFone(index);
                    break;
                case "star":
                    if (!agenda.favoriteContact(op[1])) {
                        System.out.println("fail: contato " + op[1] + " nao existe");
                    }
                    break;
                case "starred":
                    for (Contact c : agenda.getFavorites()) {
                        System.out.println("@ " + c);
                    }
                    break;
                case "unstar":
                    if (!agenda.disfavorContact(op[1])) {
                        System.out.println("fail: contato " + op[1] + " nao existe");
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
