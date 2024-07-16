package cn.edu.nju.ics.qtosplatform.domain.aggregator;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineOsEnum;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    private MachineId id;
    private String alias;
    private MachineOsEnum os;
    private String host;
    private ProjectId projectId;
}
