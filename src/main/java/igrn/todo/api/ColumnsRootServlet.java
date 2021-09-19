package igrn.todo.api;

import igrn.todo.board.Column;
import igrn.todo.board.Taskboard;
import igrn.todo.util.ColumnParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.json.Json;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ColumnsRoot", value = "/api/columns")
public class ColumnsRootServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        Taskboard taskboard = getTaskboardFromDatabase();
        List<Column> columns = taskboard.getColumns();

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            jsonWriter.writeArray(ColumnParser.toJsonArray(columns));
        }
    }
}
