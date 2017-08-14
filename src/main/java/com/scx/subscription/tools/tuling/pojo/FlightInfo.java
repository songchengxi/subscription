package com.scx.subscription.tools.tuling.pojo;

import java.util.List;

/**
 * 航班信息
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class FlightInfo extends BaseInfo {

    private List<Flight> list;

    public List<Flight> getList() {
        return list;
    }

    public void setList(List<Flight> list) {
        this.list = list;
    }

}
