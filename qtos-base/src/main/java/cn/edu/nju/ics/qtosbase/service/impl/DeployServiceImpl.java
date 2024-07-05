package cn.edu.nju.ics.qtosbase.service.impl;

import cn.edu.nju.ics.qtosbase.infrastructure.FileStore;
import cn.edu.nju.ics.qtosbase.service.DeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DeployServiceImpl implements DeployService {
    private final String deployDir;

    private final List<String> installScriptCandidates;

    private final FileStore fileStore;

    public DeployServiceImpl(@Value("${qtos.base.deploy-dir}") String deployDir,
                             @Value("${qtos.base.install-script-candidates}") List<String> installScriptCandidates,
                             FileStore fileStore) {
        this.deployDir = deployDir;
        this.installScriptCandidates = installScriptCandidates;
        this.fileStore = fileStore;
    }

    @Override
    public String transport(@NonNull InputStream archived) throws IOException {
        String taskId = UUID.randomUUID().toString().replaceAll("-", "");
        log.info("taskId: {}", taskId);

        Path taskPath = Paths.get(deployDir, taskId);
        fileStore.createDirectoryRecursively(taskPath);
        log.info("create taskPath: {}", taskPath.toAbsolutePath());

        fileStore.extractTarAndTransport(archived, taskPath);
        log.info("transport the archived file and unarchive it to: {}", taskPath.toAbsolutePath());

        return taskId;
    }

    @Override
    public void install(@NonNull String taskId) throws IOException, InterruptedException {
        String taskPath = String.format("%s/%s", deployDir, taskId);
        for (String candidate: installScriptCandidates) {
            File installScript = new File(taskPath, candidate);
            if (!installScript.exists()) {
                continue;
            }
            log.info("install script: {}", installScript.getAbsolutePath());

            String[] command = new String[2];
            if (candidate.endsWith(".sh")) {
                command[0] = "bash";
            } else if (candidate.endsWith(".py")) {
                command[0] = "python3";
            }
            command[1] = installScript.getAbsolutePath();

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(taskPath));

            int exitCode = pb.start().waitFor();
            if (exitCode == 0) {
                log.info("install script success");
                return;
            }

            log.info("install script failed with exit code {}, try next", exitCode);
        }
        throw new RuntimeException("execution of all install scripts failed");
    }
}
