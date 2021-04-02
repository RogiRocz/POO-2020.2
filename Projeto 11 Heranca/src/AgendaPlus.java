import java.util.Collection;
import java.util.TreeMap;

public class AgendaPlus extends Agenda {
    private TreeMap<String, ContactPlus> bookmarks;

    public AgendaPlus() {
        this.bookmarks = new TreeMap<>();
    }

    @Override
    public boolean rmContact(String nameContact) {
        for (String c : contacts.keySet()) {
            if (c.equals(nameContact)) {
                bookmarks.remove(nameContact);
                contacts.remove(nameContact);
                return true;
            }
        }
        // Contato inexistente
        return false;
    }

    // Nao sei o tipo de id
    public void bookmark(String nameContact) {
        for (ContactPlus c : contacts.values()) {
            if (c.getName().equals(nameContact)) {
                c.setBookmark(true);
                bookmarks.put(c.getName(), c);
            }
        }
    }

    public void unBookmark(String nameContact) {
        for (ContactPlus c : getContacts()) {
            if (c.getName().equals(nameContact)) {
                c.setBookmark(false);
                bookmarks.remove(c.getName());
            }
        }
    }

    public Collection<ContactPlus> getBookmarks() {
        return bookmarks.values();
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (ContactPlus c : contacts.values()) {
            if (c.getStarred()) {
                bld.append("@ " + c + "\n");
            } else {
                bld.append("- " + c + "\n");
            }
        }

        return bld.toString();
    }
}