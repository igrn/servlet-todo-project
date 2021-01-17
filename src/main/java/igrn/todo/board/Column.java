package igrn.todo.board;

import java.util.List;
import java.util.stream.Collectors;

public class Column extends BoardElement {
    private final List<Ticket> tickets;

    public Column(int id, String title, List<Ticket> tickets) {
        super(id, title);
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
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