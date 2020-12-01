import java.util.Scanner;

public class SomaEnquanto {
    public static void main(String[] args) {
        int op = 1;
        int soma = 0;
        Scanner src = new Scanner(System.in);
        while (op != -1) {
            System.out.println("\nValor para soma");
            soma += src.nextInt();
            System.out.println("\nDeseja continuar somando? (-1 pra sair ou qualquer outro numero pra continuar)");
            op = src.nextInt();
        }
        System.out.println("O somatorio e: " + soma);
        src.close();
    }
}