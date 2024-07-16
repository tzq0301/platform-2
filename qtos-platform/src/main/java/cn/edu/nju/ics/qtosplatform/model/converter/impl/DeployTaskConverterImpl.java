package cn.edu.nju.ics.qtosplatform.model.converter.impl;

import cn.edu.nju.ics.qtosplatform.model.converter.DeployTaskConverter;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskDependencyPO;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskPO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeployTaskConverterImpl implements DeployTaskConverter {
    @Override
    public DeployTask toDeployTask(DeployTaskPO deployTaskPO) {
        return new DeployTask(deployTaskPO.getId(), deployTaskPO.getServiceName(), deployTaskPO.getProjectId(), deployTaskPO.getMachineId(), deployTaskPO.getStatus());
    }

    @Override
    public DeployTask toDeployTask(DeployTaskPO deployTaskPO, List<DeployTaskDependencyPO> dependencies) {
        return new DeployTask(deployTaskPO.getId(), deployTaskPO.getServiceName(), deployTaskPO.getProjectId(), deployTaskPO.getMachineId(), deployTaskPO.getStatus(), mapToDependentTaskIds(dependencies));
    }

    @Override
    public DeployTaskPO toDeployTaskPO(DeployTask deployTask) {
        return new DeployTaskPO(deployTask.getId(), deployTask.getServiceName(), deployTask.getProjectId(), deployTask.getMachineId(), deployTask.getStatus().getValue());
    }

    @Override
    public List<String> mapToDependentTaskIds(List<DeployTaskDependencyPO> dependencies) {
        return dependencies
                .stream()
                .map(DeployTaskDependencyPO::getTargetDeployTaskId)
                .toList();
    }

    @Override
    public List<DeployTaskDependencyPO> toDeployTaskDependencies(DeployTask deployTask) {
        return deployTask.getDependentTaskIds()
                .stream()
                .map(dependentTaskId -> new DeployTaskDependencyPO(deployTask.getId(), dependentTaskId))
                .toList();
    }
}
