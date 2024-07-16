package cn.edu.nju.ics.qtosplatform.converter.impl;

import cn.edu.nju.ics.qtosplatform.converter.ProjectConverter;
import cn.edu.nju.ics.qtosplatform.model.entity.Project;
import cn.edu.nju.ics.qtosplatform.model.po.ProjectPO;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverterImpl implements ProjectConverter {
    @Override
    public Project toProject(ProjectPO projectPO) {
        return new Project(projectPO.getId(), projectPO.getName());
    }
}
