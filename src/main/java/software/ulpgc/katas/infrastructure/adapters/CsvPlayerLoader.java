package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.architecture.model.Player;
import software.ulpgc.katas.architecture.ports.in.PlayerLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvPlayerLoader implements PlayerLoader {
    private int totalRead = 0;
    private int totalDiscarded = 0;

    @Override
    public List<Player> load(InputStream inputStream) {
        if (inputStream == null)  throw new IllegalArgumentException("El InputStream no puede ser nulo.");

        List<Player> players = new ArrayList<>();
        totalDiscarded = 0;
        totalRead = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
            String line = reader.readLine();

            while((line = reader.readLine()) != null){
                totalRead++;
                if(line.isBlank()){
                    totalDiscarded++;
                    continue;
                }

                Player player = new LineParser().parseLine(line);
                if (player != null){
                    players.add(player);
                } else {
                    totalDiscarded++;
                }
            }
        } catch (IOException e) {
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

    private record LineParser() {
        public Player parseLine(String line){
            String[] player = line.split(",");
            if(player.length < 5){
                return null;
            }
            try {
                return new Player(
                        player[0].trim(),
                        player[1].trim(),
                        Double.parseDouble(player[2].trim()),
                        Double.parseDouble(player[3].trim()),
                        Integer.parseInt(player[4].trim())
                );
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
