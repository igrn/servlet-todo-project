package igrn.todo;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// TODO: 14.01.2021 Много дублирования с TicketServlet (вытащить в интерфейс или сервлет-родитель?)
@WebServlet(name = "Columns", value = "/api/columns")
public class ColumnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/taskboard.json"); //Это можно вызвать только из сервлета
        Map<Integer, Column> columns = JsonParser.toColumnMap(Json.createReader(input).readArray());

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            if (request.getQueryString() != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                jsonWriter.writeObject(JsonParser.toJsonColumn(Column.find(id, columns)));
            } else {
                jsonWriter.writeArray(JsonParser.columnsToJsonArray(columns));
            }
        }
    }
}
