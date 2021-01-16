package igrn.todo;

import java.util.Map;
import java.util.stream.Collectors;

public class Column extends Entity {
    private final Map<Integer, Ticket> tickets;

    public Column(String title, Map<Integer, Ticket> tickets) {
        super(title);
        this.tickets = tickets;
    }

    public Map<Integer, Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(int id, Ticket ticket) {
        tickets.put(id, ticket);
    }

    @Override
    public String toString() {
        String ticketTitles = tickets.values().stream().map(Ticket::getTitle)
                                                       .collect(Collectors.joining(", "));
        return String.format("Column: title = %s, Tickets: %s", title, ticketTitles);
    }
}
