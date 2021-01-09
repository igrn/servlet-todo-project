package igrn.todo;

import java.util.ArrayList;
import java.util.stream.Collectors;

//TODO: добавить логику удаления из списка COLUMNS
public class Column {
    private int id;
    private String title;
    private final ArrayList<Ticket> tickets = new ArrayList<>();;
    private static final ArrayList<Column> COLUMNS = new ArrayList<>();

    public Column(int id, String title) {
        this.id = id;
        this.title = title;
        COLUMNS.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static ArrayList<String> getAllColumns() {
        ArrayList<String> strings = new ArrayList<>();
        COLUMNS.forEach(column -> strings.add(column.toString()));
        return strings;
    }

    @Override
    public String toString() {
        //TODO: возможно, лучше просто выводить количество тикетов в колонке
        String ticketTitles = tickets.stream().map(ticket -> ticket.getTitle() + ", ")
                                              .collect(Collectors.joining());
        return String.format("Column: id = %d, title = %s", id, title)
                + ", {Tickets: " + ticketTitles + "}";
    }
}
