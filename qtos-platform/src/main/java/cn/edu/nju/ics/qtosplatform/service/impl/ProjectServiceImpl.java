package cn.edu.nju.ics.qtosplatform.service.impl;

import cn.edu.nju.ics.qtosplatform.model.assembler.DeployTaskAssembler;
import cn.edu.nju.ics.qtosplatform.model.assembler.MachineAssembler;
import cn.edu.nju.ics.qtosplatform.model.assembler.ProjectAssembler;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectsResponseDTO;
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

    private final DeployTaskAssembler deployTaskAssembler;

    private final MachineAssembler machineAssembler;

    private final ProjectAssembler projectAssembler;

    public ProjectServiceImpl(DeployTaskRepository deployTaskRepository,
                              MachineRepository machineRepository,
                              ProjectRepository projectRepository,
                              DeployTaskAssembler deployTaskAssembler,
                              MachineAssembler machineAssembler,
                              ProjectAssembler projectAssembler) {
        this.deployTaskRepository = deployTaskRepository;
        this.machineRepository = machineRepository;
        this.projectRepository = projectRepository;
        this.deployTaskAssembler = deployTaskAssembler;
        this.machineAssembler = machineAssembler;
        this.projectAssembler = projectAssembler;
    }

    @Override
    public ProjectsResponseDTO listProjects() {
        List<ProjectDTO> projectDTOs = projectRepository.listProjects()
                .stream()
                .map(project -> {
                    var machineDTOs = machineRepository
                            .listByProjectId(project.getId())
                            .stream()
                            .map(machineAssembler::toDTO)
                            .toList();
                    var deployTaskDTOs = deployTaskRepository
                            .listByProjectId(project.getId())
                            .stream()
                            .map(deployTaskAssembler::toDTO)
                            .toList();
                    return projectAssembler.toDTO(project, machineDTOs, deployTaskDTOs);
                })
                .toList();
        return new ProjectsResponseDTO(projectDTOs);
    }
}
