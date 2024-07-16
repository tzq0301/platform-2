package cn.edu.nju.ics.qtosplatform.model.converter;

import cn.edu.nju.ics.qtosplatform.model.entity.Project;
import cn.edu.nju.ics.qtosplatform.model.po.ProjectPO;

public interface ProjectConverter {
    Project toProject(ProjectPO projectPO);
}
