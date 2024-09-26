package cn.edu.nju.ics.qtosbase.interfaces.controller;

import cn.edu.nju.ics.qtosbase.service.DeployService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("taskId") String taskId,
                         @RequestParam("file") MultipartFile archived) throws IOException {
        InputStream inputStream = archived.getInputStream();
        deployService.transport(taskId, inputStream);
    }

    @PostMapping("/install")
    public void install(@RequestBody Map<String, String> params) throws IOException, InterruptedException {
        String taskId = Objects.requireNonNull(params.get("taskId"));
        if (!StringUtils.hasText(taskId)) {
            throw new IllegalArgumentException("taskId is empty");
        }
        deployService.install(taskId);
    }

    @PostMapping("/uninstall")
    public void uninstall(@RequestBody Map<String, String> params) throws IOException, InterruptedException {
        String taskId = Objects.requireNonNull(params.get("taskId"));
        if (!StringUtils.hasText(taskId)) {
            throw new IllegalArgumentException("taskId is empty");
        }
        deployService.uninstall(taskId);
    }
}
