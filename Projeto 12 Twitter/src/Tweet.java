import java.util.TreeSet;

public class Tweet {
    private int idTw;
    private String username;
    private String msg;
    private TreeSet<String> likes;

    public Tweet(int idTw, String username, String msg) {
        this.idTw = idTw;
        this.username = username;
        this.msg = msg;
        this.likes = new TreeSet<>();
    }

    public int getIdTw() {
        return idTw;
    }

    public String getUsername() {
        return username;
    }

    public String getMsg() {
        return msg;
    }

    public void like(String username) {
        likes.add(username);
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(idTw + ":" + username + "( " + msg + " )");

        if (!likes.isEmpty()) {
            bld.append("[ ");
            for (String like : likes) {
                bld.append(like + " ");
            }
            bld.append("]");
        }

        return bld.toString();
    }
}