package cn.edu.nju.ics.qtosplatform.converter;

import cn.edu.nju.ics.qtosplatform.domain.aggregator.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskDependencyPO;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskPO;

import java.util.List;

public interface DeployTaskConverter {
    DeployTask toDeployTask(DeployTaskPO deployTaskPO);

    DeployTask toDeployTask(DeployTaskPO deployTaskPO, List<DeployTaskDependencyPO> dependencies);

    DeployTaskPO toDeployTaskPO(DeployTask deployTask);

    List<DeployTaskDependencyPO> toDeployTaskDependencies(DeployTask deployTask);
}
