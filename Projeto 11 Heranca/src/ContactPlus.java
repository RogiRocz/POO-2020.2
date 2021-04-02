public class ContactPlus extends Contact {
    private boolean starred;

    public ContactPlus(String nameContact) {
        super(nameContact);
        this.starred = false;
    }

    public boolean getStarred() {
        return this.starred;
    }

    public void setBookmark(boolean value) {
        this.starred = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}