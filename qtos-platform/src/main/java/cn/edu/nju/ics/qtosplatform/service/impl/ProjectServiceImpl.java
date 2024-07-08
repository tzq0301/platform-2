package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.entity.Machine;
import cn.edu.nju.ics.qtosplatform.entity.Project;
import cn.edu.nju.ics.qtosplatform.entity.model.ProjectsDTO;
import cn.edu.nju.ics.qtosplatform.infrastructure.repository.DeployTaskRepository;
import cn.edu.nju.ics.qtosplatform.infrastructure.repository.MachineRepository;
import cn.edu.nju.ics.qtosplatform.infrastructure.repository.ProjectRepository;
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
    public ProjectsDTO listProjects() {
        ProjectsDTO projectsDTO = new ProjectsDTO(new ArrayList<>());

        var projects = projectRepository.listProjects();
        for (Project project : projects) {
            projectsDTO.getProjects().add(new ProjectsDTO.Project(project.getId(), project.getName()));
        }

        for (ProjectsDTO.Project project : projectsDTO.getProjects()) {
            List<DeployTask> deployTasks = deployTaskRepository.listByProjectId(project.getId());
            for (DeployTask deployTask : deployTasks) {
                project.getTasks().add(new ProjectsDTO.Task(deployTask.getId(), deployTask.getServiceName(), deployTask.getStatus().getStatus()));
            }
            for (Machine machine : machineRepository.listByProjectId(project.getId())) {
                project.getMachines().add(new ProjectsDTO.Machine(machine.getId(), machine.getAlias()));
            }
        }

        return projectsDTO;
    }
}
