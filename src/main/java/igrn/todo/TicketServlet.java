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
import java.util.stream.Collectors;

@WebServlet(name = "Tickets", value = "/api/tickets")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/board.json"); //Это можно вызвать только из сервлета
        List<Column> columns = JsonManager.readJson(input);

        try (PrintWriter writer = response.getWriter()) {
            if (request.getQueryString() != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                writer.println(findTicket(id, columns));
            } else {
                columns.forEach(column -> column.getTickets().forEach(writer::println));
            }
        }
    }

    private Ticket findTicket(int id, List<Column> columns) {
        List <Ticket> tickets = columns.stream().flatMap(column -> column.getTickets().stream())
                                                .collect(Collectors.toList());

        return tickets.stream().filter(ticket -> ticket.getId() == id)
                               .findFirst()
                               .orElseThrow(() ->
                new RuntimeException("A ticket with the specified id number doesn't exist"));
    }
}
