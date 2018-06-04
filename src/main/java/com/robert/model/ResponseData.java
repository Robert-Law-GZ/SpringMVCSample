package com.robert.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
    private int code = 200;
    private String msg = "请求成功";
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setListData(Object data){
        Map map=new HashMap();
        map.put("list",data);
        this.data=map;
    }

}
