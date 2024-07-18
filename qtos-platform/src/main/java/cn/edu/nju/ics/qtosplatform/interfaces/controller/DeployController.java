package cn.edu.nju.ics.qtosplatform.interfaces.controller;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.model.command.InstallCommand;
import cn.edu.nju.ics.qtosplatform.model.command.UninstallCommand;
import cn.edu.nju.ics.qtosplatform.model.command.UploadCommand;
import cn.edu.nju.ics.qtosplatform.model.dto.request.InstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UninstallRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.request.UploadRequest;
import cn.edu.nju.ics.qtosplatform.model.dto.response.UploadResponse;
import cn.edu.nju.ics.qtosplatform.service.DeployService;
import jakarta.validation.Valid;
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
    public UploadResponse upload(@Valid UploadRequest request) throws IOException {
        var command = new UploadCommand(request.serviceName(), new ProjectId(request.projectId()), new MachineId(request.machineId()), request.dependentTaskIds().stream().map(DeployTaskId::new).toList(), request.file());
        return deployService.upload(command);
    }

    @PostMapping("/install")
    public void install(@Valid @RequestBody InstallRequest request) {
        System.out.println(request.taskId());
        var command = new InstallCommand(new DeployTaskId(request.taskId()));
        deployService.install(command);
    }

    @PostMapping("/uninstall")
    public void uninstall(@Valid @RequestBody UninstallRequest request) {
        var command = new UninstallCommand(new DeployTaskId(request.taskId()));
        deployService.uninstall(command);
    }
}
