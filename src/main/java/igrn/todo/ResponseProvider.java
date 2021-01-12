package igrn.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ResponseProvider {
    default void printResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getQueryString() == null) {
            printList(response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            printInfo(response, id);
        }
    }

    void printInfo(HttpServletResponse response, int id) throws IOException;

    void printList(HttpServletResponse response) throws IOException;
}
