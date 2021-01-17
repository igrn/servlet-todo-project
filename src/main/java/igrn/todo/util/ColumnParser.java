package igrn.todo.util;

import igrn.todo.board.Column;
import igrn.todo.board.Ticket;

import javax.json.*;
import java.util.List;
import java.util.stream.Collectors;

public interface ColumnParser {
    // Парсит список Column из json-массива (вместе с тикетами)
    static List<Column> parseArray(JsonArray columns) {
        return columns.getValuesAs(JsonObject.class)
                      .stream()
                      .map(ColumnParser::parse)
                      .collect(Collectors.toList());
    }

    // Парсит один Column из объекта JsonObject
    static Column parse(JsonObject column) {
        int id = Integer.parseInt(column.getString("id"));
        String title = column.getString("title");
        List<Ticket> tickets = TicketParser.parseArray(column.getJsonArray("tickets"));
        return new Column(id, title, tickets);
    }

    // Конвертирует список Column обратно в json-массив (вместе с тикетами)
    static JsonArray toJsonArray(List<Column> columns) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        columns.stream().map(ColumnParser::makeJson).forEach(builder::add);
        return builder.build();
    }

    // Конвертирует один Column обратно в объект JsonObject
    static JsonObject toJson(Column column) {
        return makeJson(column).build();
    }

    private static JsonObjectBuilder makeJson(Column column) {
        return Json.createObjectBuilder()
                   .add("id", column.getId())
                   .add("title", column.getTitle())
                   .add("tickets", TicketParser.toJsonArray(column.getTickets()));
    }
}
