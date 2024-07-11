package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.Getter;

@Getter
public enum DeployTaskStatus {
    UNDEPLOY(0), DEPLOYED(1);

    private final Integer status;

    DeployTaskStatus(Integer status) {
        this.status = status;
    }

    public static DeployTaskStatus from(Integer status) {
        return switch (status) {
            case 0 -> DeployTaskStatus.UNDEPLOY;
            case 1 -> DeployTaskStatus.DEPLOYED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
