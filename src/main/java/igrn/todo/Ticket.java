package igrn.todo;

//TODO: static метод и/или переменная для отображения всех тикетов во всех колонках?
public class Ticket {
    private int id;
    private String title;

    public Ticket(int id, String title) {
        this.id = id;
        this.title = title;
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
}
