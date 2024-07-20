package cn.edu.nju.ics.qtosplatform.converter;

import cn.edu.nju.ics.qtosplatform.domain.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.po.MachinePO;

public interface MachineConverter {
    Machine toMachine(MachinePO machinePO);
}
