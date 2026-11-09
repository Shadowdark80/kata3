package software.ulpgc.katas.architecture.ports.in;

import software.ulpgc.katas.architecture.model.Player;

import java.io.InputStream;
import java.util.List;

public interface PlayerLoader {
    List<Player> load(InputStream inputStream);
    int getDiscardedCount();
    int getTotalreadCount();
}
