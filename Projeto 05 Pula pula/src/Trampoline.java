import java.util.ArrayList;
import java.util.Scanner;

public class Trampoline {
    private ArrayList<Kid> kidsWaiting;
    private ArrayList<Kid> kidsPlaying;

    public Trampoline() {
        kidsWaiting = new ArrayList<>();
        kidsPlaying = new ArrayList<>();
    }

    public void arrive(Kid kid) {
        kidsWaiting.add(kid);
    }

    public void in() {
        kidsPlaying.add(kidsWaiting.get(0));
        kidsWaiting.remove(0);
    }

    public void out() {
        kidsWaiting.add(kidsPlaying.get(0));
        kidsPlaying.remove(0);
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("=> ");
        for (Kid kid : this.kidsWaiting) {
            msg.append(kid.toString() + " ");
        }
        msg.append("=> [ ");
        for (Kid kid : this.kidsPlaying) {
            msg.append(kid.toString() + " ");
        }
        msg.append("]");
        return msg.toString();
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Trampoline t = new Trampoline();

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
                case "arrive":
                    String name = op[1];
                    int age = Integer.parseInt(op[2]);
                    Kid k = new Kid(name, age);
                    t.arrive(k);
                    break;
                case "show":
                    System.out.println(t);
                    break;
                case "in":
                    t.in();
                    break;
                case "out":
                    t.out();
                    break;
                case "end":
                    break;
                default:
                    break;
            }
        } while (!(op[0].equals("end")));

        read.close();
    }
}
