package igrn.todo.api;

import igrn.todo.board.Taskboard;
import igrn.todo.board.Ticket;
import igrn.todo.util.TicketParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.json.Json;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TicketsRoot", value = "/api/tickets")
public class TicketsRootServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        Taskboard taskboard = getTaskboardFromDatabase();
        List<Ticket> tickets = taskboard.getAllTickets();

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            jsonWriter.writeArray(TicketParser.toJsonArray(tickets));
        }
    }
}
