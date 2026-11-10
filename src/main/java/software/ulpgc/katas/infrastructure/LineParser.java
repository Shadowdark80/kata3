package software.ulpgc.katas.infrastructure;

import software.ulpgc.katas.architecture.model.Player;

public record LineParser() {
    public Player parseLine(String line) {
        String[] player = line.split(",");
        if (player.length < 5){
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
