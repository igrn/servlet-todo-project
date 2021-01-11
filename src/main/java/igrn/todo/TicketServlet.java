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
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            printTicketInfo(response, id);
        } else {
            printTicketsList(response);
        }
    }

    private void printTicketsList(HttpServletResponse response) throws IOException {
        ArrayList<String> tickets = Ticket.getStringList();
        try (PrintWriter writer = response.getWriter()) {
            tickets.forEach(writer::println);
        }
    }

    private void printTicketInfo(HttpServletResponse response, int id) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            if (id >= 0 && id < Ticket.getStringList().size()) {
                writer.println(Ticket.get(id));
            } else {
                throw new RuntimeException("Not a valid id");
            }
        } catch (RuntimeException e) {
            log("Exception: RuntimeException in method TicketServlet.printTicketInfo()", e);
        }
    }
}
