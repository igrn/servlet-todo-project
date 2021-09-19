package igrn.todo.util;

import igrn.todo.board.Ticket;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketParserTest {
    static List<Ticket> tickets;
    static JsonArray jsonTickets;

    @BeforeAll
    static void setUp() {
        tickets = new ArrayList<>() {{
            add(new Ticket(0, "Ticket 0"));
            add(new Ticket(1, "Ticket 1"));
            add(new Ticket(2, "Ticket 2"));
        }};
        jsonTickets = Json.createArrayBuilder()
                          .add(createJsonTicket(0, "Ticket 0"))
                          .add(createJsonTicket(1, "Ticket 1"))
                          .add(createJsonTicket(2, "Ticket 2"))
                          .build();
    }

    private static JsonObject createJsonTicket(int id, String title) {
        return Json.createObjectBuilder()
                   .add("id", id)
                   .add("title", title)
                   .build();
    }

    @AfterAll
    static void tearDown() {
        tickets = null;
        jsonTickets = null;
    }

    @Test
    void testParseArray() {
        List<Ticket> expected = tickets;
        List<Ticket> actual = TicketParser.parseArray(jsonTickets);
        assertEquals(expected, actual);
    }

    @Test
    void testParse() {
        Ticket expected = tickets.get(1);
        Ticket actual = TicketParser.parse(jsonTickets.getJsonObject(1));
        assertEquals(expected, actual);
    }

    @Test
    void testToJsonArray() {
        JsonArray expected = jsonTickets;
        JsonArray actual = TicketParser.toJsonArray(tickets);
        assertEquals(expected, actual);
    }

    @Test
    void testToJson() {
        JsonObject expected = jsonTickets.getJsonObject(1);
        JsonObject actual = TicketParser.toJson(tickets.get(1));
        assertEquals(expected, actual);
    }
}