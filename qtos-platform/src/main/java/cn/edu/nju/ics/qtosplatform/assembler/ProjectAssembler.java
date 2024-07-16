package cn.edu.nju.ics.qtosplatform.assembler;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.domain.aggregator.Project;
import cn.edu.nju.ics.qtosplatform.model.dto.ProjectDTO;

import java.util.List;

public interface ProjectAssembler {
    ProjectDTO toDTO(Project project, List<Machine> machines, List<DeployTask> deployTasks);
}
