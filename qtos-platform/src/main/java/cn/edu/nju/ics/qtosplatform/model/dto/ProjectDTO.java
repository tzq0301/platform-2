package cn.edu.nju.ics.qtosplatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private List<DeployTaskDTO> tasks;
    private List<MachineDTO> machines;
}
