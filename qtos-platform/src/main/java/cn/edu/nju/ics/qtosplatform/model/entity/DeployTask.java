package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeployTask {
    private String id;
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private DeployTaskStatusEnum status;
    private List<String> dependentTaskIds;
}
