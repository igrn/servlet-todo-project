package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

// TODO: 14.01.2021 Много повторов с TicketServlet
@WebServlet(name = "Columns", value = "/api/columns")
public class ColumnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/desk.json"); //Это можно вызвать только из сервлета
        List<Column> columns = JsonManager.readJson(input);

        try (PrintWriter writer = response.getWriter()) {
            if (request.getQueryString() != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                writer.println(findColumn(id, columns));
            } else {
                columns.forEach(writer::println); //переделать в JsonManager?
            }
        }
    }

    private Column findColumn(int id, List<Column> columns) {
        return columns.stream().filter(column -> column.getId() == id)
                               .findFirst()
                               .orElseThrow(() ->
                new RuntimeException("A column with the specified id number doesn't exist"));
    }
}
