package igrn.todo.util;

import igrn.todo.board.Ticket;

import javax.json.*;
import java.util.List;
import java.util.stream.Collectors;

public interface TicketParser {
    // Парсит список Ticket из json-массива
    static List<Ticket> parseArray(JsonArray tickets) {
        return tickets.getValuesAs(JsonObject.class)
                      .stream()
                      .map(TicketParser::parse)
                      .collect(Collectors.toList());
    }

    // Парсит один Ticket из объекта JsonObject
    static Ticket parse(JsonObject ticket) {
        int id = ticket.getInt("id");
        String title = ticket.getString("title");
        return new Ticket(id, title);
    }

    // Конвертирует список Ticket обратно в json-массив
    static JsonArray toJsonArray(List<Ticket> tickets) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        tickets.stream().map(TicketParser::makeJson).forEach(builder::add);
        return builder.build();
    }

    // Конвертирует один Ticket обратно в объект JsonObject
    static JsonObject toJson(Ticket ticket) {
        return makeJson(ticket).build();
    }

    private static JsonObjectBuilder makeJson(Ticket ticket) {
        return Json.createObjectBuilder()
                   .add("id", ticket.getId())
                   .add("title", ticket.getTitle());
    }
}
