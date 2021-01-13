package igrn.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Column extends Ticket {
    private List<Ticket> tickets = new ArrayList<>();

    public Column(int id, String title) {
        super(id, title);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        String ticketTitles = tickets.stream().map(Ticket::getTitle)
                                              .collect(Collectors.joining(", "));
        return String.format("Column: id = %d, title = %s, Tickets: %s",
                                               id, title, ticketTitles);
    }
}
