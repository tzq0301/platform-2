package cn.edu.nju.ics.qtosplatform.model.assembler;

import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeployTaskAssembler {
    DeployTaskDTO toDTO(DeployTask deployTask);

    default Integer map(DeployTaskStatus status) {
        return status.getStatus();
    }
}
