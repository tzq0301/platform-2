package cn.edu.nju.ics.qtosbase.infrastructure.file.impl;

import cn.edu.nju.ics.qtosbase.infrastructure.file.FileManager;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileManagerImpl implements FileManager {
    @Override
    public void createDirectoryRecursively(@NonNull Path path) throws IOException {
        FileUtils.createParentDirectories(new File(path.toString()));
        Files.createDirectory(path);
    }

    @Override
    public void extractTarAndTransport(@NonNull InputStream archived, @NonNull Path dest) throws IOException {
        try (var tarArchiveInputStream = new TarArchiveInputStream(new GzipCompressorInputStream(archived))) {
            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextEntry()) != null) {
                var entryPath = Paths.get(dest.toString(), entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectory(entryPath);
                } else {
                    Files.createFile(entryPath);
                    try (var outputStream = new BufferedOutputStream(new FileOutputStream(entryPath.toFile()))) {
                        IOUtils.copy(tarArchiveInputStream, outputStream);
                    }
                }
            }
        }
    }
}
