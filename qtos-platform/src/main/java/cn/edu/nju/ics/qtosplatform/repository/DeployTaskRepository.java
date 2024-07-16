package cn.edu.nju.ics.qtosplatform.repository;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatus;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeployTaskRepository {
    void create(@NonNull DeployTask deployTask);

    void updateStatus(@NonNull String taskId, @NonNull DeployTaskStatus status);

    @NonNull
    DeployTask findById(@NonNull String taskId);

    List<DeployTask> listByProjectId(@NonNull ProjectId projectId);
}
