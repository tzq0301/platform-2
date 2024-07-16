package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    private Long id;
    private String alias;
    private MachineOsEnum os;
    private String host;
    private Long projectId;
}
