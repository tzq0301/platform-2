package cn.edu.nju.ics.qtosplatform.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeployTaskPO {
    private String id;
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private Integer status;
}
