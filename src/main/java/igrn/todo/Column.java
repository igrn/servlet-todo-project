package igrn.todo;

import java.util.ArrayList;
import java.util.stream.Collectors;

//TODO: добавить логику удаления из списка COLUMNS
//TODO: продумать получше создание и доступ к объектам (get, конструктор или через метод)
public class Column extends Entity {
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private static final ArrayList<Column> COLUMNS = new ArrayList<>() {{
        add(new Column(1, "Column 1"));
        add(new Column(2, "Column 2"));
        add(new Column(3, "Column 3"));
    }};

    public Column(int id, String title) {
        super(id, title);
//        COLUMNS.add(this); // FIXME: 09.01.2021 рекурсия из-за вызова выше
    }

    public static Column get(int id) {
        return COLUMNS.get(id);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static ArrayList<String> getStringList() {
        ArrayList<String> strings = new ArrayList<>();
        COLUMNS.forEach(column -> strings.add(column.toString()));
        return strings;
    }

    @Override
    public String toString() {
        //TODO: возможно, лучше просто выводить количество тикетов в колонке
        String ticketTitles = tickets.stream()
                .map(ticket -> "{\"" + ticket.getTitle() + "\"}, ")
                .collect(Collectors.joining());
        return String.format("Column: id = %d, title = %s, Tickets: %s", id, title, ticketTitles);
    }
}
