package software.ulpgc.katas.infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.ulpgc.katas.architecture.model.PlayerReport;
import software.ulpgc.katas.architecture.ports.out.ReportJsonSerializer;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class GsonPlayerSerializer implements ReportJsonSerializer {

    private final Gson gson;

    public GsonPlayerSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void serializeToJson(PlayerReport report, OutputStream destination) {
        if (destination == null) throw new IllegalArgumentException("El OutputStream no puede ser nulo.");
        PrintWriter writer = new PrintWriter(destination, true, StandardCharsets.UTF_8);
        writer.println(gson.toJson(report));
    }
}
