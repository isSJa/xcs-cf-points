package com.issja.utils;

import lombok.Data;

/**
 * @Auth ： isSJa
 * @Time ： 2022/12/29 22:44
 **/
@Data
public class Result {
    private static final Integer SUCCESS_CODE=200;
    private static final Integer ERROR_CODE=500;

    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Integer code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result success(String msg,Object data){
        return new Result(SUCCESS_CODE,msg,data);
    }

    public static Result success(Object data){
        return new Result(SUCCESS_CODE,"操作成功",data);
    }

    public static Result success(){
        return new Result(SUCCESS_CODE,"操作成功",null);
    }

    public static Result error(Integer code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result error(String msg,Object data){
        return new Result(ERROR_CODE,msg,data);
    }

    public static Result error(Object data){
        return new Result(ERROR_CODE,"操作失败",data);
    }

    public static Result error(){
        return new Result(ERROR_CODE,"操作成功",null);
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
