package igrn.todo.board;

import org.junit.jupiter.api.*;

import java.util.List;

class TaskboardTest extends ColumnTest {
    static Taskboard taskboard;
    static List<Ticket> flatTickets;

    @BeforeAll
    static void setUp() {
        ColumnTest.setUp();
        taskboard = new Taskboard(0, "Taskboard 0", columns);
    }

    @AfterAll
    static void tearDown() {
        ColumnTest.tearDown();
        taskboard = null;
    }

    @Test
    void testGetAllTickets() {

    }
}