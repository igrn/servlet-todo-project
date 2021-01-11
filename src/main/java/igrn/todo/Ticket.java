package igrn.todo;

import java.util.ArrayList;

//TODO: добавить логику удаления из списка TICKETS
//TODO: продумать получше создание и доступ к объектам (get, конструктор или через метод), static методы
public class Ticket extends Entity {
    private int parentColumnId; //TODO: должен ли тикет знать, в какой он колонке?
    private static final ArrayList<Ticket> TICKETS = new ArrayList<>() {{
        add(new Ticket(0, "Ticket 1", Column.get(1)));
        add(new Ticket(1, "Ticket 2", Column.get(1)));
        add(new Ticket(2, "Ticket 3", Column.get(1)));
        add(new Ticket(3, "Ticket 4", Column.get(1)));
    }};

    public Ticket(int id, String title, Column parentColumn) {
        super(id, title);
        parentColumnId = parentColumn.getId();
//        TICKETS.add(this); //FIXME: 09.01.2021 рекурсия из-за вызова выше
    }

    public static Ticket get(int id) {
        return TICKETS.get(id);
    }

    public static ArrayList<String> getStringList() {
        ArrayList<String> strings = new ArrayList<>();
        TICKETS.forEach(ticket -> strings.add(ticket.toString()));
        return strings;
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }
}
