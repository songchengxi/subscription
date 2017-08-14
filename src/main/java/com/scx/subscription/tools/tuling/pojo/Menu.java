package com.scx.subscription.tools.tuling.pojo;

/**
 * 菜谱
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class Menu {
    //名称
    private String name;
    //详情
    private String info;
    //详情链接
    private String detailurl;
    //图标地址
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
