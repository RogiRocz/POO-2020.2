import java.util.Scanner;

public class Moto {
    private int potencia;
    private int tempo;
    private Pessoa passageiro;

    public Moto(int potencia) {
        this.potencia = potencia;
        this.tempo = 0;
        this.passageiro = null;
    }

    public void setPotencia(int p) {
        this.potencia = p;
    }

    public Pessoa getPassageiro() {
        return this.passageiro;
    }

    public int getTempo() {
        return this.tempo;
    }

    public void comprarTempo(int valor) {
        this.tempo += valor;
    }

    public void subir(Pessoa pass) {
        if (this.passageiro != null) {
            System.out.println("fail: moto ocupada");
        } else {
            this.passageiro = pass;
        }
    }

    public void descer() {
        if (this.passageiro != null) {
            this.passageiro = null;
        } else {
            System.out.println("fail: moto vazia");
        }
    }

    public String buzinar() {
        if (this.passageiro == null) {
            return "fail: moto vazia\n";
        }

        StringBuilder bld = new StringBuilder();
        bld.append("P");

        for (int i = 0; i < this.potencia; i++) {
            bld.append("e");
        }

        bld.append("m");

        return bld.toString();
    }

    public void dirigir(int tempoGasto) {
        if (this.tempo == 0) {
            System.out.println("fail: tempo zerado");
        } else {
            this.tempo += tempoGasto * -1;
        }
    }

    @Override
    public String toString() {
        return "Moto [potencia = " + potencia + ", minutos = " + tempo + ", passageiro = " + passageiro + "]";
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Moto m = new Moto(1);

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
                case "show":
                    System.out.println(m);
                    break;
                case "init":
                    if (op.length == 2) {
                        m.setPotencia(Integer.parseInt(op[1]));
                        break;
                    }
                    m.setPotencia(1);
                    break;
                case "buzinar":
                    System.out.println(m.buzinar());
                    break;
                case "in":
                    Pessoa p = new Pessoa(op[1], Integer.parseInt(op[2]));
                    m.subir(p);
                    break;
                case "out":
                    m.descer();
                    break;
                case "drive":
                    if (m.passageiro == null) {
                        System.out.println("fail: sem pessoa pra dirigir");
                    } else if (m.passageiro.getIdade() > 10) {
                        System.out.println("fail: muito grande pra andar de moto");
                    } else if (m.tempo == 0) {
                        System.out.println("fail: sem tempo pra dirigir");
                    } else {
                        int tempoGasto = Integer.parseInt(op[1]);
                        tempoGasto *= tempoGasto < 0 ? -1 : 1;

                        if (m.tempo - tempoGasto < 0) {
                            tempoGasto -= (tempoGasto - m.tempo);
                            System.out.println("fail: so foi possivel dirigir " + m.tempo + " minutos");
                        }

                        m.dirigir(tempoGasto);
                    }
                    break;
                case "buy":
                    int valor = Integer.parseInt(op[1]);
                    valor *= valor < 0 ? -1 : 1;
                    m.comprarTempo(valor);
                    break;
                default:
                    break;
            }
        } while (!(op[0].equals("end")));
        read.close();
    }
}
