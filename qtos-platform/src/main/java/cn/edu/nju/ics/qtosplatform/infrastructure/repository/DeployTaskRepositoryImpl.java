package cn.edu.nju.ics.qtosplatform.infrastructure.repository;

import cn.edu.nju.ics.qtosplatform.converter.DeployTaskConverter;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTask;
import cn.edu.nju.ics.qtosplatform.model.entity.DeployTaskStatus;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskDependencyPO;
import cn.edu.nju.ics.qtosplatform.model.po.DeployTaskPO;
import cn.edu.nju.ics.qtosplatform.repository.DeployTaskRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeployTaskRepositoryImpl implements DeployTaskRepository {
    private final JdbcClient jdbcClient;

    private final DeployTaskConverter deployTaskConverter;

    public DeployTaskRepositoryImpl(JdbcClient jdbcClient,
                                    DeployTaskConverter deployTaskConverter) {
        this.jdbcClient = jdbcClient;
        this.deployTaskConverter = deployTaskConverter;
    }

    @Override
    public void create(@NonNull DeployTask deployTask) {
        var deployTaskPO = deployTaskConverter.toDeployTaskPO(deployTask);

        jdbcClient.sql("""
                        INSERT INTO `deploy_task`(`id`, `service_name`, `project_id`, `machine_id`, `status`)
                        VALUES (:id, :serviceName, :projectId, :machineId, :status)
                        """)
                .param("id", deployTaskPO.getId())
                .param("serviceName", deployTaskPO.getServiceName())
                .param("projectId", deployTaskPO.getProjectId())
                .param("machineId", deployTaskPO.getMachineId())
                .param("status", deployTaskPO.getStatus())
                .update();

        deployTaskConverter.toDeployTaskDependencies(deployTask).forEach(po ->
                jdbcClient.sql("""
                                INSERT INTO `deploy_task_dependency`(`source_deploy_task_id`, `target_deploy_task_id`)
                                VALUES (:sourceDeployTaskId, :targetDeployTaskId)
                                """)
                        .param("sourceDeployTaskId", po.getSourceDeployTaskId())
                        .param("targetDeployTaskId", po.getTargetDeployTaskId())
                        .update()
        );
    }

    @Override
    public void updateStatus(@NonNull String taskId, @NonNull DeployTaskStatus status) {
        jdbcClient.sql("""
                        UPDATE `deploy_task`
                        SET `status` = :status
                        WHERE `id` = :taskId
                        """)
                .param("status", status.getValue())
                .param("taskId", taskId)
                .update();
    }

    @Override
    @NonNull
    public DeployTask findById(@NonNull String taskId) {
        var deployTaskPO = jdbcClient
                .sql("""
                        SELECT *
                        FROM `deploy_task`
                        WHERE `id` = :id
                        LIMIT 1
                        """)
                .param("id", taskId)
                .query(DeployTaskPO.class)
                .optional()
                .orElseThrow();

        var dependentTaskDependencyPOs = jdbcClient
                .sql("""
                        SELECT *
                        FROM `deploy_task_dependency`
                        WHERE `source_deploy_task_id` = :sourceDeployTaskId
                        """)
                .param("sourceDeployTaskId", taskId)
                .query(DeployTaskDependencyPO.class)
                .list();

        return deployTaskConverter.toDeployTask(deployTaskPO, dependentTaskDependencyPOs);
    }

    @Override
    public List<DeployTask> listByProjectId(@NonNull Long projectId) {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `deploy_task`
                        WHERE `project_id` = :projectId
                        """)
                .param("projectId", projectId)
                .query(DeployTaskPO.class)
                .list()
                .stream()
                .map(deployTaskConverter::toDeployTask)
                .toList();
    }
}
