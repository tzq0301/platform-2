package cn.edu.nju.ics.qtosplatform.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeployTaskDependencyPO {
    private Integer id;
    private String sourceDeployTaskId;
    private String targetDeployTaskId;

    public DeployTaskDependencyPO(String sourceDeployTaskId, String targetDeployTaskId) {
        this.sourceDeployTaskId = sourceDeployTaskId;
        this.targetDeployTaskId = targetDeployTaskId;
    }
}
