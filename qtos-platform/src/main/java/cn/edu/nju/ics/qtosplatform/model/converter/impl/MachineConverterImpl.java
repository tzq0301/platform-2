package cn.edu.nju.ics.qtosplatform.model.converter.impl;

import cn.edu.nju.ics.qtosplatform.model.converter.MachineConverter;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.entity.MachineOsEnum;
import cn.edu.nju.ics.qtosplatform.model.po.MachinePO;
import org.springframework.stereotype.Component;

@Component
public class MachineConverterImpl implements MachineConverter {
    @Override
    public Machine toMachine(MachinePO machinePO) {
        return new Machine(machinePO.getId(), machinePO.getAlias(), MachineOsEnum.from(machinePO.getOs()), machinePO.getHost(), machinePO.getProjectId());
    }
}
