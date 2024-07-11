package cn.edu.nju.ics.qtosplatform.interfaces.controller;

import cn.edu.nju.ics.qtosplatform.exception.InvalidArgumentsException;
import cn.edu.nju.ics.qtosplatform.model.assembler.DeployTaskAssembler;
import cn.edu.nju.ics.qtosplatform.model.dto.InstallRequestDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UninstallRequestDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadRequestDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadResponseDTO;
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

    private final DeployTaskAssembler deployTaskAssembler;

    public DeployController(DeployService deployService,
                            DeployTaskAssembler deployTaskAssembler) {
        this.deployService = deployService;
        this.deployTaskAssembler = deployTaskAssembler;
    }

    @PostMapping("/upload")
    public UploadResponseDTO upload(UploadRequestDTO request) throws IOException {
        var command = deployTaskAssembler.toUploadCommandDTO(request);
        return deployService.upload(command);
    }

    @PostMapping("/install")
    public void install(@RequestBody InstallRequestDTO request) {
        if (!StringUtils.hasText(request.getTaskId())) {
            throw new InvalidArgumentsException("install taskId is empty");
        }

        var command = deployTaskAssembler.toInstallCommandDTO(request);
        deployService.install(command);
    }

    @PostMapping("/uninstall")
    public void uninstall(@RequestBody UninstallRequestDTO request) {
        if (!StringUtils.hasText(request.getTaskId())) {
            throw new InvalidArgumentsException("uninstall taskId is empty");
        }

        var command = deployTaskAssembler.toUninstallCommandDTO(request);
        deployService.uninstall(command);
    }
}
