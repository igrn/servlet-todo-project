package igrn.todo.board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {
    List<Column> columns;
    List<List<Ticket>> tickets;
    int idCount = 0;

    @BeforeEach
    void initColumns() {
        tickets = new ArrayList<>() {{
            add(createTickets(3));
            add(createTickets(1));
            add(createTickets(2));
        }};
        columns = new ArrayList<>() {{
            add(new Column(0, "Column 0", tickets.get(0)));
            add(new Column(1, "Column 1", tickets.get(1)));
            add(new Column(2, "Column 2", tickets.get(2)));
        }};
    }

    // Метод проверен дебагом; создает список тикетов для одной колонки
    private List<Ticket> createTickets(int size) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int id = idCount + i;
            String title = "Ticket " + id;
            tickets.add(new Ticket(id, title));
        }
        idCount += size;
        return tickets;
    }

    @AfterEach
    void destroyColumns() {
        columns = null;
        tickets = null;
        idCount = 0;
    }

    @Test
    void find() {
        Column expected = columns.get(2);
        Column actual = Column.find(2, columns);
        assertEquals(expected, actual);
    }
}
