public class Notify {
    private String chatId;
    private int unreadCount;

    public Notify(String chatId) {
        this.chatId = chatId;
        this.unreadCount = 0;
    }

    public String getId() {
        return this.chatId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void addCount() {
        this.unreadCount++;
    }

    public void rmNotifi() {
        this.unreadCount = 0;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(chatId);
        if (unreadCount != 0) {
            bld.append("(" + unreadCount + ")");
        }
        return bld.toString();
    }

}
