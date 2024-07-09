package cn.edu.nju.ics.qtosplatform.interfaces.result;

import org.springframework.lang.Nullable;

public record Result<T>(int code, String message, @Nullable T data) {
    public Result(ResultEnum resultEnum, @Nullable T data) {
        this(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    public static <T> Result<T> success(@Nullable T data) {
        return new Result<>(ResultEnum.SUCCESS, data);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        return new Result<>(resultEnum, null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum, String message) {
        return new Result<>(resultEnum.getCode(), message, null);
    }
}
