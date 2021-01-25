package igrn.todo.board;

import org.junit.jupiter.api.AfterEach;
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
            add(new Ticket(0, "Ticket 0"));
            add(new Ticket(1, "Ticket 1"));
            add(new Ticket(2, "Ticket 2"));
        }};
    }

    @AfterEach
    void destroyTickets() {
        tickets = null;
    }

    @Test
    void testFind() {
        Ticket expected = tickets.get(1);
        Ticket actual = Ticket.find(1, tickets);
        assertEquals(expected, actual);
    }
}
