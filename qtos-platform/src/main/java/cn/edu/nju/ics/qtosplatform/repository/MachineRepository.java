package cn.edu.nju.ics.qtosplatform.repository;

import cn.edu.nju.ics.qtosplatform.model.entity.Machine;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MachineRepository {
    @NonNull
    Machine findById(@NonNull Long id);

    List<Machine> listByProjectId(@NonNull Long projectId);
}
