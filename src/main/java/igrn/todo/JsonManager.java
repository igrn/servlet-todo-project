package igrn.todo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 13.01.2021 Отработать кейс PUT тикета (переделать в Set или HashMap?)
// FIXME: 14.01.2021 Методы практически полностью совпадают, их нужно как-то обобщить (наследование?!)
public class JsonManager {
    // Конвертирует json-массив колонок в список (содержит тикеты)
    public static List<Column> toColumnList(JsonArray jsonArray) {
        List<JsonObject> columns = jsonArray.getValuesAs(JsonObject.class);
        return columns.stream().map(column -> {
                                        int id = column.getInt("id");
                                        String title = column.getString("title");
                                        List<Ticket> tickets = toTicketList(column.getJsonArray("tickets"));
                                        return new Column(id, title, tickets);
                               }).collect(Collectors.toList());
    }

    // Конвертирует json-массив тикетов ОДНОЙ колонки в список тикетов
    private static List<Ticket> toTicketList(JsonArray jsonArray) {
        List<JsonObject> tickets = jsonArray.getValuesAs(JsonObject.class);
        return tickets.stream().map(ticket -> {
                                        int id = ticket.getInt("id");
                                        String title = ticket.getString("title");
                                        return new Ticket(id, title);
                               }).collect(Collectors.toList());
    }

    // Конвертирует список колонок обратно в json-массив (так же содержит тикеты)
    public static JsonArray toJsonColumns(List<Column> columns) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        columns.stream().map(column -> Json.createObjectBuilder()
                                           .add("id", column.getId())
                                           .add("title", column.getTitle())
                                           .add("tickets", toJsonTickets(column.getTickets())))
                        .forEach(builder::add);
        return builder.build();
    }

    // Конвертирует список тикетов обратно в json-массив (не обязательно одной колонки)
    private static JsonArray toJsonTickets(List<Ticket> tickets) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        tickets.stream().map(ticket -> Json.createObjectBuilder()
                                           .add("id", ticket.getId())
                                           .add("title", ticket.getTitle()))
                        .forEach(builder::add);
        return builder.build();
    }
}
