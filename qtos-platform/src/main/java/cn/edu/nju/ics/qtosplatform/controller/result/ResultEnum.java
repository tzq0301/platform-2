package cn.edu.nju.ics.qtosplatform.controller.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(0, "success"),
    ERROR(1, "error"),
    INVALID_ARGUMENTS(2, "invalid arguments"),
    ;

    private final int code;

    private final String message;
}
