package software.ulpgc.katas.infrastructure;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamFactory {
    public static InputStream fromFile(String path) throws FileNotFoundException {
        return new FileInputStream(new File(path));
    }
    public static InputStream fromMemory(String data) throws FileNotFoundException {
        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }

    public static OutputStream toConsole() {
        return System.out;
    }

    public static OutputStream toFile(String path) throws FileNotFoundException {
        return new FileOutputStream(path);
    }
}
