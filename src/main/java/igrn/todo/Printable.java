package igrn.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Printable {
    default void printResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getQueryString().isEmpty()) {
            printList(response);
        } else {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                printInfo(response, id);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // FIXME: 11.01.2021 нет норм логов
            }
        }
    }

    void printInfo(HttpServletResponse response, int id) throws IOException;

    void printList(HttpServletResponse response) throws IOException;
}
