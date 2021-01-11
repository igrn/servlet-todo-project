package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Columns", value = "/api/columns")
public class ColumnServlet extends HttpServlet implements Printable {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        String idParameter = request.getParameter("id");
        if (idParameter != null) {
            printInfo(response, idParameter);
        } else {
            printList(response);
        }
    }

    @Override
    public void printInfo(HttpServletResponse response, String idParameter) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            int id = Integer.parseInt(idParameter);
            if (id >= 0 && id < Column.getStringList().size()) {
                writer.println(Column.get(id));
            } else {
                throw new RuntimeException("Not a valid id");
            }
        } catch (RuntimeException e) {
            log("RuntimeException in method printInfo()", e);
        }
    }

    @Override
    public void printList(HttpServletResponse response) throws IOException {
        ArrayList<String> columns = Column.getStringList();
        try (PrintWriter writer = response.getWriter()) {
            columns.forEach(writer::println);
        }
    }
}
