package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.entity.Project;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectsResponseDTO;
import cn.edu.nju.ics.qtosplatform.repository.DeployTaskRepository;
import cn.edu.nju.ics.qtosplatform.repository.MachineRepository;
import cn.edu.nju.ics.qtosplatform.repository.ProjectRepository;
import cn.edu.nju.ics.qtosplatform.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final DeployTaskRepository deployTaskRepository;

    private final MachineRepository machineRepository;

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(DeployTaskRepository deployTaskRepository,
                              MachineRepository machineRepository,
                              ProjectRepository projectRepository) {
        this.deployTaskRepository = deployTaskRepository;
        this.machineRepository = machineRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectsResponseDTO listProjects() {
        ProjectsResponseDTO projectsResponseDTO = new ProjectsResponseDTO(new ArrayList<>());

        var projects = projectRepository.listProjects();
        for (Project project : projects) {
            projectsResponseDTO.getProjects().add(new ProjectsResponseDTO.Project(project.getId(), project.getName()));
        }

        for (ProjectsResponseDTO.Project project : projectsResponseDTO.getProjects()) {
            List<DeployTask> deployTasks = deployTaskRepository.listByProjectId(project.getId());
            for (DeployTask deployTask : deployTasks) {
                project.getTasks().add(new ProjectsResponseDTO.Task(deployTask.getId(), deployTask.getServiceName(), deployTask.getStatus().getStatus()));
            }
            for (Machine machine : machineRepository.listByProjectId(project.getId())) {
                project.getMachines().add(new ProjectsResponseDTO.Machine(machine.getId(), machine.getAlias()));
            }
        }

        return projectsResponseDTO;
    }
}
