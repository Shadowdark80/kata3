package software.ulpgc.katas.architecture.model;

public record PlayerMetadata(int totalRead,
                             int validRecords,
                             int discardedRecords,
                             double averagePointsPerGame,
                             String generatedAt) {
}
