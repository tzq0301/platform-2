package cn.edu.nju.ics.qtosplatform.infrastructure.repository.impl;

import cn.edu.nju.ics.qtosplatform.entity.Project;
import cn.edu.nju.ics.qtosplatform.infrastructure.repository.ProjectRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JdbcClient jdbcClient;

    public ProjectRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Project> listProjects() {
        return jdbcClient.sql("""
                        SELECT *
                        FROM `project`
                        """)
                .query(Project.class)
                .list();
    }
}
