package com.scx.subscription.tools.tuling.pojo;

/**
 * 返回参数基类
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class BaseInfo {
    //状态码
    private int code;
    //文字内容
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
