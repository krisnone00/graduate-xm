package com.example.exception;


import   com.example.common.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//标识他是一个全局处理这样一个的类
@ControllerAdvice("com.example.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody  //返回json串
    public Result<Void> error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody  //返回json串
    public Result error(CustomException e){
        return Result.error(e.getCode(),e.getMsg());
    }

    // 新增：处理JSON参数校验异常（如@RequestBody）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleValidException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Result.error(400, errorMsg); // 直接返回验证信息
    }

    // 新增：处理表单参数校验异常（如@RequestParam）
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result handleBindException(BindException e) {
        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Result.error(400, errorMsg);
    }
}
