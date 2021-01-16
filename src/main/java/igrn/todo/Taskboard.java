package igrn.todo;

import java.util.Map;
import java.util.stream.Collectors;

// FIXME: 16.01.2021 Класс недостаточно используется и не там создается
public class Taskboard extends Entity {
    private final Map<Integer, Column> columns;

    public Taskboard(String title, Map<Integer, Column> columns) {
        super(title);
        this.columns = columns;
    }

    public Map<Integer, Column> getColumns() {
        return columns;
    }

    //Собирает мапу из всех тикетов на доске (для удобного поиска по id)
    public Map<Integer, Ticket> getAllTickets() {
        return columns.values().stream()
                .flatMap(column -> column.getTickets().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
