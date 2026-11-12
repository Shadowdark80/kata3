package software.ulpgc.katas;

import software.ulpgc.katas.architecture.domain.GeneratePlayerReportUseCase;
import software.ulpgc.katas.architecture.model.Player;
import software.ulpgc.katas.architecture.model.PlayerReport;
import software.ulpgc.katas.architecture.ports.in.PlayerLoader;
import software.ulpgc.katas.architecture.ports.out.ReportjsonSerializer;
import software.ulpgc.katas.infrastructure.adapters.CsvPlayerLoader;
import software.ulpgc.katas.infrastructure.adapters.GsonPlayerSerializer;
import software.ulpgc.katas.infrastructure.adapters.StreamFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Main {
    static void main() {
        PlayerLoader loader = new CsvPlayerLoader();
        GeneratePlayerReportUseCase useCase = new GeneratePlayerReportUseCase();
        ReportjsonSerializer serializer = new GsonPlayerSerializer();

        try (InputStream inputStream = StreamFactory.fromFile("nba_players_kata3.csv")){
            OutputStream outputStream = StreamFactory.toFile("nba_players_results.json");

            List<Player> players = loader.load(inputStream);
            PlayerReport report = useCase.execute(players, loader.getTotalReadCount(), loader.getDiscardedCount());
            serializer.serializeToJson(report, outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error durante la ejecución: ", e);
        }
    }
}
