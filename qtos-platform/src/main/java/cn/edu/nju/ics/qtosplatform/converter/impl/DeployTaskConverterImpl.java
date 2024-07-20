package cn.edu.nju.ics.qtosplatform.converter.impl;

import cn.edu.nju.ics.qtosplatform.converter.DeployTaskConverter;
import cn.edu.nju.ics.qtosplatform.domain.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskStatus;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskDependencyPO;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskPO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeployTaskConverterImpl implements DeployTaskConverter {
    @Override
    public DeployTask toDeployTask(DeployTaskPO deployTaskPO) {
        return new DeployTask(new DeployTaskId(deployTaskPO.getId()), deployTaskPO.getServiceName(), new ProjectId(deployTaskPO.getProjectId()),
                new MachineId(deployTaskPO.getMachineId()), DeployTaskStatus.from(deployTaskPO.getStatus()), new ArrayList<>());
    }

    @Override
    public DeployTask toDeployTask(DeployTaskPO deployTaskPO, List<DeployTaskDependencyPO> dependencies) {
        return new DeployTask(new DeployTaskId(deployTaskPO.getId()), deployTaskPO.getServiceName(), new ProjectId(deployTaskPO.getProjectId()), new MachineId(deployTaskPO.getMachineId()),
                DeployTaskStatus.from(deployTaskPO.getStatus()), dependencies.stream().map(DeployTaskDependencyPO::getTargetDeployTaskId).map(DeployTaskId::new).toList());
    }

    @Override
    public DeployTaskPO toDeployTaskPO(DeployTask deployTask) {
        return new DeployTaskPO(deployTask.getId().value(), deployTask.getServiceName(), deployTask.getProjectId().value(), deployTask.getMachineId().value(), deployTask.getStatus().getValue());
    }

    @Override
    public List<DeployTaskDependencyPO> toDeployTaskDependencies(DeployTask deployTask) {
        return deployTask.getDependentTaskIds()
                .stream()
                .map(dependentTaskId -> new DeployTaskDependencyPO(deployTask.getId().value(), dependentTaskId.value()))
                .toList();
    }
}
