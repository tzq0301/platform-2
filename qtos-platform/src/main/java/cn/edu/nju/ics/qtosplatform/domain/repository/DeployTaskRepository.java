package cn.edu.nju.ics.qtosplatform.domain.repository;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.domain.aggregator.DeployTask;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskStatus;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeployTaskRepository {
    void create(@NonNull DeployTask deployTask);

    void updateStatus(@NonNull DeployTaskId taskId, @NonNull DeployTaskStatus status);

    @NonNull
    DeployTask findById(@NonNull DeployTaskId taskId);

    List<DeployTask> listByProjectId(@NonNull ProjectId projectId);
}
