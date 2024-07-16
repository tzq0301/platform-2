package cn.edu.nju.ics.qtosplatform.model.converter;

import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.po.MachinePO;

public interface MachineConverter {
    Machine toMachine(MachinePO machinePO);
}
