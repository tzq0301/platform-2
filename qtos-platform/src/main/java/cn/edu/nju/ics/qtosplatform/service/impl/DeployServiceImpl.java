package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatus;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.dto.InstallCommandDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadCommandDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadResponseDTO;
import cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase.QtosBaseClient;
import cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase.QtosBaseClientFactory;
import cn.edu.nju.ics.qtosplatform.repository.DeployTaskRepository;
import cn.edu.nju.ics.qtosplatform.repository.MachineRepository;
import cn.edu.nju.ics.qtosplatform.service.DeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
@Slf4j
public class DeployServiceImpl implements DeployService {
    private final MachineRepository machineRepository;

    private final DeployTaskRepository deployTaskRepository;

    private final QtosBaseClientFactory qtosBaseClientFactory;

    private final Integer qtosBasePort;

    public DeployServiceImpl(@NonNull MachineRepository machineRepository,
                             @NonNull DeployTaskRepository deployTaskRepository, QtosBaseClientFactory qtosBaseClientFactory,
                             @Value("${qtos.platform.base-port}") Integer qtosBasePort) {
        this.machineRepository = machineRepository;
        this.deployTaskRepository = deployTaskRepository;
        this.qtosBaseClientFactory = qtosBaseClientFactory;
        this.qtosBasePort = qtosBasePort;
    }

    @Override
    public UploadResponseDTO upload(@NonNull UploadCommandDTO command) throws IOException {
        Machine machine = machineRepository.findById(command.getMachineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        String taskId = qtosBaseClient.upload(command.getArchived());

        DeployTask deployTask = new DeployTask(taskId, command.getServiceName(), command.getProjectId(),
                command.getMachineId(), DeployTaskStatus.UNDEPLOY, Arrays.asList(command.getDependentTaskIds()));

        deployTaskRepository.create(deployTask);

        return new UploadResponseDTO(taskId);
    }

    @Override
    public void install(@NonNull InstallCommandDTO command) {
        String taskId = command.getTaskId();

        log.info("install taskId: {}", taskId);

        DeployTask deployTask = deployTaskRepository.findById(taskId);

        if (deployTask.getStatus() == DeployTaskStatus.DEPLOYED) {
            throw new RuntimeException("this task is already deployed: " + taskId);
        }

        deployTask.getDependentTaskIds().forEach(dependentTaskId -> {
            if (deployTaskRepository.findById(dependentTaskId).getStatus() != DeployTaskStatus.DEPLOYED) {
                throw new RuntimeException("dependent task not deployed: " + dependentTaskId);
            }
        });

        Machine machine = machineRepository.findById(deployTask.getMachineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        try {
            qtosBaseClient.install(Map.of("taskId", taskId));
        } catch (Exception e) {
            deployTaskRepository.updateStatus(taskId, DeployTaskStatus.FAILED);
            throw e;
        }

        deployTaskRepository.updateStatus(taskId, DeployTaskStatus.DEPLOYED);
    }
}
