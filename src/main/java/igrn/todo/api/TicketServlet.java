package igrn.todo.api;

import igrn.todo.board.Taskboard;
import igrn.todo.board.Ticket;
import igrn.todo.util.TicketParser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.json.Json;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Tickets", value = "/api/tickets/*")
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
            Ticket ticket = Ticket.find(id, tickets); // TODO: 26.01.21 Попробовать реализовать через метод get у Map
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
