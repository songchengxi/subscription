package com.scx.subscription.qrcode.pojo;

/**
 * 微信返回的结果对象
 */
public class WechatResult {

    private boolean success;

    private int type;

    private Object object;

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "WechatResult{" +
                "success=" + success +
                ", type=" + type +
                ", object=" + object +
                ", msg='" + msg + '\'' +
                '}';
    }
}
