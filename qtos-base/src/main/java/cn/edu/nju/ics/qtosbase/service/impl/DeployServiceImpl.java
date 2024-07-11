package cn.edu.nju.ics.qtosbase.service.impl;

import cn.edu.nju.ics.qtosbase.infrastructure.file.FileStore;
import cn.edu.nju.ics.qtosbase.infrastructure.idgenerator.IdGenerator;
import cn.edu.nju.ics.qtosbase.infrastructure.idgenerator.impl.UuidGenerator;
import cn.edu.nju.ics.qtosbase.service.DeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
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

    private final List<String> uninstallScriptCandidates;

    private final FileStore fileStore;

    private final IdGenerator<UUID> idGenerator;

    public DeployServiceImpl(@Value("${qtos.base.deploy-dir}") String deployDir,
                             @Value("${qtos.base.install-script-candidates}") List<String> installScriptCandidates,
                             @Value("${qtos.base.uninstall-script-candidates}") List<String> uninstallScriptCandidates,
                             FileStore fileStore) {
        this.deployDir = deployDir;
        this.installScriptCandidates = installScriptCandidates;
        this.uninstallScriptCandidates = uninstallScriptCandidates;
        this.fileStore = fileStore;
        this.idGenerator = new UuidGenerator(UuidGenerator.Version.V7);
    }

    @Override
    public String transport(@NonNull InputStream archived) throws IOException {
        String taskId = idGenerator.generate().toString().replaceAll("-", "");
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

        if (!new File(taskPath).exists()) {
            log.warn("taskPath for installing: {} does not exist", taskPath);
            throw new FileNotFoundException(taskPath);
        }

        executeAlongCandidates(taskPath, installScriptCandidates);
    }

    @Override
    public void uninstall(@NonNull String taskId) throws IOException, InterruptedException {
        String taskPath = String.format("%s/%s", deployDir, taskId);

        if (!new File(taskPath).exists()) {
            log.warn("taskPath for uninstalling: {} does not exist", taskPath);
            throw new FileNotFoundException(taskPath);
        }

        executeAlongCandidates(taskPath, uninstallScriptCandidates);
    }

    private void executeAlongCandidates(String taskPath, List<String> scriptsCandidates) throws InterruptedException, IOException {
        for (String candidate : scriptsCandidates) {
            File script = new File(taskPath, candidate);
            if (!script.exists()) {
                log.info("script {} does not exist", candidate);
                continue;
            }
            log.info("script: {}", script.getAbsolutePath());

            String[] command = new String[2];
            if (candidate.endsWith(".sh")) {
                command[0] = "bash";
            } else if (candidate.endsWith(".py")) {
                command[0] = "python3";
            }
            command[1] = script.getAbsolutePath();

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(taskPath));

            int exitCode = pb.start().waitFor();
            if (exitCode == 0) {
                log.info("execute script success");
                return;
            }

            log.info("execute script failed with exit code {}, try next", exitCode);
        }
        throw new RuntimeException("execution of all scripts failed");
    }
}
