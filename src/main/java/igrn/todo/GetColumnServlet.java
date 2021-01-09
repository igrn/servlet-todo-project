package igrn.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//@WebServlet(name = "GetColumnServlet", value = "/api/get/column")
public class GetColumnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //если передан id, то показать инфу по колонке с этим id; иначе инфу по всем колонкам
        if (request.getParameter("columnId") == null) {
            ArrayList<String> columns = Column.getAllColumns();
            PrintWriter writer = response.getWriter();
            writer.println(columns);
            writer.flush();
        } else {
            //TODO: код, если передан id (может быть поменяю местами блоки)
        }
    }
}
