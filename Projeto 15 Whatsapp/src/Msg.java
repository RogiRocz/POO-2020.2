public class Msg {
    private String userId;
    private String text;

    public Msg(String userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    @Override
    public String toString() {
        return userId + ":" + text;
    }
}
