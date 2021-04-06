import java.util.Set;
import java.util.TreeMap;

public class User {
    private String username;
    private TreeMap<String, User> followers;
    private TreeMap<String, User> following;
    private TreeMap<Integer, Tweet> timeline;
    private int unReadCount;

    public User(String username) {
        this.username = username;
        this.followers = new TreeMap<>();
        this.following = new TreeMap<>();
        this.timeline = new TreeMap<>();
        this.unReadCount = 0;
    }

    public String getUserName() {
        return this.username;
    }

    public void follow(User user) {
        this.following.put(user.getUserName(), user);
        user.followers.put(this.username, this);
    }

    public void unfollow(String username) {

        // Creio que não precisa desse for

        for (User u : following.values()) {
            if (u.getUserName().equals(username)) {
                following.remove(username);
                u.followers.remove(this.username);
            }
        }

    }

    public Tweet getTweet(int idTw) {
        return timeline.get(idTw);
    }

    public void sendTweet(Tweet tweet) {
        timeline.put(tweet.getIdTw(), tweet);
        for (User u : followers.values()) {
            u.sendTweet(tweet);
            u.unReadCount++;
        }
    }

    public String getUnread() {
        StringBuilder bld = new StringBuilder();
        for (int i = timeline.size() - unReadCount; i < timeline.size(); i++) {
            bld.append(timeline.get(i) + "\n");
        }

        unReadCount = 0;

        return bld.toString();
    }

    public String getTimeline() {
        // Creio que seja assim
        StringBuilder bld = new StringBuilder();
        for (Tweet t : timeline.values()) {
            bld.append(t + "\n");
        }
        return bld.toString();
    }

    public String formatText(Set<String> keys) {
        StringBuilder fmt = new StringBuilder();
        fmt.append("[ ");
        for (String key : keys) {
            fmt.append(key + " ");
        }
        fmt.append("]\n");
        return fmt.toString();
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(username + "\n");
        // Testar se nao precisa colocar \n
        bld.append("\t seguindo \t" + formatText(following.keySet()));
        bld.append("\t seguidores \t" + formatText(followers.keySet()));
        return bld.toString();
    }
}