package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.entity.DeployTaskStatusEnum;
import cn.edu.nju.ics.qtosplatform.entity.Machine;
import cn.edu.nju.ics.qtosplatform.entity.model.InstallCommand;
import cn.edu.nju.ics.qtosplatform.entity.model.UploadCommand;
import cn.edu.nju.ics.qtosplatform.entity.model.UploadDTO;
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
    public UploadDTO upload(@NonNull UploadCommand uploadCommand) throws IOException {
        Machine machine = machineRepository.findById(uploadCommand.getMachineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        String taskId = qtosBaseClient.upload(uploadCommand.getArchived());

        DeployTask deployTask = new DeployTask(taskId, uploadCommand.getServiceName(), uploadCommand.getProjectId(),
                uploadCommand.getMachineId(), DeployTaskStatusEnum.UNDEPLOY, Arrays.asList(uploadCommand.getDependentTaskIds()));

        deployTaskRepository.create(deployTask);

        return new UploadDTO(taskId);
    }

    @Override
    public void install(@NonNull InstallCommand installCommand) {
        String taskId = installCommand.getTaskId();

        log.info("install taskId: {}", taskId);

        DeployTask deployTask = deployTaskRepository.findById(taskId);

        if (deployTask.getStatus() != DeployTaskStatusEnum.UNDEPLOY) {
            throw new RuntimeException("this status of task is not " + DeployTaskStatusEnum.UNDEPLOY + ": " + taskId);
        }

        deployTask.getDependentTaskIds().forEach(dependentTaskId -> {
            if (deployTaskRepository.findById(dependentTaskId).getStatus() != DeployTaskStatusEnum.DEPLOYED) {
                throw new RuntimeException("dependent task not deployed: " + dependentTaskId);
            }
        });

        Machine machine = machineRepository.findById(deployTask.getMachineId());

        QtosBaseClient qtosBaseClient = qtosBaseClientFactory.create(machine.getHost(), qtosBasePort);

        qtosBaseClient.install(Map.of("taskId", taskId));

        deployTaskRepository.updateStatus(taskId, DeployTaskStatusEnum.DEPLOYED);
    }
}
