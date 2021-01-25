package igrn.todo.board;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TaskboardTest extends ColumnTest {
    static List<Taskboard> taskboards;

    @BeforeAll
    static void setUp() {
        ColumnTest.setUp();
        taskboards = new ArrayList<>() {{
            add(new Taskboard(0, "Taskboard 0", columns));
            add(new Taskboard(1, "Taskboard 1", columns));
            add(new Taskboard(2, "Taskboard 2", columns));
        }};
    }

    @AfterAll
    static void tearDown() {
        ColumnTest.tearDown();
        taskboards = null;
    }

    @Test @Override
    void testFind() {
        Taskboard expected = taskboards.get(1);
        Taskboard actual = Taskboard.find(1, taskboards);
        assertEquals(expected, actual);
    }

    @Test @Override
    void testFindIfIdNotFound() {
        assertThrows(RuntimeException.class, () -> Taskboard.find(-1, taskboards));
    }

    @Test
    void testGetAllTickets() {
        List<Ticket> expected = columns.stream().flatMap(column -> column.getTickets().stream())
                                                .collect(Collectors.toList());
        List<Ticket> actual = taskboards.get(1).getAllTickets();
        assertEquals(expected, actual);
    }
}