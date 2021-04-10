import java.util.ArrayList;
import java.util.List;

public class Inbox {
    private User user;
    private ArrayList<Msg> msgs;

    public Inbox(User user) {
        this.user = user;
        this.msgs = new ArrayList<>();
    }

    public void addMsg(Msg msg) {
        msgs.add(msg);
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    public void clearMsgs() {
        msgs.clear();
    }

    public User getUser() {
        return this.user;
    }
}
