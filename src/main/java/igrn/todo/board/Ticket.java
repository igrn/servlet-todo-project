package igrn.todo.board;

import java.util.Objects;

public class Ticket extends BoardElement {
    public Ticket(int id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return String.format("Ticket: id = %d, title = %s", id, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket that = (Ticket) o;
        return id == that.getId() && Objects.equals(title, that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
