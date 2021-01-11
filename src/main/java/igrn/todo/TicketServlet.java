package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Tickets", value = "/api/tickets")
public class TicketServlet extends HttpServlet implements Printable {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        printResponse(request, response);
    }

    @Override
    public void printInfo(HttpServletResponse response, int id) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            if (Ticket.POOL.stream().anyMatch(ticket -> ticket.getId() == id)) {
                writer.println(Ticket.get(id));
            } else {
                throw new RuntimeException("Not a valid id");
            }
        } catch (RuntimeException e) {
            log("RuntimeException in method printInfo()", e);
        }
    }

    @Override
    public void printList(HttpServletResponse response) throws IOException {
        ArrayList<String> tickets = Ticket.getStringList();
        try (PrintWriter writer = response.getWriter()) {
            tickets.forEach(writer::println);
        }
    }
}
