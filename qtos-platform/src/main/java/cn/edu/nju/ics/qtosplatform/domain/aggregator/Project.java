package cn.edu.nju.ics.qtosplatform.domain.aggregator;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private ProjectId id;
    private String name;
}
