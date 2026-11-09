package software.ulpgc.katas.architecture.model;

public record ReportPlayerMetadata(int totalRead,
                                   int validRecords,
                                   int discardedRecords,
                                   double averagePointsPerGame,
                                   String generatedAt) {
}
