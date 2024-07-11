package cn.edu.nju.ics.qtosplatform.model.entity;

import lombok.Getter;

@Getter
public enum OsEnum {
    LINUX(0), WINDOWS(1);

    private final Integer type;

    OsEnum(Integer type) {
        this.type = type;
    }

    public static OsEnum from(Integer type) {
        return switch (type) {
            case 0 -> OsEnum.LINUX;
            case 1 -> OsEnum.WINDOWS;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
