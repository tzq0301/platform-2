package cn.edu.nju.ics.qtosplatform.model.po;

import lombok.Data;

@Data
public class DeployTaskPO {
    private String id;
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private Integer status;
}
