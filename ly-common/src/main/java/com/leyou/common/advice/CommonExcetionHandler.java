package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @version : 1.0
 * @ClassName: CommonExcetionHandler
 * @Description : 处理异常
 * @auther: hejia
 * @date: 2019/3/28
 */
@ControllerAdvice //拦截所有的Controller
public class CommonExcetionHandler {
    //quxiao
    @ExceptionHandler(LyException.class)
    public ResponseEntity<String> handlerExcetion(LyException e){
        ExceptionEnums em = e.getExceptionEnums();
        return ResponseEntity.status(em.getCode()).body(em.getMsg());
    }
}
