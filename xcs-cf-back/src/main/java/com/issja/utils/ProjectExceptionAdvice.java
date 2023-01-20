package com.issja.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auth ： isSJa
 * @Time ： 2022/12/23 19:43
 **/
// 作为springmvc的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 拦截所有的异常信息
    @ExceptionHandler
    public Result doException(Exception exception) {
        exception.printStackTrace();
        return Result.error("服务器故障，请稍后再试", null);
    }
}
