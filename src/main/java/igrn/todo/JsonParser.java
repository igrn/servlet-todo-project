package igrn.todo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 13.01.2021 Отработать кейс PUT тикета (переделать в Set или Map?)
// FIXME: 14.01.2021 Методы практически полностью совпадают (не вижу способов упростить)
public interface JsonParser {
    // Конвертирует json-массив колонок в java-список колонок (содержит тикеты)
    static List<Column> toColumnList(JsonArray columnArray) {
        List<JsonObject> columns = columnArray.getValuesAs(JsonObject.class);
        return columns.stream().map(column -> {
                                        int id = column.getInt("id");
                                        String title = column.getString("title");
                                        List<Ticket> tickets = toTicketList(column.getJsonArray("tickets"));
                                        return new Column(id, title, tickets);
                               }).collect(Collectors.toList());
    }

    // Конвертирует json-массив тикетов в java-список тикетов (не обязательно одной колонки)
    static List<Ticket> toTicketList(JsonArray ticketArray) {
        List<JsonObject> tickets = ticketArray.getValuesAs(JsonObject.class);
        return tickets.stream().map(ticket -> {
                                        int id = ticket.getInt("id");
                                        String title = ticket.getString("title");
                                        return new Ticket(id, title);
                               }).collect(Collectors.toList());
    }

    // Конвертирует список колонок обратно в json-массив (так же содержит тикеты)
    static JsonArray toJsonColumns(List<Column> columns) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        columns.stream().map(column -> Json.createObjectBuilder()
                                           .add("id", column.getId())
                                           .add("title", column.getTitle())
                                           .add("tickets", toJsonTickets(column.getTickets())))
                        .forEach(builder::add);
        return builder.build();
    }

    // Конвертирует список тикетов обратно в json-массив (не обязательно одной колонки)
    static JsonArray toJsonTickets(List<Ticket> tickets) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        tickets.stream().map(ticket -> Json.createObjectBuilder()
                                           .add("id", ticket.getId())
                                           .add("title", ticket.getTitle()))
                        .forEach(builder::add);
        return builder.build();
    }

    // Конвертирует 1 java-колонку в объект json, содержащий тикеты
    static JsonObject toJson(Column column) {
        return Json.createObjectBuilder().add("id", column.getId())
                                         .add("title", column.getTitle())
                                         .add("tickets", toJsonTickets(column.getTickets()))
                                         .build();
    }

    // Конвертирует 1 java-тикет в объект json
    static JsonObject toJson(Ticket ticket) {
        return Json.createObjectBuilder().add("id", ticket.getId())
                                         .add("title", ticket.getTitle())
                                         .build();
    }
}
