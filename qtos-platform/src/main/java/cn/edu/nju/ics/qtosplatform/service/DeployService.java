package cn.edu.nju.ics.qtosplatform.service;

import cn.edu.nju.ics.qtosplatform.model.dto.InstallCommandDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadCommandDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadResponseDTO;
import io.micrometer.common.lang.NonNull;

import java.io.IOException;

public interface DeployService {
    UploadResponseDTO upload(@NonNull UploadCommandDTO uploadCommandDTO) throws IOException;

    void install(@NonNull InstallCommandDTO installCommandDTO);
}
