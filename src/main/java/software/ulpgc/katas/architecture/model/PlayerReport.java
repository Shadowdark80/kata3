package software.ulpgc.katas.architecture.model;

import java.util.List;

public record PlayerReport(ReportPlayerMetadata reportPlayerMetadata, List<Player> data) {
}
