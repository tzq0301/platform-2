package cn.edu.nju.ics.qtosplatform.interfaces.controller.advice;

import cn.edu.nju.ics.qtosplatform.exception.InvalidArgumentsException;
import cn.edu.nju.ics.qtosplatform.interfaces.result.Result;
import cn.edu.nju.ics.qtosplatform.interfaces.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {
    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseBody
    public Result<?> handleInvalidArgumentsException(InvalidArgumentsException e) {
        log.error(e.getMessage());
        return Result.error(ResultEnum.INVALID_ARGUMENTS, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> handleException(Exception e) {
        log.error(e.getMessage());
        return Result.error(ResultEnum.ERROR);
    }
}
