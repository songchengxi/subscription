package com.scx.subscription.tools.tuling.pojo;

import java.util.List;

/**
 * 新闻消息
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class NewsInfo extends BaseInfo {

    private List<News> list;

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

}
