import java.util.ArrayList;
import java.util.List;

public class Contact implements Comparable<Contact> {
    private String nameContact;
    List<Fone> fones;
    // boolean favorite;

    public Contact(String nameContact, Fone fn) {
        this.nameContact = nameContact;
        this.fone = new ArrayList<>();
        this.fones.add(fn);
        this.favorite = false;
    }

    public void addFone(Fone f) {
        fones.add(f);
    }

    public void rmFone(int index) {
        fones.remove(index);
    }

    public String getNameContact() {
        return nameContact;
    }

    /*
     * public boolean isFavorite() { return favorite; }
     * 
     * public void setFavorite(boolean favorite) { this.favorite = favorite; }
     */

    @Override
    public int compareTo(Contact o) {
        return nameContact.compareTo(o.getNameContact());
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(nameContact);
        for (int i = 0; i < fones.size(); i++) {
            bld.append(" [");
            bld.append(i + ":" + fones.get(i));
            bld.append("] ");
        }

        return bld.toString();
    }

}