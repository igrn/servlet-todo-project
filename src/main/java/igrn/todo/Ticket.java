package igrn.todo;

public class Ticket extends Entity {

    public Ticket(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("Ticket: title = %s", title);
    }
}
