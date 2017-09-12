package com.scx.subscription.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "wx_user")
public class User implements Serializable {

    @Id
    @Column(name = "openid")
    public String openid;//用户的标识，对当前公众号唯一

    @Column(name = "subscribe")
    public String subscribe;//值为0时，代表此用户没有关注该公众号

    @Column(name = "scene_id")
    public String sceneId;//二维码scene_id

    @Column(name = "ticket")
    public String ticket;//二维码的ticket

    @Column(name = "recommend")
    public String recommend;//推荐人

    @Column(name = "nickname")
    public String nickname;//昵称

    @Column(name = "sex")
    public String sex;//1：男性，2：女性，0：未知

    @Column(name = "language")
    public String language;//语言，简体中文为zh_CN

    @Column(name = "city")
    public String city;//城市

    @Column(name = "province")
    public String province;//省份

    @Column(name = "country")
    public String country;//国家

    @Column(name = "headimgurl")
    public String headimgurl;//头像，用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。

    @Column(name = "subscribe_time")
    public String subscribeTime;//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间

    @Column(name = "unionid")
    public String unionid;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。

    @Column(name = "remark")
    public String remark;//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注

    @Column(name = "groupid")
    public String groupid;//用户所在的分组ID（兼容旧的用户分组接口）

    @Column(name = "tagid_list")
    public String tagidList;//用户被打上的标签ID列表

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", subscribe='" + subscribe + '\'' +
                ", sceneId='" + sceneId + '\'' +
                ", ticket='" + ticket + '\'' +
                ", recommend='" + recommend + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                ", unionid='" + unionid + '\'' +
                ", remark='" + remark + '\'' +
                ", groupid='" + groupid + '\'' +
                ", tagidList='" + tagidList + '\'' +
                '}';
    }
}
