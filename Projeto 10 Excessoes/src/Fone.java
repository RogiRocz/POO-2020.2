public class Fone {
    String label;
    String number;

    public Fone(String label, String number) {
        this.label = label;
        this.number = number;
    }

    public static boolean validate(String number) {
        String caracteresValidos = "0123456789().";
        for (int i = 0; i < number.length(); i++) {
            if (caracteresValidos.indexOf(number.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return label + ":" + number;
    }
}
