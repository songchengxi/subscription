package com.scx.subscription.tools.tuling.pojo;

/**
 * 航班
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class Flight {
    //航班
    private String flight;
    //航班路线
    private String route;
    //起飞时间
    private String starttime;
    //到达时间
    private String endtime;
    //航班状态
    private String state;
    //详情
    private String detailurl;
    //图标地址
    private String icon;

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
