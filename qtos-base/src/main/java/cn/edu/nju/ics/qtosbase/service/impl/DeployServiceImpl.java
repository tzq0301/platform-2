package cn.edu.nju.ics.qtosbase.service.impl;

import cn.edu.nju.ics.qtosbase.service.DeployService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DeployServiceImpl implements DeployService {
    private final String deployDir;

    private final List<String> installScriptCandidates;

    public DeployServiceImpl(@Value("${qtos.base.deploy-dir}") String deployDir,
                             @Value("${qtos.base.install-script-candidates}") List<String> installScriptCandidates) {
        this.deployDir = deployDir;
        this.installScriptCandidates = installScriptCandidates;
    }

    @Override
    public String transport(@NonNull InputStream archived) throws IOException {
        String taskId = UUID.randomUUID().toString().replaceAll("-", "");
        log.info("taskId: {}", taskId);

        Path taskPath = Paths.get(deployDir, taskId);
        FileUtils.createParentDirectories(new File(taskPath.toString()));
        Files.createDirectory(taskPath);
        log.info("create taskPath: {}", taskPath.toAbsolutePath());

        try (var tarArchiveInputStream = new TarArchiveInputStream(new GzipCompressorInputStream(archived))) {
            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextEntry()) != null) {
                var entryPath = Paths.get(taskPath.toString(), entry.getName());
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

        log.info("transport the archived file and unarchive it to: {}", taskPath.toAbsolutePath());

        return taskId;
    }

    @Override
    public void install(@NonNull String taskId) throws IOException {
        String taskPath = String.format("%s/%s", deployDir, taskId);
        for (String candidate: installScriptCandidates) {
            File installScript = new File(taskPath, candidate);
            if (!installScript.exists()) {
                continue;
            }
            log.info("install script: {}", installScript.getAbsolutePath());

            int exitCode = Runtime.getRuntime().exec(new String[]{installScript.getAbsolutePath()}).exitValue();
            if (exitCode == 0) {
                log.info("install script success");
                return;
            }

            log.info("install script failed with exit code {}, try next", exitCode);
        }
        throw new RuntimeException("execution of all install scripts failed");
    }
}
