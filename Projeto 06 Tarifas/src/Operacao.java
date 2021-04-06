public class Operacao {
    int id;
    String description;
    float value;
    float balance;

    public Operacao(int id, String description, float value, float balance) {
        this.id = id;
        this.description = description;
        if (description.equals("saque") || description.equals("tarifa")) {
            this.value = value * -1;
        } else {
            this.value = value;
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + ":\t" + description + ":\t" + value + ":\t" + balance;
    }

}