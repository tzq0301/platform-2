package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.Getter;

@Getter
public enum DeployTaskStatus {
    UNDEPLOY(0),
    DEPLOYED(1),
    FAILED(2),
    ;

    private final Integer status;

    DeployTaskStatus(Integer status) {
        this.status = status;
    }

    public static DeployTaskStatus from(Integer status) {
        return switch (status) {
            case 0 -> DeployTaskStatus.UNDEPLOY;
            case 1 -> DeployTaskStatus.DEPLOYED;
            case 2 -> DeployTaskStatus.FAILED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
