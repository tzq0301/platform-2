package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeployTask {
    private String id;

    private String serviceName;

    private Long projectId;

    private Long machineId;

    private DeployTaskStatus status;

    private List<String> dependentTaskIds;

    public DeployTask(String id, String serviceName, Long projectId, Long machineId, Integer status) {
        this(id, serviceName, projectId, machineId, DeployTaskStatus.from(status), new ArrayList<>());
    }

    public DeployTask(String id, String serviceName, Long projectId, Long machineId, Integer status, List<String> dependentTaskIds) {
        this(id, serviceName, projectId, machineId, DeployTaskStatus.from(status), dependentTaskIds);
    }
}
