package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.Data;

@Data
public class Machine {
    private Long id;
    private String alias;
    private OsEnum os;
    private String host;
    private Long projectId;
}
