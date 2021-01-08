package igrn.todo;

import java.util.ArrayList;

public class Column {
    private int id;
    private String title;
    private ArrayList<Ticket> tickets;

    public Column(int id, String title) {
        this.id = id;
        this.title = title;
        tickets = new ArrayList<>();
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

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
