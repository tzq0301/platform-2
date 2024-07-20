package cn.edu.nju.ics.qtosplatform.converter.impl;

import cn.edu.nju.ics.qtosplatform.converter.MachineConverter;
import cn.edu.nju.ics.qtosplatform.domain.entity.Machine;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineOsEnum;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.model.po.MachinePO;
import org.springframework.stereotype.Component;

@Component
public class MachineConverterImpl implements MachineConverter {
    @Override
    public Machine toMachine(MachinePO machinePO) {
        return new Machine(new MachineId(machinePO.getId()), machinePO.getAlias(), MachineOsEnum.from(machinePO.getOs()), machinePO.getHost(), new ProjectId(machinePO.getProjectId()));
    }
}
