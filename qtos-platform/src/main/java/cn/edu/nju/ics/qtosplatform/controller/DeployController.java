package cn.edu.nju.ics.qtosplatform.controller;

import cn.edu.nju.ics.qtosplatform.entity.model.InstallCommand;
import cn.edu.nju.ics.qtosplatform.entity.model.InstallRequest;
import cn.edu.nju.ics.qtosplatform.entity.model.UploadCommand;
import cn.edu.nju.ics.qtosplatform.entity.model.UploadDTO;
import cn.edu.nju.ics.qtosplatform.service.DeployService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping("/upload")
    public UploadDTO upload(@RequestParam("serviceName") String serviceName,
                            @RequestParam("projectId") Long projectId,
                            @RequestParam("machineId") Long machineId,
                            @RequestParam("dependentTaskIds") String[] dependentTaskIds,
                            @RequestParam("file") MultipartFile archived) throws IOException {
        var command = new UploadCommand(serviceName, projectId, machineId, dependentTaskIds, archived);
        return deployService.upload(command);
    }

    @PostMapping("/install")
    public void install(@RequestBody InstallRequest request) {
        String taskId = Objects.requireNonNull(request.getTaskId());
        InstallCommand command = new InstallCommand(taskId);
        deployService.install(command);
    }
}
