package igrn.todo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface TicketProvider extends ResponseProvider {
//    @Override
//    default void printInfo(HttpServletResponse response, int id) throws IOException {
////        try (PrintWriter writer = response.getWriter()) {
////            if (Ticket.POOL.stream().anyMatch(ticket -> ticket.getId() == id)) {
////                writer.println(Ticket.get(id));
////            } else {
////                throw new RuntimeException("A ticket with the specified id number doesn't exist");
////            }
////        }
//    }
//
//    @Override
//    default void printList(HttpServletResponse response) throws IOException {
////        ArrayList<String> tickets = Ticket.getStringList();
////        try (PrintWriter writer = response.getWriter()) {
////            tickets.forEach(writer::println);
////        }
//    }
}
