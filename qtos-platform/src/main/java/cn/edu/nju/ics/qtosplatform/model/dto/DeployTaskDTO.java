package cn.edu.nju.ics.qtosplatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeployTaskDTO {
    private String id;
    private String serviceName;
    private Integer status;
}
