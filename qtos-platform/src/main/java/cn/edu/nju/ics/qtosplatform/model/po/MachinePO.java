package cn.edu.nju.ics.qtosplatform.model.po;

import lombok.Data;

@Data
public class MachinePO {
    private Long id;
    private String alias;
    private Integer os;
    private String host;
    private Long projectId;
}
