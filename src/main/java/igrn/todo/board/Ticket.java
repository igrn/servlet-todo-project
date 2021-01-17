package igrn.todo.board;

public class Ticket extends BoardElement {
    public Ticket(int id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }
}
