package cn.edu.nju.ics.qtosplatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectsResponseDTO {
    List<ProjectDTO> projects;
}
