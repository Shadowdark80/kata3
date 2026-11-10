package software.ulpgc.katas.architecture.domain;

import software.ulpgc.katas.architecture.model.Player;
import software.ulpgc.katas.architecture.model.PlayerReport;
import software.ulpgc.katas.architecture.model.ReportPlayerMetadata;
import software.ulpgc.katas.architecture.ports.out.ReportJsonSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class GeneratePlayerReportUseCase {

    public PlayerReport execute(List<Player> players, int totalRead, int totalDiscarded) {
        if (players.isEmpty() || players == null){
            ReportPlayerMetadata emptyMetadata = new ReportPlayerMetadata(
                    totalRead,
                    0,
                    totalDiscarded,
                    0.0,
                    LocalDateTime.now().toString()
            );
            return new PlayerReport(emptyMetadata, List.of());
        }

        return new PlayerReport(calculatePlayerMetadata(players, totalRead, totalDiscarded), players);
    }

    private double calculateAveragePoints(List<Player> players) {
        return players.stream().mapToDouble(Player::pointsPerGame).average().orElse(0.0);
    }

    private ReportPlayerMetadata calculatePlayerMetadata(List<Player> players, int totalRead, int totalDiscarded) {
        return new ReportPlayerMetadata(totalRead,
                                        players.size(),
                                        totalDiscarded,
                                        Math.round(calculateAveragePoints(players) * 100.0) / 100.0,
                                        LocalDateTime.now().toString());
    }
}
