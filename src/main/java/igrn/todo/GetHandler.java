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

    //TODO: метод длинноват, надо уменьшить
    //Предполагается, что параметры отправлены в правильном порядке
    public void processRequest(List<String> parameters) throws ServletException, IOException {
        boolean hasColumnId = parameters.get(0).equals("columnId");
        boolean hasTicketId = parameters.get(1).equals("ticketId");

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
