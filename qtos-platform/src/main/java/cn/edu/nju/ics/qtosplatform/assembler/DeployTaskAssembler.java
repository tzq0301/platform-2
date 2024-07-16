package cn.edu.nju.ics.qtosplatform.assembler;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;

public interface DeployTaskAssembler {
    DeployTaskDTO toDTO(DeployTask deployTask);
}
