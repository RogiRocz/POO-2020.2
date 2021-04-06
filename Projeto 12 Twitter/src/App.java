import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        Controller sistema = new Controller();

        do {
            e = read.nextLine();
            op = e.split(" ");

            // Falta proteger o codigo contra erros

            switch (op[0]) {
            case "show":
                System.out.println(sistema);
                break;
            case "add":
                String user = op[1];
                sistema.addUser(user);
                break;
            case "follow":
                User u1 = sistema.getUser(op[1]);
                User u2 = sistema.getUser(op[2]);
                try {
                    u1.follow(u2);
                } catch (Exception s) {
                    System.out.println("fail: usuario nao encontrado");
                }
                break;
            case "twittar":
                String userTweet = op[1];
                StringBuilder msg = new StringBuilder();
                for (int i = 2; i < op.length; i++) {
                    if (i + 1 == op.length) {
                        msg.append(op[i]);
                        break;
                    }
                    msg.append(op[i] + " ");
                }

                sistema.sendTweet(userTweet, msg.toString());
                break;
            case "timeline":
                String userTimeline = op[1];

                try {
                    System.out.println(sistema.getUser(userTimeline).getTimeline());
                } catch (Exception s) {
                    System.out.println("fail: usuario nao encontrado");
                }
                break;
            case "like":
                String userLike = op[1];
                int idTweet = Integer.parseInt(op[2]);

                if (userLike == null) {
                    System.out.println("fail: usuario nao encontrado");
                    break;
                }

                try {
                    sistema.getUser(userLike).getTweet(idTweet).like(userLike);
                } catch (Exception s) {
                    System.out.println("fail: tweet nao existe");
                }

                break;
            case "unfollow":
                String userUnfollow1 = op[1];
                String userUnfollow2 = op[2];

                try {
                    sistema.getUser(userUnfollow1).unfollow(userUnfollow2);
                } catch (Exception s) {
                    System.out.println("fail: usuario nao encontrado");
                }
                break;
            case "unread":
                String userUnread = op[1];

                try {
                    System.out.println(sistema.getUser(userUnread).getUnread());
                } catch (Exception s) {
                    System.out.println("fail: usuario nao encontrado");
                }
                break;
            case "end":
                break;
            default:
                break;
            }
        } while (!op[0].equals("end"));

        read.close();
    }
}
