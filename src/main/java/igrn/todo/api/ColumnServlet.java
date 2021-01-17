package igrn.todo.api;

import igrn.todo.board.Column;
import igrn.todo.board.Taskboard;
import igrn.todo.util.ColumnParser;

import javax.json.Json;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Columns", value = "/api/columns/*")
public class ColumnServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        forwardIfEmptyPath(request, response);
        String path = request.getPathInfo().replaceFirst("/", "");
        Taskboard taskboard = getTaskboardFromDatabase();

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            int id = Integer.parseInt(path);
            Column column = Column.find(id, taskboard.getColumns());
            jsonWriter.writeObject(ColumnParser.toJson(column));
        }
    }

    @Override
    protected void forwardIfEmptyPath(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getPathInfo().equals("/")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/api/columns");
            dispatcher.forward(request, response);
        }
    }
}
