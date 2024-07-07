package cn.edu.nju.ics.qtosplatform.controller;

import cn.edu.nju.ics.qtosplatform.entity.model.ProjectsDTO;
import cn.edu.nju.ics.qtosplatform.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public ProjectsDTO projects() {
        return projectService.listProjects();
    }
}
