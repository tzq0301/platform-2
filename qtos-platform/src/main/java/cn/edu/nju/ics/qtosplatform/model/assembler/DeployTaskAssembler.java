package cn.edu.nju.ics.qtosplatform.model.assembler;

import cn.edu.nju.ics.qtosplatform.model.dto.*;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeployTaskAssembler {
    DeployTaskDTO toDTO(DeployTask deployTask);

    default Integer map(DeployTaskStatus status) {
        return status.getStatus();
    }

    @Mapping(target = "archived", source = "file")
    UploadCommandDTO toUploadCommandDTO(UploadRequestDTO uploadRequestDTO);

    InstallCommandDTO toInstallCommandDTO(InstallRequestDTO installRequestDTO);
}
