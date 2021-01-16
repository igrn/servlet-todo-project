package igrn.todo;

import java.util.Map;

public abstract class Entity {
    protected String title;

    public Entity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static <T extends Entity> Map.Entry<Integer, T> find(int id, Map<Integer, T> map) {
        return map.entrySet().stream()
                             .filter(entry -> entry.getKey() == id)
                             .findFirst()
                             .orElseThrow(RuntimeException::new); //Element with the specified id number doesn't exist
    }
}
