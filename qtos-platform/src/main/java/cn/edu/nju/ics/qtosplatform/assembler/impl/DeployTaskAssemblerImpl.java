package cn.edu.nju.ics.qtosplatform.assembler.impl;

import cn.edu.nju.ics.qtosplatform.assembler.DeployTaskAssembler;
import cn.edu.nju.ics.qtosplatform.domain.aggregator.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;
import org.springframework.stereotype.Component;

@Component
public class DeployTaskAssemblerImpl implements DeployTaskAssembler {
    @Override
    public DeployTaskDTO toDTO(DeployTask deployTask) {
        return new DeployTaskDTO(deployTask.getId().value(), deployTask.getServiceName(), deployTask.getStatus().getValue());
    }
}
