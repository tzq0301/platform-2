package cn.edu.nju.ics.qtosplatform.infrastructure.repository;

import cn.edu.nju.ics.qtosplatform.converter.MachineConverter;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import cn.edu.nju.ics.qtosplatform.domain.entity.Machine;
import cn.edu.nju.ics.qtosplatform.model.po.MachinePO;
import cn.edu.nju.ics.qtosplatform.domain.repository.MachineRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MachineRepositoryImpl implements MachineRepository {
    private final JdbcClient jdbcClient;

    private final MachineConverter machineConverter;

    public MachineRepositoryImpl(JdbcClient jdbcClient,
                                 MachineConverter machineConverter) {
        this.jdbcClient = jdbcClient;
        this.machineConverter = machineConverter;
    }

    @Override
    @NonNull
    public Machine findById(@NonNull MachineId id) {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `machine`
                        WHERE `id` = :id
                        LIMIT 1
                        """)
                .param("id", id.value())
                .query(MachinePO.class)
                .optional()
                .map(machineConverter::toMachine)
                .orElseThrow();
    }

    @Override
    public List<Machine> listByProjectId(@NonNull ProjectId projectId) {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `machine`
                        WHERE `project_id` = :projectId
                        """)
                .param("projectId", projectId.value())
                .query(MachinePO.class)
                .list()
                .stream()
                .map(machineConverter::toMachine)
                .toList();
    }
}
