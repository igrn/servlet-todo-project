package igrn.todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String query = request.getQueryString();
        boolean hasColumnId = request.getParameter("columnId") != null;
        boolean hasTicketId = request.getParameter("ticketId") != null;

        //Далее форвард на другие сервлеты в зависимости от значений columnId и ticketId
        //TODO: Создать класс Handler, чтобы это всё покомпактнее было??
        RequestDispatcher dispatcher;
        String forwardMapping = "/";
        if (hasColumnId && !hasTicketId) {
            //TODO: Он должен уметь обрабатывать и запросы без id, т.к. возможны запросы GET /columns, в кот. нет columnId
            forwardMapping = "/api/column";
        }
        if (!hasColumnId && hasTicketId) {
            forwardMapping = "/api/ticket";
        }
        if (hasColumnId && hasTicketId) {
            forwardMapping = "/api/column/ticket";
        }
        dispatcher = request.getRequestDispatcher(forwardMapping);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
