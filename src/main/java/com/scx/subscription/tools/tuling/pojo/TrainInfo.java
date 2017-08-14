package com.scx.subscription.tools.tuling.pojo;

import java.util.List;

/**
 * 列车信息
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class TrainInfo extends BaseInfo {

    private List<Train> list;

    public List<Train> getList() {
        return list;
    }

    public void setList(List<Train> list) {
        this.list = list;
    }

}
