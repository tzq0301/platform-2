package cn.edu.nju.ics.qtosplatform.model.entity;

public enum DeployTaskStatusEnum {
    UNDEPLOY(0), DEPLOYED(1);

    private final Integer status;

    DeployTaskStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
