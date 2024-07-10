package cn.edu.nju.ics.qtosplatform.model.assembler;

import cn.edu.nju.ics.qtosplatform.model.dto.MachineDTO;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachineAssembler {
    MachineDTO toDTO(Machine machine);
}
