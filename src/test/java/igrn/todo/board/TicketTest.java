package igrn.todo.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    List<Ticket> tickets;

    @BeforeEach
    void initTickets() {
        tickets = new ArrayList<>() {{
            add(new Ticket(0, "Ticket 1"));
            add(new Ticket(1, "Ticket 2"));
            add(new Ticket(2, "Ticket 3"));
        }};
    }

    @Test
    void testFind() {
        Ticket expected = new Ticket(1, "Ticket 2");
        Ticket actual = Ticket.find(1, tickets);
        assertEquals(expected, actual);
    }
}