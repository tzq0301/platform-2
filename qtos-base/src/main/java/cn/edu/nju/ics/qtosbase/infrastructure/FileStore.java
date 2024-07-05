package cn.edu.nju.ics.qtosbase.infrastructure;

import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileStore {
    void createDirectoryRecursively(@NonNull Path path) throws IOException;

    void extractTarAndTransport(@NonNull InputStream archived, @NonNull Path dest) throws IOException;
}
