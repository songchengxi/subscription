package com.scx.subscription.tools.tuling.pojo;

/**
 * 新闻
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class News {
    //标题
    private String article;
    //来源
    private String source;
    //详情地址
    private String detailurl;
    //图标地址
    private String icon;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
