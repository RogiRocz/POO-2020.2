import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class Agenda {
    protected TreeMap<String, ContactPlus> contacts;

    public Agenda() {
        contacts = new TreeMap<>();
    }

    public void addContact(String name, Fone fone) {
        // contacts.put(contato.getName(), contato);
        for (ContactPlus c : contacts.values()) {
            if (c.getName().equals(name)) {
                c.addFone(fone);
                return;
            }
        }

        contacts.put(name, new ContactPlus(name));
        addContact(name, fone);
    }

    public boolean rmContact(String name) {
        for (String c : contacts.keySet()) {
            if (c.equals(name)) {
                contacts.remove(name);
                return true;
            }
        }

        // Contato inexistente
        return false;
    }

    public List<ContactPlus> search(String pattern) {

        ArrayList<ContactPlus> aux = new ArrayList<>();
        for (ContactPlus c : contacts.values()) {
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

    public ContactPlus getContact(String name) {
        for (ContactPlus c : contacts.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    public Collection<ContactPlus> getContacts() {
        return contacts.values();
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (ContactPlus c : contacts.values()) {
            bld.append(c + "\n");
            /*
             * if (c.getStarred()) { bld.append("@ " + c + "\n"); } else { bld.append("- " +
             * c + "\n"); }
             */
        }

        return bld.toString();
    }
}
