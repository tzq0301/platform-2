package cn.edu.nju.ics.qtosplatform.interfaces.controller;

import cn.edu.nju.ics.qtosplatform.exception.InvalidArgumentsException;
import cn.edu.nju.ics.qtosplatform.model.dto.InstallCommandDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.InstallRequestDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadCommandDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.UploadResponseDTO;
import cn.edu.nju.ics.qtosplatform.service.DeployService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping("/upload")
    public UploadResponseDTO upload(@RequestParam("serviceName") String serviceName,
                                    @RequestParam("projectId") Long projectId,
                                    @RequestParam("machineId") Long machineId,
                                    @RequestParam("dependentTaskIds") String[] dependentTaskIds,
                                    @RequestParam("file") MultipartFile archived) throws IOException {
        var command = new UploadCommandDTO(serviceName, projectId, machineId, dependentTaskIds, archived);
        return deployService.upload(command);
    }

    @PostMapping("/install")
    public void install(@RequestBody InstallRequestDTO request) {
        String taskId = request.getTaskId();

        if (!StringUtils.hasText(taskId)) {
            throw new InvalidArgumentsException("taskId is empty");
        }

        InstallCommandDTO command = new InstallCommandDTO(taskId);
        deployService.install(command);
    }
}
