import java.util.Scanner;

public class Jogo {
    private Pet tamagotchi;

    public Pet getTamagotchi() {
        return tamagotchi;
    }

    public void setTamagotchi(Pet tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Jogo game = new Jogo();

        do {
            e = read.nextLine();
            op = e.split(" ");

            switch (op[0]) {
                case "init":
                    int energyMax = Integer.parseInt(op[1]);
                    int hungryMax = Integer.parseInt(op[2]);
                    int cleanMax = Integer.parseInt(op[3]);
                    Pet pet = new Pet(energyMax, hungryMax, cleanMax);
                    game.setTamagotchi(pet);
                    break;
                case "show":
                    System.out.println(game.getTamagotchi());
                    break;
                case "play":
                    game.getTamagotchi().play();
                    break;
                case "clean":
                    game.getTamagotchi().shower();
                    break;
                case "eat":
                    game.getTamagotchi().eat();
                    break;
                case "sleep":
                    game.getTamagotchi().sleep();
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
