package cn.edu.nju.ics.qtosplatform.service;

import cn.edu.nju.ics.qtosplatform.entity.model.InstallCommand;
import cn.edu.nju.ics.qtosplatform.entity.model.UploadCommand;
import cn.edu.nju.ics.qtosplatform.entity.model.UploadDTO;
import io.micrometer.common.lang.NonNull;

import java.io.IOException;

public interface DeployService {
    UploadDTO upload(@NonNull UploadCommand uploadCommand) throws IOException;

    void install(@NonNull InstallCommand installCommand);
}
