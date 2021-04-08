import java.util.List;

public interface IChat {
    String getId();

    void sendMessage(Mensagem msg);

    void addMessage(Mensagem msg);

    void clear();

    List<Mensagem> getInbox();
}