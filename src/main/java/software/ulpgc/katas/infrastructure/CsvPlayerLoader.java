package software.ulpgc.katas.infrastructure;

import software.ulpgc.katas.architecture.model.Player;
import software.ulpgc.katas.architecture.ports.in.PlayerLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvPlayerLoader implements PlayerLoader {

    private int totalDiscarded = 0;
    private int totalRead = 0;

    @Override
    public List<Player> load(InputStream inputStream) {
        if (inputStream == null) throw new IllegalArgumentException("El InputStream no puede ser nulo.");

        List<Player> players = new ArrayList<>();
        totalDiscarded = 0;
        totalRead = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                totalRead++;
                if (line.isBlank()){
                    totalDiscarded++;
                    continue;
                }

                Player player = new LineParser().parseLine(line);
                if(player != null){
                    players.add(player);
                } else{
                    totalDiscarded++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el InputStream", e);
        }
        return players;
    }

    @Override
    public int getDiscardedCount() {
        return totalDiscarded;
    }

    @Override
    public int getTotalReadCount() {
        return totalRead;
    }
}
