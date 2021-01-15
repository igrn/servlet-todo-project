package igrn.todo;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket extends BoardElement {

    public Ticket(int id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }

    //Собирает список из всех тикетов на доске (для удобного поиска по id)
    public static List<Ticket> collectAll(List<Column> columns) {
        return columns.stream().flatMap(column -> column.getTickets().stream())
                               .collect(Collectors.toList());
    }
}
