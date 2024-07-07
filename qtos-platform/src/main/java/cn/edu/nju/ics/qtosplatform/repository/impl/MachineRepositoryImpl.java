package cn.edu.nju.ics.qtosplatform.repository.impl;

import cn.edu.nju.ics.qtosplatform.entity.Machine;
import cn.edu.nju.ics.qtosplatform.repository.MachineRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MachineRepositoryImpl implements MachineRepository {
    private final JdbcClient jdbcClient;

    public MachineRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    @NonNull
    public Machine findById(@NonNull Long id) {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `machine`
                        WHERE `id` = :id
                        LIMIT 1
                        """)
                .param("id", id)
                .query(Machine.class)
                .optional()
                .orElseThrow();
    }

    @Override
    public List<Machine> listByProjectId(@NonNull Long projectId) {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `machine`
                        WHERE `project_id` = :projectId
                        """)
                .param("projectId", projectId)
                .query(Machine.class)
                .list();
    }
}
