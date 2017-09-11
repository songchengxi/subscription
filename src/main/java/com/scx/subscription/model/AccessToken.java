package com.scx.subscription.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信通用接口凭证
 */
@Entity
@Table(name = "access_token")
public class AccessToken {

    @Id
    @Column(name = "app_id")
    private String appId;

    //获得到的凭证
    @Column(name = "token")
    private String token;

    //凭证有效时间，单位：秒
    @Column(name = "expires_in")
    private String expiresIn;

    @Column(name = "time")
    private String time;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "appId='" + appId + '\'' +
                ", token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                ", time='" + time + '\'' +
                '}';
    }
}
