package igrn.todo.api;

import igrn.todo.board.Taskboard;
import igrn.todo.board.Ticket;
import igrn.todo.util.TicketParser;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Tickets", value = "/api/tickets/*")
public class TicketServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        Taskboard taskboard = getTaskboardFromDatabase();
        List<Ticket> tickets = taskboard.getAllTickets();

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            int id = Integer.parseInt(request.getPathInfo().replace("/", ""));
            Ticket ticket = Ticket.find(id, tickets);
            jsonWriter.writeObject(TicketParser.toJson(ticket));
        }
    }
}
