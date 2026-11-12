package software.ulpgc.katas.architecture.ports.out;

import software.ulpgc.katas.architecture.model.PlayerReport;

import java.io.OutputStream;

public interface ReportjsonSerializer {
    void serializeToJson(PlayerReport report, OutputStream destination);
}
