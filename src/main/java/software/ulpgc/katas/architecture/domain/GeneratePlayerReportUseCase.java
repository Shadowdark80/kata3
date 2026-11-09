package software.ulpgc.katas.architecture.domain;

import software.ulpgc.katas.architecture.model.Player;
import software.ulpgc.katas.architecture.model.PlayerReport;
import software.ulpgc.katas.architecture.model.ReportPlayerMetadata;

import java.time.LocalDateTime;
import java.util.List;

public class GeneratePlayerReportUseCase {

    public PlayerReport execute(List<Player> players, int totalRead, int totalDiscarded) {
        return new PlayerReport(calculatePlayerMetadata(players, totalRead, totalDiscarded), List.of());
    }

    private double calculateAveragePoints(List<Player> players) {
        return players.stream().mapToDouble(Player::pointsPerGame).average().orElse(0.0);
    }

    private ReportPlayerMetadata calculatePlayerMetadata(List<Player> players, int totalRead, int totalDiscarded) {

        calculatePlayerMetadataEmptyOrNull(players, totalRead, totalDiscarded);

        return new ReportPlayerMetadata(totalRead,
                                        players.size(),
                                        totalDiscarded,
                                        Math.round(calculateAveragePoints(players)),
                                        LocalDateTime.now().toString());
    }

    private PlayerReport calculatePlayerMetadataEmptyOrNull(List<Player> players, int totalRead, int totalDiscarded) {
        if (!players.isEmpty()) {
            return null;
        }
        return new PlayerReport(new ReportPlayerMetadata(totalRead,
                0,
                totalDiscarded,
                0.0,
                LocalDateTime.now().toString()),
                List.of());
    }
}
