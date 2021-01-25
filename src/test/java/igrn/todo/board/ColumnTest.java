package igrn.todo.board;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest extends TicketTest {
    static List<Column> columns;

    @BeforeAll
    static void setUp() {
        TicketTest.setUp();
        columns = new ArrayList<>() {{
            add(new Column(0, "Column 0", tickets));
            add(new Column(1, "Column 1", tickets));
            add(new Column(2, "Column 2", tickets));
        }};
    }

    @AfterAll
    static void tearDown() {
        TicketTest.tearDown();
        columns = null;
    }

    @Test @Override
    void testFind() {
        Column expected = columns.get(1);
        Column actual = Column.find(1, columns);
        assertEquals(expected, actual);
    }

    @Test @Override
    void testFindIfIdNotFound() {
        assertThrows(RuntimeException.class, () -> Column.find(-1, columns));
    }
}
