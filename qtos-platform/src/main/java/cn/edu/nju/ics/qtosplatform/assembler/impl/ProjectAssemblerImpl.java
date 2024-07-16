package cn.edu.nju.ics.qtosplatform.assembler.impl;

import cn.edu.nju.ics.qtosplatform.assembler.DeployTaskAssembler;
import cn.edu.nju.ics.qtosplatform.assembler.MachineAssembler;
import cn.edu.nju.ics.qtosplatform.assembler.ProjectAssembler;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.entity.Project;
import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.MachineDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectAssemblerImpl implements ProjectAssembler {
    private final DeployTaskAssembler deployTaskAssembler;

    private final MachineAssembler machineAssembler;

    public ProjectAssemblerImpl(DeployTaskAssembler deployTaskAssembler,
                                MachineAssembler machineAssembler) {
        this.deployTaskAssembler = deployTaskAssembler;
        this.machineAssembler = machineAssembler;
    }

    @Override
    public ProjectDTO toDTO(Project project, List<Machine> machines, List<DeployTask> deployTasks) {
        List<MachineDTO> machineDTOs = machines.stream().map(machineAssembler::toDTO).toList();
        List<DeployTaskDTO> deployTaskDTOs = deployTasks.stream().map(deployTaskAssembler::toDTO).toList();
        return new ProjectDTO(project.getId(), project.getName(), deployTaskDTOs, machineDTOs);
    }
}
