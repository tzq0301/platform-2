package cn.edu.nju.ics.qtosplatform.repository;

import cn.edu.nju.ics.qtosplatform.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.entity.DeployTaskStatusEnum;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeployTaskRepository {
    void create(@NonNull DeployTask deployTask);

    void updateStatus(@NonNull String taskId, @NonNull DeployTaskStatusEnum status);

    @NonNull
    DeployTask findById(@NonNull String taskId);

    List<DeployTask> listByProjectId(@NonNull Long projectId);
}
