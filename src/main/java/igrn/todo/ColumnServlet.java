package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Columns", value = "/api/columns")
public class ColumnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        //Если id не передан, то выдать список со всеми колонками
        if (request.getParameter("id") == null) {
            ArrayList<String> columns = Column.getStringList();
            PrintWriter writer = response.getWriter();
            writer.println(columns);
            writer.flush();
        }

        //TODO: код, если передан id (проверить на корректность id)

    }
}
