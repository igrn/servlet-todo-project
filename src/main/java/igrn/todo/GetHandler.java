package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetHandler extends RequestHandler {

    public GetHandler(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    //TODO: метод длинноват, надо уменьшить
    public void execute(String... params) throws ServletException, IOException {
        boolean hasColumnId = request.getParameter(params[0]) != null;
        boolean hasTicketId = request.getParameter(params[1]) != null;
        String mapping = "/";

        if (hasColumnId && !hasTicketId) {
            mapping = "/api/column";
        } else if (!hasColumnId && hasTicketId) {
            mapping = "/api/ticket";
        }
        if (hasColumnId && hasTicketId) {
            mapping = "/api/column/ticket";
        }

        dispatcher = request.getRequestDispatcher(mapping);
        dispatcher.forward(request, response);
    }
}
