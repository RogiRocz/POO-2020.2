import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class Chat {
    private String chatId;
    protected TreeMap<String, Inbox> inboxes;
    protected TreeMap<String, User> users;

    public Chat(String chatId) {
        this.chatId = chatId;
        this.inboxes = new TreeMap<>();
        this.users = new TreeMap<>();
    }

    public String getChatId() {
        return chatId;
    }

    public List<Msg> getMsgs(String userId) {
        User user = users.get(userId);

        if (user.getNotifyUser(this.chatId).getUnreadCount() == 0) {
            return new ArrayList<>();
        }

        ArrayList<Msg> msgs = new ArrayList<>();

        for (Inbox i : inboxes.values()) {
            if (!i.getUser().getUserId().equals(userId)) {
                msgs.addAll(i.getMsgs());
            }
        }

        return msgs;
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public void sendMessage(User sender, String message) {
        for (Inbox i : inboxes.values()) {
            User user = i.getUser();
            if (user.getUserId().equals(sender.getUserId())) {
                i.addMsg(new Msg(sender.getUserId(), message));
                notifyUsersChat(sender.getUserId());
                return;
            }
        }

        Inbox inbox = new Inbox(sender);
        inbox.addMsg(new Msg(sender.getUserId(), message));

        inboxes.put(sender.getUserId(), inbox);
        notifyUsersChat(sender.getUserId());
    }

    public Inbox getInboxUser(String userId) {
        for (Inbox i : inboxes.values()) {
            if (i.getUser().getUserId().equals(userId)) {
                return i;
            }
        }

        throw new WSExcessoes("fail: usuario " + userId + " nao encontrado");
    }

    private void notifyUsersChat(String userId) {
        for (User u : users.values()) {
            if (!u.getUserId().equals(userId)) {
                u.addNotify(this);
            }
        }
    }

    public int unreadCount(String userId) {
        User user = users.get(userId);
        Notify notify = user.getNotifyUser(this.chatId);

        return notify.getUnreadCount();
    }

    public boolean hasUser(String userId) {
        for (User u : users.values()) {
            if (u.getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    public void addUserChat(User user) {
        users.put(user.getUserId(), user);
    }

    public void rmUserChat(User user) {
        users.remove(user.getUserId());
    }

    public void addByInvite(User invited) {
        users.put(invited.getUserId(), invited);
        invited.addChat(this);
    }

    @Override
    public String toString() {
        return chatId;
    }
}
