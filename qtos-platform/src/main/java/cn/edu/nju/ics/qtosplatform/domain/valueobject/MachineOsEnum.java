package cn.edu.nju.ics.qtosplatform.domain.valueobject;

import lombok.Getter;

@Getter
public enum MachineOsEnum {
    LINUX(0), WINDOWS(1);

    private final Integer type;

    MachineOsEnum(Integer type) {
        this.type = type;
    }

    public static MachineOsEnum from(Integer type) {
        return switch (type) {
            case 0 -> MachineOsEnum.LINUX;
            case 1 -> MachineOsEnum.WINDOWS;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
