import java.util.ArrayList;
import java.util.List;

public class Contact implements Comparable<Contact> {
    private String name;
    List<Fone> fones;

    public Contact(String name) {
        this.name = name;
        this.fones = new ArrayList<>();
    }

    public void addFone(Fone f) {
        fones.add(f);
    }

    public void rmFone(int index) {
        fones.remove(index);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(name);
        for (int i = 0; i < fones.size(); i++) {
            bld.append(" [");
            bld.append(i + ":" + fones.get(i));
            bld.append("] ");
        }

        return bld.toString();
    }
}