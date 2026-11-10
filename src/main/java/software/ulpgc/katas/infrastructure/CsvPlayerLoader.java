package software.ulpgc.katas.infrastructure;

import software.ulpgc.katas.architecture.model.Player;
import software.ulpgc.katas.architecture.ports.in.PlayerLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                totalRead++;
                if (line.isBlank()){
                    totalDiscarded++;
                    continue;
                }

                Player player = parseLine(line);
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

    private Player parseLine(String line) {
        String[] player = line.split(",");
        if (player.length < 5){
            totalDiscarded++;
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

    @Override
    public int getDiscardedCount() {
        return totalDiscarded;
    }

    @Override
    public int getTotalreadCount() {
        return totalRead;
    }
}
