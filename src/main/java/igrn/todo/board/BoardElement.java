package igrn.todo.board;

import java.util.List;

public abstract class BoardElement {
    protected int id;
    protected String title;

    public BoardElement(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static <T extends BoardElement> T find(int id, List<T> elements) {
        return elements.stream().filter(element -> element.getId() == id)
                                .findFirst()
                                .orElseThrow(RuntimeException::new); //Element with the specified id number doesn't exist
    }
}
