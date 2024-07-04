package cn.edu.nju.ics.qtosbase.controller;

import cn.edu.nju.ics.qtosbase.service.DeployService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping("/upload")
    public Map<?, ?> upload(@RequestParam("file") MultipartFile archived) throws IOException {
        InputStream inputStream = archived.getInputStream();
        return Map.of("taskId", deployService.transport(inputStream));
    }
}
