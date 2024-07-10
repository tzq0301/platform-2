package cn.edu.nju.ics.qtosplatform.model.converter;

import cn.edu.nju.ics.qtosplatform.model.po.MachinePO;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.entity.OsEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachineConverter {
    Machine toMachine(MachinePO machinePO);

    default OsEnum map(Integer value) {
        return switch (value) {
            case 0 -> OsEnum.LINUX;
            case 1 -> OsEnum.WINDOWS;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}
