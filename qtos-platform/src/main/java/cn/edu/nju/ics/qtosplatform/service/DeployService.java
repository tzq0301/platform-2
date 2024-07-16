package cn.edu.nju.ics.qtosplatform.service;

import cn.edu.nju.ics.qtosplatform.model.command.InstallCommand;
import cn.edu.nju.ics.qtosplatform.model.command.UninstallCommand;
import cn.edu.nju.ics.qtosplatform.model.command.UploadCommand;
import cn.edu.nju.ics.qtosplatform.model.dto.request.InstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UninstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.response.UploadResponse;
import io.micrometer.common.lang.NonNull;

import java.io.IOException;

public interface DeployService {
    UploadResponse upload(@NonNull UploadCommand command) throws IOException;

    void install(@NonNull InstallCommand command);

    void uninstall(@NonNull UninstallCommand command);
}
