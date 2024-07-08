package cn.edu.nju.ics.qtosplatform.infrastructure.repository.impl;

import cn.edu.nju.ics.qtosplatform.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.entity.DeployTaskStatusEnum;
import cn.edu.nju.ics.qtosplatform.infrastructure.repository.DeployTaskRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeployTaskRepositoryImpl implements DeployTaskRepository {
    private final JdbcClient jdbcClient;

    public DeployTaskRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void create(@NonNull DeployTask deployTask) {
        jdbcClient.sql("""
                        INSERT INTO `deploy_task`(`id`, `service_name`, `project_id`, `machine_id`, `status`)
                        VALUES (:id, :serviceName, :projectId, :machineId, :status)
                        """)
                .param("id", deployTask.getId())
                .param("serviceName", deployTask.getServiceName())
                .param("projectId", deployTask.getProjectId())
                .param("machineId", deployTask.getMachineId())
                .param("status", deployTask.getStatus().getStatus())
                .update();

        deployTask.getDependentTaskIds().forEach(dependentTaskId ->
                jdbcClient.sql("""
                                INSERT INTO `deploy_task_dependency`(`source_deploy_task_id`, `target_deploy_task_id`)
                                VALUES (:sourceDeployTaskId, :targetDeployTaskId)
                                """)
                        .param("sourceDeployTaskId", deployTask.getId())
                        .param("targetDeployTaskId", dependentTaskId)
                        .update()
        );
    }

    @Override
    public void updateStatus(@NonNull String taskId, @NonNull DeployTaskStatusEnum status) {
        jdbcClient.sql("""
                        UPDATE `deploy_task`
                        SET `status` = :status
                        WHERE `id` = :taskId
                        """)
                .param("status", status.getStatus())
                .param("taskId", taskId)
                .update();
    }

    @Override
    @NonNull
    public DeployTask findById(@NonNull String taskId) {
        var deployTask = jdbcClient
                .sql("""
                        SELECT *
                        FROM `deploy_task`
                        WHERE `id` = :id
                        LIMIT 1
                        """)
                .param("id", taskId)
                .query(DeployTask.class)
                .optional()
                .orElseThrow();

        var dependentTaskIds = jdbcClient
                .sql("""
                        SELECT `target_deploy_task_id`
                        FROM `deploy_task_dependency`
                        WHERE `source_deploy_task_id` = :sourceDeployTaskId
                        """)
                .param("sourceDeployTaskId", taskId)
                .query(String.class)
                .list();

        deployTask.setDependentTaskIds(dependentTaskIds);

        return deployTask;
    }

    @Override
    public List<DeployTask> listByProjectId(@NonNull Long projectId) {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `deploy_task`
                        WHERE `project_id` = :projectId
                        """)
                .param("projectId", projectId)
                .query(DeployTask.class)
                .list();
    }
}
