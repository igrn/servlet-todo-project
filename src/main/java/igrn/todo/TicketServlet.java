package igrn.todo;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "Tickets", value = "/api/tickets")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/board.json"); //Это можно вызвать только из сервлета
        List<Column> columns = JsonParser.toColumnList(Json.createReader(input).readArray());
        List<Ticket> tickets = Ticket.collectAll(columns);

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            if (request.getQueryString() != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                jsonWriter.writeObject(JsonParser.toJson(Ticket.find(id, tickets)));
            } else {
                jsonWriter.writeArray(JsonParser.toJsonTickets(tickets));
            }
        }
    }
}
