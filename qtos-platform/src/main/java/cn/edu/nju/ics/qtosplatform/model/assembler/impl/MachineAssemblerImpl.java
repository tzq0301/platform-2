package cn.edu.nju.ics.qtosplatform.model.assembler.impl;

import cn.edu.nju.ics.qtosplatform.model.assembler.MachineAssembler;
import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.dto.MachineDTO;
import org.springframework.stereotype.Component;

@Component
public class MachineAssemblerImpl implements MachineAssembler {
    @Override
    public MachineDTO toDTO(Machine machine) {
        return new MachineDTO(machine.getId(), machine.getAlias());
    }
}
