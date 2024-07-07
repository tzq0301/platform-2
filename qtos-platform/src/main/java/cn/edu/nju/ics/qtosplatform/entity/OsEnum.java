package cn.edu.nju.ics.qtosplatform.entity;

public enum OsEnum {
    LINUX(0), WINDOWS(1);

    private final Integer type;

    OsEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
