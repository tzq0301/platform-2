package cn.edu.nju.ics.qtosplatform.infrastructure.repository;

import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatusEnum;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeployTaskRepository {
    void create(@NonNull DeployTask deployTask);

    void updateStatus(@NonNull String taskId, @NonNull DeployTaskStatusEnum status);

    @NonNull
    DeployTask findById(@NonNull String taskId);

    List<DeployTask> listByProjectId(@NonNull Long projectId);
}
