package com.wen.pojo;

public class AjaxResult {
    private Integer code; //代码标识
    private String mesg; //提示消息
    private boolean success; //是否执行成功
    private Object obj; //传入前台的数据

    public AjaxResult() {
    }

    public AjaxResult(Integer code, String mesg, boolean success, Object obj) {
        this.code = code;
        this.mesg = mesg;
        this.success = success;
        this.obj = obj;
    }

    public AjaxResult(String mesg, boolean success) {
        this.mesg = mesg;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
