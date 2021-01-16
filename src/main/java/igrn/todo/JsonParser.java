package igrn.todo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// FIXME: 14.01.2021 Методы практически полностью совпадают (не вижу способов упростить)
public interface JsonParser {
    // Конвертирует json-массив колонок в java-мапу колонок (содержит тикеты)
    static Map<Integer, Column> toColumnMap(JsonArray columnArray) {
        List<JsonObject> columns = columnArray.getValuesAs(JsonObject.class);
        return columns.stream().collect(Collectors.toMap(
                column -> column.getInt("id"),
                column -> {
                    var tickets = toTicketMap(column.getJsonArray("tickets"));
                    return new Column(column.getString("title"), tickets);
                }));
    }

    // Конвертирует json-массив тикетов в java-мапу тикетов (не обязательно одной колонки)
    static Map<Integer, Ticket> toTicketMap(JsonArray ticketArray) {
        List<JsonObject> tickets = ticketArray.getValuesAs(JsonObject.class);
        return tickets.stream().collect(Collectors.toMap(
                ticket -> ticket.getInt("id"),
                ticket -> new Ticket(ticket.getString("title"))));
    }

    // Конвертирует мапу колонок обратно в json-массив (так же содержит тикеты)
    static JsonArray columnsToJsonArray(Map<Integer, Column> columns) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (var entry : columns.entrySet()) {
            builder.add(Json.createObjectBuilder()
                            .add("id", entry.getKey())
                            .add("title", entry.getValue().getTitle())
                            .add("tickets", ticketsToJsonArray(entry.getValue().getTickets())));
        }
        return builder.build();
    }

    // Конвертирует мапу тикетов обратно в json-массив (не обязательно одной колонки)
    static JsonArray ticketsToJsonArray(Map<Integer, Ticket> tickets) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (var entry : tickets.entrySet()) {
            builder.add(Json.createObjectBuilder()
                            .add("id", entry.getKey())
                            .add("title", entry.getValue().getTitle()));
        }
        return builder.build();
    }

    // Конвертирует 1 java-колонку в объект json, содержащий тикеты
    static JsonObject toJsonColumn(Map.Entry<Integer, Column> column) {
        return Json.createObjectBuilder()
                   .add("id", column.getKey())
                   .add("title", column.getValue().getTitle())
                   .add("tickets", ticketsToJsonArray(column.getValue().getTickets()))
                   .build();
    }

    // Конвертирует 1 java-тикет в объект json
    static JsonObject toJsonTicket(Map.Entry<Integer, Ticket> ticket) {
        return Json.createObjectBuilder()
                   .add("id", ticket.getKey())
                   .add("title", ticket.getValue().getTitle())
                   .build();
    }
}
