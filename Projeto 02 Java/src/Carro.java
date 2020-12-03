import java.util.Scanner;

public class Carro {
    int pass;
    float gas;
    float km;

    Carro() {
        this.pass = 0;
        this.gas = 0;
        this.km = 0;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass += pass;
    }

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas += gas;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km += km;
    }

    public String toString() {
        return "pass: " + pass + ", gas: " + gas + ", km: " + km;
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Carro c = new Carro();

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
                case "show":
                    System.out.println(c);
                    break;
                case "in":
                    if (c.getPass() == 2) {
                        System.out.println("fail: limite de pessoas atingido");
                    } else {
                        c.setPass(1);
                    }
                    break;
                case "out":
                    if (c.getPass() == 0) {
                        System.out.println("fail: nao ha ninguem no carro");
                    } else {
                        c.setPass(-1);
                    }
                    break;
                case "fuel":
                    float gas = Float.parseFloat(op[1]);
                    if (c.getGas() + gas > 100) {
                        float sobrando = c.getGas() + gas - 100;
                        gas -= sobrando;
                    }

                    c.setGas(gas);
                    break;
                case "drive":
                    float km = Float.parseFloat(op[1]);
                    if (c.getPass() != 0) {
                        if (c.getGas() == 0) {
                            System.out.println("fail: tanque vazio");
                            break;
                        }

                        if (c.getGas() - km < 0) {
                            System.out.println("fail: tanque vazio apos andar " + c.getGas() + " km");
                            c.setKm(c.getGas());
                            c.setGas(-1 * c.getGas());
                        } else {
                            c.setKm(km);
                            c.setGas(-km);
                        }
                    } else {
                        System.out.println("fail: nao ha ninguem no carro");
                    }
                    break;
                default:
                    break;
            }

        } while (!(op[0].equals("end")));

        read.close();
    }
}
