package cn.edu.nju.ics.qtosbase.controller;

import cn.edu.nju.ics.qtosbase.service.DeployService;
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
    public String upload(@RequestParam("file") MultipartFile archived) throws IOException {
        InputStream inputStream = archived.getInputStream();
        return deployService.transport(inputStream);
    }

    @PostMapping("/install")
    public void install(@RequestBody Map<String, String> params) throws IOException, InterruptedException {
        String taskId = Objects.requireNonNull(params.get("taskId"));
        deployService.install(taskId);
    }
}
