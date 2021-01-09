package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetHandler extends RequestHandler {
    public GetHandler(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void processRequest(List<String> parameters) throws ServletException, IOException {
        boolean hasColumnId = parameters.stream().anyMatch(s -> s.equals("columnId"));
        boolean hasTicketId = parameters.stream().anyMatch(s -> s.equals("ticketId"));

        String mapping = "/api/get/column";
        if (hasColumnId) {
            if (hasTicketId) {
                mapping = "/api/get/ticket";
            } else {
                mapping = "/api/get/column";
            }
        }

        dispatcher = request.getRequestDispatcher(mapping);
        dispatcher.forward(request, response);
    }
}
