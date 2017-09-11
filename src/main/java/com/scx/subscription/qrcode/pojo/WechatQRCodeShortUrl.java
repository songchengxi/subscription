package com.scx.subscription.qrcode.pojo;

import java.io.Serializable;

/**
 * 二维码短链接返回结果
 */
public class WechatQRCodeShortUrl implements Serializable {

    private static final long serialVersionUID = -835980382124181501L;

    private int errcode; // 状态

    private String errmsg; //信息

    private String short_url; //短链接

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    @Override
    public String toString() {
        return "WechatQRCodeShortUrl{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", short_url='" + short_url + '\'' +
                '}';
    }
}
