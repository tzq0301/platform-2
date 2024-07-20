package cn.edu.nju.ics.qtosplatform.domain.repository;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.domain.entity.Machine;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MachineRepository {
    @NonNull
    Machine findById(@NonNull MachineId id);

    List<Machine> listByProjectId(@NonNull ProjectId projectId);
}
