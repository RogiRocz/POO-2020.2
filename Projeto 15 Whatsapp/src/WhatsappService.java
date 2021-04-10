import java.util.TreeMap;

class WSExcessoes extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WSExcessoes(String msg) {
        super(msg);
    }
}
/* Criar a classe que lida com as excessoes */

public class WhatsappService {
    private TreeMap<String, Chat> repChats;
    private TreeMap<String, User> repUsers;

    public WhatsappService() {
        this.repChats = new TreeMap<>();
        this.repUsers = new TreeMap<>();
    }

    protected User getUser(String userId) {
        // Ver a possibilidade de usar .get()
        for (User u : repUsers.values()) {
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }

        throw new WSExcessoes("fail: usuario " + userId + "nao encontrado");
    }

    protected Chat getChat(String chatId) {
        for (Chat c : repChats.values()) {
            if (c.getChatId().equals(chatId)) {
                return c;
            }
        }

        throw new WSExcessoes("fail: chat " + chatId + " nao encontrado");
    }

    protected boolean userExiste(String userId) {
        for (User u : repUsers.values()) {
            if (u.getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    protected boolean chatExiste(String chatId) {
        for (Chat c : repChats.values()) {
            if (c.getChatId().equals(chatId)) {
                return true;
            }
        }

        return false;
    }

    public String getChatsUser(String userId) {
        User user = getUser(userId);
        StringBuilder bld = new StringBuilder();

        bld.append("[ ");
        for (Chat c : user.getChats()) {
            // Configurar a exbicao do chat (toString)
            bld.append(c + " ");
        }
        bld.append("]");

        return bld.toString();
    }

    public String getUsersChat(String chatId) {
        Chat chat = getChat(chatId);
        StringBuilder bld = new StringBuilder();

        bld.append("[ ");
        for (User u : chat.getUsers()) {
            // Configurar a exbicao do user (toString)
            bld.append(u + " ");
        }
        bld.append("]");

        return bld.toString();
    }

    public String getNotifyUser(String userId) {
        User user = getUser(userId);
        StringBuilder bld = new StringBuilder();

        bld.append("[ ");
        // Configurar a exbicao do notify (toString)
        for (Notify n : user.getNotifies()) {
            bld.append(n + " ");
        }
        bld.append("]");

        return bld.toString();
    }

    public void addByInvite(String invitesUser, String guestId, String chatId) {
        // Verificar excessoes e regras de negocio

        User usuarioQueConvida = getUser(invitesUser);
        User usuarioConvidado = getUser(guestId);

        // Verifica se o usuario ta no chat
        if (!getChat(chatId).hasUser(invitesUser)) {
            throw new WSExcessoes("fail: user " + invitesUser + " nao esta no chat " + chatId);
        }

        for (Chat c : usuarioQueConvida.getChats()) {
            if (c.equals(getChat(chatId))) {
                c.addByInvite(usuarioConvidado);
            }
        }
    }

    public void createUser(String userId) {
        repUsers.put(userId, new User(userId));
    }

    public void createChat(String userId, String chatId) {
        // Analisar direito o que esta sendo feito aqui
        if (chatExiste(chatId)) {
            throw new WSExcessoes("fail: chat " + chatId + " ja existe");
        }

        Chat newChat = new Chat(chatId);
        User user = getUser(userId);
        repChats.put(chatId, newChat);
        newChat.addUserChat(user);
        user.addChat(newChat);
    }

    public String allUsers() {
        StringBuilder bld = new StringBuilder();

        bld.append("[ ");
        for (User u : repUsers.values()) {
            bld.append(u + " ");
        }
        bld.append("]");

        return bld.toString();
    }

    public String allChats() {
        StringBuilder bld = new StringBuilder();

        bld.append("[ ");
        for (Chat c : repChats.values()) {
            bld.append(c + " ");
        }
        bld.append("]");

        return bld.toString();
    }

    public void rmUserChat(String userId, String chatId) {
        User user = getUser(userId);

        for (Chat c : user.getChats()) {
            if (c.equals(getChat(chatId))) {
                c.rmUserChat(user);
                user.rmChat(c);
            }
        }

    }

    public void sendMessage(String userId, String chatId, String message) {
        User sender = getUser(userId);

        getChat(chatId).sendMessage(sender, message);
    }

    public String readMessageUserChat(String userId, String chatId) {
        if (!userExiste(userId)) {
            getUser(userId);
        }

        StringBuilder bld = new StringBuilder();

        for (Msg m : getChat(chatId).getMsgs(userId)) {
            bld.append("[");
            bld.append(m);
            bld.append("]\n");
        }

        getUser(userId).getNotifyUser(chatId).rmNotifi();

        return bld.toString();
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("Users: " + allUsers());
        bld.append("\n");
        bld.append("Chats: " + allChats());
        return bld.toString();
    }

}
