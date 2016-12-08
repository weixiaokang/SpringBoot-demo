package com.weixk.helloworld.exception;

import com.weixk.helloworld.domain.Result;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 *
 * Created by weixk on 16/12/7.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BindException.class)
    public Result validHandler(HttpServletRequest request, BindException exception) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            builder.append(error.getDefaultMessage());
        }
        return new Result(0, builder.toString());
    }
}
