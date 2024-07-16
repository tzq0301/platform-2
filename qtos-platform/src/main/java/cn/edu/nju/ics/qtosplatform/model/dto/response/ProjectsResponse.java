package cn.edu.nju.ics.qtosplatform.model.dto.response;

import cn.edu.nju.ics.qtosplatform.model.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectsResponse {
    List<ProjectDTO> projects;
}
