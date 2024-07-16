package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.model.assembler.ProjectAssembler;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.response.ProjectsResponse;
import cn.edu.nju.ics.qtosplatform.repository.DeployTaskRepository;
import cn.edu.nju.ics.qtosplatform.repository.MachineRepository;
import cn.edu.nju.ics.qtosplatform.repository.ProjectRepository;
import cn.edu.nju.ics.qtosplatform.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final DeployTaskRepository deployTaskRepository;

    private final MachineRepository machineRepository;

    private final ProjectRepository projectRepository;

    private final ProjectAssembler projectAssembler;

    public ProjectServiceImpl(DeployTaskRepository deployTaskRepository,
                              MachineRepository machineRepository,
                              ProjectRepository projectRepository,
                              ProjectAssembler projectAssembler) {
        this.deployTaskRepository = deployTaskRepository;
        this.machineRepository = machineRepository;
        this.projectRepository = projectRepository;
        this.projectAssembler = projectAssembler;
    }

    @Override
    public ProjectsResponse listProjects() {
        List<ProjectDTO> projectDTOs = projectRepository.listProjects()
                .stream()
                .map(project -> {
                    List<Machine> machines = machineRepository.listByProjectId(project.getId());
                    List<DeployTask> deployTasks = deployTaskRepository.listByProjectId(project.getId());
                    return projectAssembler.toDTO(project, machines, deployTasks);
                })
                .toList();
        return new ProjectsResponse(projectDTOs);
    }
}
