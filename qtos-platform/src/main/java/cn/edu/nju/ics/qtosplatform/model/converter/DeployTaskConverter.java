package cn.edu.nju.ics.qtosplatform.model.converter;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatus;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskDependencyPO;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeployTaskConverter {
    @Mapping(target = "dependentTaskIds", ignore = true)
    DeployTask toDeployTask(DeployTaskPO deployTaskPO);

    DeployTaskPO toDeployTaskPO(DeployTask deployTask);

    default List<String> mapToDependentTaskIds(List<DeployTaskDependencyPO> dependencies) {
        return dependencies
                .stream()
                .map(DeployTaskDependencyPO::getTargetDeployTaskId)
                .toList();
    }

    default List<DeployTaskDependencyPO> toDeployTaskDependencies(DeployTask deployTask) {
        return deployTask.getDependentTaskIds()
                .stream()
                .map(dependentTaskId -> new DeployTaskDependencyPO(deployTask.getId(), dependentTaskId))
                .toList();
    }

    default DeployTaskStatus map(Integer value) {
        return DeployTaskStatus.from(value);
    }

    default Integer map(DeployTaskStatus status) {
        return status.getStatus();
    }
}
