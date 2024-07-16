package cn.edu.nju.ics.qtosplatform.domain.aggregator;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskStatus;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeployTask {
    private DeployTaskId id;

    private String serviceName;

    private ProjectId projectId;

    private MachineId machineId;

    private DeployTaskStatus status;

    private List<DeployTaskId> dependentTaskIds;
}
