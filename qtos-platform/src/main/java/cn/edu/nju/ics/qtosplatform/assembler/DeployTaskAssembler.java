package cn.edu.nju.ics.qtosplatform.assembler;

import cn.edu.nju.ics.qtosplatform.domain.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;

public interface DeployTaskAssembler {
    DeployTaskDTO toDTO(DeployTask deployTask);
}
