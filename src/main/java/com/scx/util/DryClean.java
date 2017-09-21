package com.scx.util;

import com.scx.subscription.model.WXUser;

public class DryClean {

    /**
     * 主菜单
     */
    public static String getMainMenu(WXUser u) {
        StringBuilder sb = new StringBuilder();
        sb.append(u.getNickname()).append("，您好，欢迎关注泰洁干洗/鼓掌[呲牙]/::B，请回复数字选择服务：").append("\n\n");
        sb.append("1 <a href=\"http://www.baidu.com\">图文消息</a>").append("\n");
        sb.append("2 多图文消息\ue159").append("\n");
        sb.append("3 实时天气\ue13d").append("\n");
        sb.append("4 快递查询\ue03c").append("\n");
        sb.append("5 历史上的今天").append("\n");
        sb.append("6 翻译").append("\n");
        sb.append("7 人脸识别").append("\n");
        sb.append("回复\"?\"显示此帮助菜单").append("\n");
        sb.append("<a href=\"http://songcx.duapp.com/subscription/page/dry-clean/index.html?openId=").append(u.getOpenid()).append("\">积分兑换商城</a>");
        return sb.toString();
    }
}
