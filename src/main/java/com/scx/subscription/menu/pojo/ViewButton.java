package com.scx.subscription.menu.pojo;

/**
 * View类型的菜单
 *
 * @author 宋程玺
 * @date 2015-08-11
 */
public class ViewButton extends Button {

    private String type;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
