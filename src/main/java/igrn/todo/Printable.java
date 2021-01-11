package igrn.todo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Printable {
    void printInfo(HttpServletResponse response, String idParameter) throws IOException;

    void printList(HttpServletResponse response) throws IOException;
}
