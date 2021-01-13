package igrn.todo;

public class Ticket {
    protected int id;
    protected String title;

    public Ticket(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }
}
