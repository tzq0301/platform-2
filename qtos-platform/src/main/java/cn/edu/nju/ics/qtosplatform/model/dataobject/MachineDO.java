package cn.edu.nju.ics.qtosplatform.model.dataobject;

import lombok.Data;

@Data
public class MachineDO {
    private Long id;
    private String alias;
    private Integer os;
    private String host;
    private Long projectId;
}
