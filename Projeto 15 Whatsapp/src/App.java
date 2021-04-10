import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String op[];
        String e;

        Scanner read = new Scanner(System.in);
        WhatsappService ws = new WhatsappService();

        do {
            e = read.nextLine();
            op = e.split(" ");

            try {
                switch (op[0]) {
                case "show":
                    System.out.println(ws);
                    break;
                case "add":
                    String userAdd = op[1];
                    ws.createUser(userAdd);
                    break;
                case "allUsers":
                    System.out.println(ws.allUsers());
                    break;
                case "allChats":
                    System.out.println(ws.allChats());
                    break;
                case "newChat":
                    String userId = op[1];
                    String chatId = op[2];
                    ws.createChat(userId, chatId);
                    break;
                case "chats":
                    String userIdChats = op[1];
                    System.out.println(ws.getChatsUser(userIdChats));
                    break;
                case "invite":
                    String remententeInv = op[1];
                    String destinatarioInv = op[2];
                    String chatIdInv = op[3];
                    ws.addByInvite(remententeInv, destinatarioInv, chatIdInv);
                    break;
                case "users":
                    String chatIdUsers = op[1];
                    System.out.println(ws.getUsersChat(chatIdUsers));
                    break;
                case "leave":
                    String userIdLeave = op[1];
                    String chatIdLeave = op[2];
                    ws.rmUserChat(userIdLeave, chatIdLeave);
                    break;
                case "zap":
                    String userIdZap = op[1];
                    String chatIdZap = op[2];
                    StringBuilder msg = new StringBuilder();
                    for (int i = 3; i < op.length; i++) {
                        msg.append(op[i] + " ");
                    }
                    ws.sendMessage(userIdZap, chatIdZap, msg.toString());
                    break;
                case "notify":
                    String userIdNotifiy = op[1];
                    System.out.println(ws.getNotifyUser(userIdNotifiy));
                    break;
                case "ler":
                    String userIdLer = op[1];
                    String chatIdLer = op[2];
                    System.out.println(ws.readMessageUserChat(userIdLer, chatIdLer));
                    break;
                case "end":
                    break;
                default:
                    System.out.println("fail: comando invalido");
                    break;
                }
            } catch (Exception s) {
                System.out.println(s.getMessage());
            }

        } while (!op[0].equals("end"));

        read.close();
    }
}