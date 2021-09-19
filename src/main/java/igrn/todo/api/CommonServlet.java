package igrn.todo.api;

import igrn.todo.board.Column;
import igrn.todo.board.Taskboard;
import igrn.todo.util.ColumnParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.json.Json;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CommonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResponseHeaders(response);
    }

    protected Taskboard getTaskboardFromDatabase() {
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/taskboard.json");
        List<Column> columns = ColumnParser.parseArray(Json.createReader(input).readArray());
        return new Taskboard(0, "New Board", columns); // TODO: 17.01.2021 нужно добавить в json поддержку досок
    }

    private void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Server", "tomcat/10.0.10");
    }

    protected void forwardIfEmptyPath(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
}
