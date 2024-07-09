package cn.edu.nju.ics.qtosplatform.model.converter;

import cn.edu.nju.ics.qtosplatform.model.dataobject.MachineDO;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.entity.OsEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachineConverter {
    Machine toMachine(MachineDO machineDO);

    default OsEnum map(Integer value) {
        return switch (value) {
            case 0:
                yield OsEnum.LINUX;
            case 1:
                yield OsEnum.WINDOWS;
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}
