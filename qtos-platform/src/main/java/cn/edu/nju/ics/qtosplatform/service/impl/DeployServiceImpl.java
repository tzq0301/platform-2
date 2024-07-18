package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.domain.aggregator.DeployTask;
import cn.edu.nju.ics.qtosplatform.domain.aggregator.Machine;
import cn.edu.nju.ics.qtosplatform.domain.repository.DeployTaskRepository;
import cn.edu.nju.ics.qtosplatform.domain.repository.MachineRepository;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskStatus;
import cn.edu.nju.ics.qtosplatform.infrastructure.client.qtosbase.QtosBaseClient;
import cn.edu.nju.ics.qtosplatform.infrastructure.client.qtosbase.QtosBaseClientFactory;
import cn.edu.nju.ics.qtosplatform.model.command.InstallCommand;
import cn.edu.nju.ics.qtosplatform.model.command.UninstallCommand;
import cn.edu.nju.ics.qtosplatform.model.command.UploadCommand;
import cn.edu.nju.ics.qtosplatform.model.dto.response.UploadResponse;
import cn.edu.nju.ics.qtosplatform.service.DeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class DeployServiceImpl implements DeployService {
    private final MachineRepository machineRepository;

    private final DeployTaskRepository deployTaskRepository;

    private final QtosBaseClientFactory qtosBaseClientFactory;

    private final Integer qtosBasePort;

    private final IdGenerator idGenerator;

    public DeployServiceImpl(@NonNull MachineRepository machineRepository,
                             @NonNull DeployTaskRepository deployTaskRepository,
                             @NonNull QtosBaseClientFactory qtosBaseClientFactory,
                             @Value("${qtos.platform.base-port}") Integer qtosBasePort,
                             @NonNull IdGenerator idGenerator) {
        this.machineRepository = machineRepository;
        this.deployTaskRepository = deployTaskRepository;
        this.qtosBaseClientFactory = qtosBaseClientFactory;
        this.qtosBasePort = qtosBasePort;
        this.idGenerator = idGenerator;
    }

    @Override
    public UploadResponse upload(@NonNull UploadCommand command) throws IOException {
        Machine machine = machineRepository.findById(command.machineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        DeployTaskId taskId = new DeployTaskId(idGenerator.generateId().toString().replaceAll("-", ""));

        qtosBaseClient.upload(taskId.value(), command.file());

        DeployTask deployTask = new DeployTask(taskId, command.serviceName(), command.projectId(), command.machineId(), DeployTaskStatus.NOT_INSTALL_YET, command.dependentTaskIds());

        deployTaskRepository.create(deployTask);

        return new UploadResponse(taskId.value());
    }

    @Override
    public void install(@NonNull InstallCommand command) {
        DeployTaskId deployTaskId = command.taskId();

        log.info("install taskId: {}", deployTaskId);

        DeployTask deployTask = deployTaskRepository.findById(deployTaskId);

        if (deployTask.getStatus() == DeployTaskStatus.INSTALLED) {
            throw new RuntimeException("this task is already installed: " + deployTaskId);
        }

        deployTask.getDependentTaskIds().forEach(dependentTaskId -> {
            if (deployTaskRepository.findById(dependentTaskId).getStatus() != DeployTaskStatus.INSTALLED) {
                throw new RuntimeException("dependent task not installed: " + dependentTaskId);
            }
        });

        Machine machine = machineRepository.findById(deployTask.getMachineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        try {
            qtosBaseClient.install(Map.of("taskId", deployTaskId.value()));
        } catch (Exception e) {
            deployTaskRepository.updateStatus(deployTaskId, DeployTaskStatus.INSTALL_FAILED);
            throw e;
        }

        deployTaskRepository.updateStatus(deployTaskId, DeployTaskStatus.INSTALLED);
    }

    @Override
    public void uninstall(@NonNull UninstallCommand command) {
        DeployTaskId taskId = command.taskId();

        log.info("uninstall taskId: {}", taskId);

        DeployTask deployTask = deployTaskRepository.findById(taskId);

        if (deployTask.getStatus() != DeployTaskStatus.INSTALLED) {
            throw new RuntimeException("this task has not been installed: " + taskId);
        }

        Machine machine = machineRepository.findById(deployTask.getMachineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        qtosBaseClient.uninstall(Map.of("taskId", taskId.value()));

        deployTaskRepository.updateStatus(taskId, DeployTaskStatus.UNINSTALLED);
    }
}
