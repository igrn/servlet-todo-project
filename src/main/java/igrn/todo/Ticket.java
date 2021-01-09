package igrn.todo;

import java.util.ArrayList;

//TODO: добавить логику удаления из списка TICKETS
public class Ticket {
    private int id;
    private String title;
    private static final ArrayList<Ticket> TICKETS = new ArrayList<>();

    public Ticket(int id, String title) {
        this.id = id;
        this.title = title;
        TICKETS.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<String> getAllTickets() {
        ArrayList<String> strings = new ArrayList<>();
        TICKETS.forEach(ticket -> strings.add(ticket.toString()));
        return strings;
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }
}
