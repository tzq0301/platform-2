package cn.edu.nju.ics.qtosplatform.converter;

import cn.edu.nju.ics.qtosplatform.domain.entity.Project;
import cn.edu.nju.ics.qtosplatform.model.po.ProjectPO;

public interface ProjectConverter {
    Project toProject(ProjectPO projectPO);
}
