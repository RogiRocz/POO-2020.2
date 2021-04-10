import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class User {
    private String userId;
    protected TreeMap<String, Chat> chats;
    protected ArrayList<Notify> notifies;

    public User(String userId) {
        this.userId = userId;
        this.chats = new TreeMap<>();
        this.notifies = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public Collection<Chat> getChats() {
        return chats.values();
    }

    public void addNotify(Chat chat) {
        for (Notify n : notifies) {
            if (n.getId().equals(chat.getChatId())) {
                n.addCount();
                return;
            }
        }

        Notify notify = new Notify(chat.getChatId());
        notifies.add(notify);
        notify.addCount();
    }

    public List<Notify> getNotifies() {
        return notifies;
    }

    public Notify getNotifyUser(String chatId) {
        for (Notify n : notifies) {
            if (n.getId().equals(chatId)) {
                return n;
            }
        }

        return null;
    }

    public void addChat(Chat chat) {
        this.chats.put(chat.getChatId(), chat);
    }

    public void rmChat(Chat chat) {
        chats.remove(chat.getChatId());
    }

    @Override
    public String toString() {
        return userId;
    }
}
