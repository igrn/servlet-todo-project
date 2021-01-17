package igrn.todo.api;

import igrn.todo.board.Taskboard;
import igrn.todo.board.Ticket;
import igrn.todo.util.TicketParser;

import javax.json.Json;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Ticket", value = "/api/tickets/*")
public class TicketServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        forwardIfEmptyPath(request, response);
        String path = request.getPathInfo().replaceFirst("/", "");
        Taskboard taskboard = getTaskboardFromDatabase();
        List<Ticket> tickets = taskboard.getAllTickets();

        try (var jsonWriter = Json.createWriter(response.getWriter())) {
            int id = Integer.parseInt(path);
            Ticket ticket = Ticket.find(id, tickets);
            jsonWriter.writeObject(TicketParser.toJson(ticket));
        }
    }

    @Override
    protected void forwardIfEmptyPath(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getPathInfo().equals("/")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/api/tickets");
            dispatcher.forward(request, response);
        }
    }
}
