package cn.edu.nju.ics.qtosplatform.assembler.impl;

import cn.edu.nju.ics.qtosplatform.assembler.MachineAssembler;
import cn.edu.nju.ics.qtosplatform.domain.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.dto.MachineDTO;
import org.springframework.stereotype.Component;

@Component
public class MachineAssemblerImpl implements MachineAssembler {
    @Override
    public MachineDTO toDTO(Machine machine) {
        return new MachineDTO(machine.getId().value(), machine.getAlias());
    }
}
