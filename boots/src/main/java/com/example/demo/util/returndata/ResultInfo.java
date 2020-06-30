package com.example.demo.util.returndata;

public class ResultInfo<T> {
    //通用返回信息对象类
    private int code;   //返回状态码
    private String msg;	//返回信息
    private T data;     //返回数据

    public ResultInfo<T> setCode(int code){
        this.code = code;
        return this;
    }

    public int getCode(){
        return this.code;
    }

    public ResultInfo<T> setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public String getMsg(){
        return this.msg;
    }

    public ResultInfo<T> setData(T data){
        this.data = data;
        return this;
    }

    public T getData(){
        return this.data;
    }

}
