package igrn.todo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

// FIXME: 12.01.2021 метод readJson должен читать файл и выдавать объект Column (Ticket внутри?)
public class JsonTest {
    public static void main(String[] args) throws IOException {
        Path target = Path.of("C:\\Users\\igor\\Desktop\\Desk.json");
        Path source = Path.of("C:\\Users\\igor\\Desktop\\Source.json");
        List<Column> columns = readJsonFile(target);
        columns.addAll(readJsonFile(source));
        columns.forEach(System.out::println);
    }

    private static List<Column> readJsonFile(Path jsonFile) throws IOException {
        try (JsonReader reader = Json.createReader(Files.newInputStream(jsonFile))) {
            List<JsonObject> jsonColumns = reader.readObject().getJsonArray("columns")
                                                              .getValuesAs(JsonObject.class);

            return jsonColumns.stream().map(jsonColumn -> { int id = jsonColumn.getInt("id");
                                                            String title = jsonColumn.getString("title");
                                                            Column column = new Column(id, title);
                                                            column.setTickets(parseTickets(jsonColumn));
                                                            return column; })
                                       .collect(Collectors.toList());
        }
    }

    private static List<Ticket> parseTickets(JsonObject jsonColumn) {
        List<JsonObject> jsonTickets = jsonColumn.getJsonArray("tickets").getValuesAs(JsonObject.class);
        return jsonTickets.stream().map(jsonTicket -> { int id = jsonTicket.getInt("id");
                                                        String title = jsonTicket.getString("title");
                                                        return new Ticket(id, title); })
                                   .collect(Collectors.toList());
    }

    // TODO: 13.01.2021 доделать этот метод?
    private static void writeToJsonFile(Path source, Path target) throws IOException {
        try (JsonReader reader = Json.createReader(Files.newInputStream(source));
             JsonWriter writer = Json.createWriter(Files.newBufferedWriter(target)))
        {
            JsonObject column = reader.readObject();
            writer.writeObject(column);
        }

    }
}
