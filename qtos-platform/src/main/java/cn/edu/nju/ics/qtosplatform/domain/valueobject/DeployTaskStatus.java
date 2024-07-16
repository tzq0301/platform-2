package cn.edu.nju.ics.qtosplatform.domain.valueobject;

import lombok.Getter;

@Getter
public enum DeployTaskStatus {
    NOT_INSTALL_YET(0),
    INSTALLED(1),
    INSTALL_FAILED(2),
    UNINSTALLED(3),
    ;

    private final Integer value;

    DeployTaskStatus(Integer value) {
        this.value = value;
    }

    public static DeployTaskStatus from(Integer status) {
        return switch (status) {
            case 0 -> DeployTaskStatus.NOT_INSTALL_YET;
            case 1 -> DeployTaskStatus.INSTALLED;
            case 2 -> DeployTaskStatus.INSTALL_FAILED;
            case 3 -> DeployTaskStatus.UNINSTALLED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
