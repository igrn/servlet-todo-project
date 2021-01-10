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
        if (request.getParameter("id") == null) {
            printTicketsList(response);
        }

        int id = Integer.parseInt(request.getParameter("id"));
        if (id >= 0 && id < Ticket.getTicketsList().size()) {
            printTicketInfo(response, id);
        }
    }

    private void printTicketsList(HttpServletResponse response) {
        ArrayList<String> tickets = Ticket.getTicketsList();
        try (PrintWriter writer = response.getWriter()) {
            tickets.forEach(writer::println);
        } catch (IOException e) {
            log("Exception: IOException caught in method TicketServlet.printTicketsList()", e);
        }
    }

    private void printTicketInfo(HttpServletResponse response, int id) {
        ArrayList<String> tickets = Ticket.getTicketsList();
        try (PrintWriter writer = response.getWriter()) {
            writer.println(tickets.get(id));
        } catch (IOException e) {
            log("Exception: IOException caught in method TicketServlet.printTicketInfo()", e);
        }
    }
}
