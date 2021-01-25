package igrn.todo.board;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    static List<Ticket> tickets;

    @BeforeAll
    static void setUp() {
        tickets = new ArrayList<>() {{
            add(new Ticket(0, "Ticket 0"));
            add(new Ticket(1, "Ticket 1"));
            add(new Ticket(2, "Ticket 2"));
        }};
    }

    @AfterAll
    static void tearDown() {
        tickets = null;
    }

    @Test
    void testFind() {
        Ticket expected = tickets.get(1);
        Ticket actual = Ticket.find(1, tickets);
        assertEquals(expected, actual);
    }

    @Test
    void testFindIfIdNotFound() {
        assertThrows(RuntimeException.class, () -> Ticket.find(-1, tickets));
    }
}
