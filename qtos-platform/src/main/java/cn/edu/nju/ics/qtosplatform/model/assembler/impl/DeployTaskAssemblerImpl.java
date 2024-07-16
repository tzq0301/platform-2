package cn.edu.nju.ics.qtosplatform.model.assembler.impl;

import cn.edu.nju.ics.qtosplatform.model.assembler.DeployTaskAssembler;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;
import org.springframework.stereotype.Component;

@Component
public class DeployTaskAssemblerImpl implements DeployTaskAssembler {
    @Override
    public DeployTaskDTO toDTO(DeployTask deployTask) {
        return new DeployTaskDTO(deployTask.getId(), deployTask.getServiceName(), deployTask.getStatus().getValue());
    }
}
