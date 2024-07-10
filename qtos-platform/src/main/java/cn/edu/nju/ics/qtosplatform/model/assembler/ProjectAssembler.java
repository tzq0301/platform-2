package cn.edu.nju.ics.qtosplatform.model.assembler;

import cn.edu.nju.ics.qtosplatform.model.dto.DeployTaskDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.MachineDTO;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectDTO;
import cn.edu.nju.ics.qtosplatform.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectAssembler {
    @Mappings(value = {
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "machines", ignore = true)
    })
    ProjectDTO toDTO(Project project);

    default ProjectDTO toDTO(Project project, List<MachineDTO> machineDTOs, List<DeployTaskDTO> deployTaskDTOs) {
        ProjectDTO projectDTO = toDTO(project);
        projectDTO.setMachines(machineDTOs);
        projectDTO.setTasks(deployTaskDTOs);
        return projectDTO;
    }
}
