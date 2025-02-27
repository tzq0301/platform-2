package cn.edu.nju.ics.qtosplatform.assembler;

import cn.edu.nju.ics.qtosplatform.domain.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.dto.MachineDTO;

public interface MachineAssembler {
    MachineDTO toDTO(Machine machine);
}
