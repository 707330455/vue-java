package com.example.demo.util.returndata;

public class Result {
    //通用返回结果的方法类

    private static final int okCode = 0;
    private static final int failCode = 1;
    private static final String okMsg = "操作成功";
    private static final String failMsg = "操作失败";
    //以上这些通常可以定义为一种枚举类，然后使用

    //成功返回方法
    public static <T> ResultInfo<T> Ok(){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(okCode).setMsg(okMsg);
        return r;
    }

    public static <T> ResultInfo<T> Ok(String msg){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(okCode).setMsg(msg);
        return r;
    }

    public static <T> ResultInfo<T> Ok(T data){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(okCode).setMsg(okMsg).setData(data);
        return r;
    }

    public static <T> ResultInfo<T> Ok(String msg, T data){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(okCode).setMsg(msg).setData(data);
        return r;
    }

    //失败返回方法
    public static <T> ResultInfo<T> Fail(){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(failCode).setMsg(failMsg);
        return r;
    }

    public static <T> ResultInfo<T> Fail(String msg){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(failCode).setMsg(msg);
        return r;
    }

    public static <T> ResultInfo<T> Fail(T data){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(failCode).setMsg(failMsg).setData(data);
        return r;
    }

    public static <T> ResultInfo<T> Fail(String msg, T data){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(failCode).setMsg(msg).setData(data);
        return r;
    }

    //通用返回方法
    public static <T> ResultInfo<T> result(int code, String msg){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(code).setMsg(msg);
        return r;
    }

    public static <T> ResultInfo<T> result(int code, String msg, T data){
        ResultInfo<T> r = new ResultInfo<T>();
        r.setCode(code).setMsg(msg).setData(data);
        return r;
    }


}
