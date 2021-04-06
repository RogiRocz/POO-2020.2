import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conta {
    private int idNext;
    private float balance;
    private int number;
    private List<Operacao> extract;

    public Conta(int number) {
        this.idNext = 0;
        this.balance = 0;
        this.number = number;
        this.extract = new ArrayList<>();
        addOperacao("abertura", 0);
    }

    public void addOperacao(String description, float value) {
        Operacao o = new Operacao(idNext, description, value, this.balance);
        extract.add(o);
        idNext++;
    }

    public boolean depositar(float value) {
        if (value <= 0) {
            System.out.println("fail: valor invalido");
            return false;
        }

        this.balance += value;
        addOperacao("deposito", value);
        return true;
    }

    public boolean sacar(float value) {
        if (value <= 0) {
            System.out.println("fail: valor invalido");
            return false;
        } else if (this.balance < value) {
            System.out.println("fail: saldo insuficiente");
            return false;
        }

        this.balance -= value;
        addOperacao("saque", value);
        return true;
    }

    public void tarifa(float value) {
        this.balance -= value;
        addOperacao("tarifa", value);
    }

    public boolean estornar(int index) {
        for (Operacao op : this.extract) {
            if (op.id == index) {
                if (op.description.equals("tarifa")) {
                    this.balance += op.value * -1;
                    addOperacao("extorno", op.value * -1);
                    return true;
                } else {
                    System.out.println("fail: indice " + index + " nao é tarifa");
                    return false;
                }
            }
        }
        System.out.println("fail: indice " + index + " invalido");
        return false;
    }

    public List<Operacao> getExtrato() {
        return this.extract;
    }

    public List<Operacao> getExtrato(int qntd) {
        List<Operacao> extratoParcial = new ArrayList<>();

        if (qntd > this.extract.size()) {
            System.out.println("fail: quantidade invalida");
            return extratoParcial;
        }

        for (int i = qntd; i > 0; i--) {
            extratoParcial.add(this.extract.get(this.extract.size() - i));
        }

        return extratoParcial;
    }

    @Override
    public String toString() {
        return "Conta: " + number + " saldo: " + balance;
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Conta conta = new Conta(0);

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
                case "init":
                    int numConta = Integer.parseInt(op[1]);
                    conta = new Conta(numConta);
                    break;
                case "show":
                    System.out.println(conta);
                    break;
                case "deposito":
                    float valorDeposito = Float.parseFloat(op[1]);
                    conta.depositar(valorDeposito);
                    break;
                case "saque":
                    float valorSaque = Float.parseFloat(op[1]);
                    conta.sacar(valorSaque);
                    break;
                case "tarifa":
                    float valorTarifa = Float.parseFloat(op[1]);
                    conta.tarifa(valorTarifa);
                    break;
                case "estorno":
                    for (int i = 1; i < op.length; i++) {
                        conta.estornar(Integer.parseInt(op[i]));
                    }
                    break;
                case "extrato":
                    for (Operacao opExtrato : conta.getExtrato()) {
                        System.out.println(opExtrato);
                    }
                    break;
                case "extratoN":
                    int qntd = Integer.parseInt(op[1]);
                    List<Operacao> extratoParcial = conta.getExtrato(qntd);
                    for (Operacao opExtrato : extratoParcial) {
                        System.out.println(opExtrato);
                    }
                    break;
                case "end":
                    break;
                default:
                    System.out.println("fail: comando nao reconhecido");
                    break;
            }
        } while (!(op[0].equals("end")));

        read.close();
    }
}