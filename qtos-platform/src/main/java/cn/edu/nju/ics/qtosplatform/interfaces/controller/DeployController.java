package cn.edu.nju.ics.qtosplatform.interfaces.controller;

import cn.edu.nju.ics.qtosplatform.exception.InvalidArgumentsException;
import cn.edu.nju.ics.qtosplatform.model.dto.request.InstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UninstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UploadRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.response.UploadResponse;
import cn.edu.nju.ics.qtosplatform.service.DeployService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping("/upload")
    public UploadResponse upload(UploadRequest request) throws IOException {
        return deployService.upload(request);
    }

    @PostMapping("/install")
    public void install(@RequestBody InstallRequest request) {
        if (!StringUtils.hasText(request.getTaskId())) {
            throw new InvalidArgumentsException("install taskId is empty");
        }

        deployService.install(request);
    }

    @PostMapping("/uninstall")
    public void uninstall(@RequestBody UninstallRequest request) {
        if (!StringUtils.hasText(request.getTaskId())) {
            throw new InvalidArgumentsException("uninstall taskId is empty");
        }

        deployService.uninstall(request);
    }
}
