package igrn.todo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface ColumnProvider extends ResponseProvider {
    @Override
    default void printInfo(HttpServletResponse response, int id) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            if (Column.POOL.stream().anyMatch(column -> column.getId() == id)) {
                writer.println(Column.get(id));
            } else {
                throw new RuntimeException("A column with the specified id number doesn't exist");
            }
        }
    }

    @Override
    default void printList(HttpServletResponse response) throws IOException {
        ArrayList<String> columns = Column.getStringList();
        try (PrintWriter writer = response.getWriter()) {
            columns.forEach(writer::println);
        }
    }
}
