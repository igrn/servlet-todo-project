package igrn.todo;

import java.util.ArrayList;

//TODO: добавить логику удаления из списка TICKETS
//TODO: продумать получше создание и доступ к объектам (get, конструктор или через метод)
public class Ticket {
    private int id;
    private String title;
    private int columnId; //TODO: должен ли тикет знать, в какой он колонке?
    private static final ArrayList<Ticket> TICKET_POOL = new ArrayList<>() {{
        add(new Ticket(0, "Ticket 1", Column.get(1)));
        add(new Ticket(1, "Ticket 2", Column.get(1)));
        add(new Ticket(2, "Ticket 3", Column.get(1)));
        add(new Ticket(3, "Ticket 4", Column.get(1)));
    }};

    public Ticket(int id, String title, Column column) {
        this.id = id;
        this.title = title;
        columnId = column.getId();
//        TICKETS.add(this); //TODO: пока закомменчено, чтобы не было рекурсии
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

    public static ArrayList<String> getTicketsList() {
        ArrayList<String> strings = new ArrayList<>();
        TICKET_POOL.forEach(ticket -> strings.add(ticket.toString()));
        return strings;
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }
}
