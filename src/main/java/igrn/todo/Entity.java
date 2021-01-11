package igrn.todo;

import java.util.ArrayList;

public abstract class Entity {
    protected int id;
    protected String title;

    public Entity(int id, String title) {
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
}
