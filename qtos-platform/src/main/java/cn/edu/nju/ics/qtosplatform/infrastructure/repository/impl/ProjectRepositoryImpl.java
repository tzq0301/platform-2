package cn.edu.nju.ics.qtosplatform.infrastructure.repository.impl;

import cn.edu.nju.ics.qtosplatform.converter.ProjectConverter;
import cn.edu.nju.ics.qtosplatform.domain.entity.Project;
import cn.edu.nju.ics.qtosplatform.infrastructure.repository.ProjectRepository;
import cn.edu.nju.ics.qtosplatform.model.po.ProjectPO;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JdbcClient jdbcClient;

    private final ProjectConverter projectConverter;

    public ProjectRepositoryImpl(JdbcClient jdbcClient,
                                 ProjectConverter projectConverter) {
        this.jdbcClient = jdbcClient;
        this.projectConverter = projectConverter;
    }

    @Override
    public List<Project> listProjects() {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `project`
                        """)
                .query(ProjectPO.class)
                .list()
                .stream()
                .map(projectConverter::toProject)
                .toList();
    }
}
