package cn.edu.nju.ics.qtosplatform.infrastructure.repository.impl;

import cn.edu.nju.ics.qtosplatform.infrastructure.repository.ProjectRepository;
import cn.edu.nju.ics.qtosplatform.model.converter.ProjectConverter;
import cn.edu.nju.ics.qtosplatform.model.dataobject.ProjectDO;
import cn.edu.nju.ics.qtosplatform.model.entity.Project;
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
                .query(ProjectDO.class)
                .stream()
                .map(projectConverter::toProject)
                .toList();
    }
}
