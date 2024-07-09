package cn.edu.nju.ics.qtosplatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ProjectsResponseDTO {
    List<Project> projects;

    @Data
    public static class Project {
        private Long id;
        private String name;
        private List<Task> tasks;
        private List<Machine> machines;

        public Project(Long id, String name) {
            this.id = id;
            this.name = name;
            this.tasks = new ArrayList<>();
            this.machines = new ArrayList<>();
        }
    }

    @Data
    @AllArgsConstructor
    public static class Task {
        private String id;
        private String serviceName;
        private Integer status;
    }

    @Data
    @AllArgsConstructor
    public static class Machine {
        private Long id;
        private String alias;
    }
}
