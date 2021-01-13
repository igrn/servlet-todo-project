package igrn.todo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 13.01.2021 Отработать кейс PUT тикета (переделать в Set или HashMap?)
public class JsonManager {
    public static List<Column> readJson(InputStream jsonFile) throws IOException {
        try (JsonReader reader = Json.createReader(jsonFile)) {
            List<JsonObject> columns = reader.readObject().getJsonArray("columns")
                                                          .getValuesAs(JsonObject.class);

            return columns.stream().map(column -> { int id = column.getInt("id");
                                                    String title = column.getString("title");
                                                    return new Column(id, title, parseTickets(column)); })
                                   .collect(Collectors.toList());
        }
    }

    private static List<Ticket> parseTickets(JsonObject column) {
        List<JsonObject> tickets = column.getJsonArray("tickets").getValuesAs(JsonObject.class);

        return tickets.stream().map(ticket -> { int id = ticket.getInt("id");
                                                String title = ticket.getString("title");
                                                return new Ticket(id, title); })
                               .collect(Collectors.toList());
    }

    // FIXME: 13.01.2021 доделать этот метод?
    private static void writeJson(Path source, Path target) throws IOException {
        try (JsonReader reader = Json.createReader(Files.newInputStream(source));
             JsonWriter writer = Json.createWriter(Files.newBufferedWriter(target)))
        {
            JsonObject column = reader.readObject();
            writer.writeObject(column);
        }
    }
}
