package cn.edu.nju.ics.qtosplatform.model.converter;

import cn.edu.nju.ics.qtosplatform.model.po.ProjectPO;
import cn.edu.nju.ics.qtosplatform.model.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectConverter {
    Project toProject(ProjectPO projectPO);
}
