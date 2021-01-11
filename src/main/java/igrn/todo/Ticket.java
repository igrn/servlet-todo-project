package igrn.todo;

import java.util.ArrayList;

//TODO: добавить логику удаления из списка TICKETS
//TODO: продумать получше создание и доступ к объектам (get, конструктор или через метод), static методы
public class Ticket extends Entity {
    public static final ArrayList<Ticket> POOL = new ArrayList<>() {{
        add(new Ticket(0, "Ticket 1", Column.get(1)));
        add(new Ticket(1, "Ticket 2", Column.get(1)));
        add(new Ticket(2, "Ticket 3", Column.get(1)));
        add(new Ticket(3, "Ticket 4", Column.get(1)));
    }};

    public Ticket(int id, String title, Column column) {
        super(id, title);
        column.addTicket(this);
//        TICKETS.add(this); //FIXME: 09.01.2021 рекурсия из-за вызова выше
    }

    public static Ticket get(int id) {
        return POOL.get(id);
    }

    public static ArrayList<String> getStringList() {
        ArrayList<String> strings = new ArrayList<>();
        POOL.forEach(ticket -> strings.add(ticket.toString()));
        return strings;
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }
}
