import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Topic {
    List<Pass> cadeiras = null;
    int qntdPref;

    public Topic() {
    }

    public Topic(int lotacao, int qntdPref) {
        this.cadeiras = new ArrayList<>(Collections.nCopies(lotacao, null));
        this.qntdPref = qntdPref;
    }

    public boolean estaSentado(String name) {
        for (Pass pass : cadeiras) {
            if (pass.getName().equals(name)) {
                // Pass já está sentado
                return true;
            }
        }

        return false;
    }

    public boolean subir(Pass pass) {
        if (pass.getAge() >= 60) {
            // Idoso
            for (int i = 0; i < this.qntdPref; i++) {
                if (this.cadeiras.get(i) == null) {
                    // Cadeiras preferenciais
                    this.cadeiras.set(i, pass);
                    return true;
                }
            }

            for (int i = this.qntdPref; i < this.cadeiras.size(); i++) {
                if (this.cadeiras.get(i) == null) {
                    // Cadeiras normais
                    this.cadeiras.set(i, pass);
                    return true;
                }
            }
        } else {
            // Não idoso
            for (int i = this.qntdPref; i < this.cadeiras.size(); i++) {
                if (this.cadeiras.get(i) == null) {
                    // Cadeiras normais
                    this.cadeiras.set(i, pass);
                    return true;
                }
            }

            for (int i = 0; i < this.qntdPref; i++) {
                if (this.cadeiras.get(i) == null) {
                    // Cadeiras preferenciais
                    this.cadeiras.set(i, pass);
                    return true;
                }
            }

        }
        // Topic lotada
        return false;
    }

    public boolean descer(String name) {
        for (Pass pass : cadeiras) {
            if (pass.getName().equals(name)) {
                this.cadeiras.set(cadeiras.indexOf(pass), null);
                return true;
            }
        }
        // Passageiro não está na topic
        return false;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("[ ");
        if (this.cadeiras != null) {
            for (int i = 0; i < this.qntdPref; i++) {
                bld.append("@");
                if (this.cadeiras.get(i) != null) {
                    bld.append(this.cadeiras.get(i).toString());
                }
                bld.append(" ");
            }

            for (int i = this.qntdPref; i < this.cadeiras.size(); i++) {
                bld.append("=");
                if (this.cadeiras.get(i) != null) {
                    bld.append(this.cadeiras.get(i).toString());
                }
                bld.append(" ");
            }
        }

        bld.append("]");
        return bld.toString();
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Topic topic = new Topic();

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
                case "init":
                    int lotacao = Integer.parseInt(op[1]);
                    int qntdPref = Integer.parseInt(op[2]);
                    topic = new Topic(lotacao, qntdPref);
                    break;
                case "show":
                    System.out.println(topic);
                    break;
                case "in":
                    try {
                        if (op.length == 2 && topic.estaSentado(op[1])) {
                            System.out.println("fail: pass ja esta na topic");
                        } else if (op.length == 3) {
                            String name = op[1];
                            int age = Integer.parseInt(op[2]);
                            if (!topic.subir(new Pass(name, age))) {
                                System.out.println("fail: topic lotada");
                            }
                        }
                    } catch (Exception a) {
                        System.err.println("fail: esta faltando a idade");
                    }
                    break;
                case "out":
                    String name = op[1];
                    if (!topic.descer(name)) {
                        System.out.println("fail: pass nao esta na topic");
                    }
                    break;
                case "end":
                    break;
                default:
                    break;
            }
        } while (!(op[0]).equals("end"));

        read.close();
    }
}