package cn.edu.nju.ics.qtosplatform.service;

import cn.edu.nju.ics.qtosplatform.model.dto.request.InstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UninstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UploadRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.response.UploadResponse;
import io.micrometer.common.lang.NonNull;

import java.io.IOException;

public interface DeployService {
    UploadResponse upload(@NonNull UploadRequest request) throws IOException;

    void install(@NonNull InstallRequest request);

    void uninstall(@NonNull UninstallRequest request);
}
