package igrn.todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class RequestHandler {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected RequestDispatcher dispatcher;

    public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    //TODO: вопрос как это будет работать в других методах кроме GET
    public abstract void processRequest(List<String> parameters) throws ServletException, IOException;
}
