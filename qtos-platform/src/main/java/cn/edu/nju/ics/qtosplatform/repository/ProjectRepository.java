package cn.edu.nju.ics.qtosplatform.repository;

import cn.edu.nju.ics.qtosplatform.domain.aggregator.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> listProjects();
}
