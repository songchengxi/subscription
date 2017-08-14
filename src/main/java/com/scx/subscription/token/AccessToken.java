package com.scx.subscription.token;

/**
 * 微信通用接口凭证
 *
 * @author 宋程玺
 * @date 2015-08-10
 */
public class AccessToken {
    //获得到的凭证
    private String token;
    //凭证有效时间，单位：秒
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }


}
