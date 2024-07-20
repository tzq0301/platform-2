package cn.edu.nju.ics.qtosplatform.domain.repository;

import cn.edu.nju.ics.qtosplatform.domain.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> listProjects();
}
