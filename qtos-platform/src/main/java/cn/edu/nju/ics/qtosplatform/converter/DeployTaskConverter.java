package cn.edu.nju.ics.qtosplatform.converter;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskDependencyPO;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskPO;

import java.util.List;

public interface DeployTaskConverter {
    DeployTask toDeployTask(DeployTaskPO deployTaskPO);

    DeployTask toDeployTask(DeployTaskPO deployTaskPO, List<DeployTaskDependencyPO> dependencies);

    DeployTaskPO toDeployTaskPO(DeployTask deployTask);

    List<String> mapToDependentTaskIds(List<DeployTaskDependencyPO> dependencies);

    List<DeployTaskDependencyPO> toDeployTaskDependencies(DeployTask deployTask);
}
