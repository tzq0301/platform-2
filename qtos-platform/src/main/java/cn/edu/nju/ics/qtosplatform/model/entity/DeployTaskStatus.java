package cn.edu.nju.ics.qtosplatform.model.entity;

public enum DeployTaskStatus {
    UNDEPLOY(0), DEPLOYED(1);

    private final Integer status;

    DeployTaskStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
