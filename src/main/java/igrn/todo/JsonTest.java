package igrn.todo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        Path deskPath = Path.of("C:\\Users\\igor\\Desktop\\Desk.json");

        try (InputStream input = Files.newInputStream(deskPath);
             JsonReader reader = Json.createReader(input))
        {
            List<JsonObject> columns = reader.readObject()
                                             .getJsonArray("columns")
                                             .getValuesAs(JsonObject.class);

            for (JsonObject column : columns) {
                List<JsonObject> tickets = column.getJsonArray("tickets")
                                                 .getValuesAs(JsonObject.class);

                for (JsonObject ticket : tickets) {
                    System.out.println(ticket.getString("title"));
                }
            }
        }
    }
}
