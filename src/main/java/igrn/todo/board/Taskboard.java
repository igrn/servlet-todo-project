package igrn.todo.board;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// FIXME: 16.01.2021 Класс недостаточно используется и не там создается
public class Taskboard extends BoardElement {
    private final List<Column> columns;

    public Taskboard(int id, String title, List<Column> columns) {
        super(id, title);
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    //Собирает список из всех тикетов на доске (для удобного поиска по id)
    public List<Ticket> getAllTickets() {
        return columns.stream().flatMap(column -> column.getTickets().stream())
                               .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Taskboard: id = %d, title = %s", id, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taskboard taskboard = (Taskboard) o;
        return id == taskboard.getId() && Objects.equals(title, taskboard.getTitle())
                && columns.hashCode() == taskboard.getColumns().hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, columns);
    }
}
