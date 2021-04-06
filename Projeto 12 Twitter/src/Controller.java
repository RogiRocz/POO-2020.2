import java.util.TreeMap;

public class Controller {
    private TreeMap<String, User> users;
    private TreeMap<Integer, Tweet> tweets;
    private int nextTwId;

    public Controller() {
        this.users = new TreeMap<>();
        this.tweets = new TreeMap<>();
        this.nextTwId = 0;
    }

    public void sendTweet(String username, String msg) {
        // Tenho q entender como isso se relaciona com os seguidores

        for (User u : users.values()) {
            if (u.getUserName().equals(username)) {
                Tweet t = new Tweet(nextTwId, username, msg);
                tweets.put(nextTwId, t);
                u.sendTweet(t);
                nextTwId++;
            }
        }
    }

    public void addUser(String username) {
        // Verificar se usuario ja existe ou e possivel de adicionar

        User objUser = new User(username);
        users.put(username, objUser);
    }

    public User getUser(String username) {
        for (User u : users.values()) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }

        // Usuario nao encontrado
        return null;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (User u : users.values()) {
            bld.append(u + "\n");
        }
        return bld.toString();
    }

}