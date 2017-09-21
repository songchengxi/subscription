package com.scx.subscription.model.dryclean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 32)
    private String id;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "buy_time")
    private Date buyTime;//购买时间

    @Column(name = "goods_id", length = 36)
    private String goodsId;

    @Column(name = "num", length = 5)
    private String num;//数量

    @Column(name = "price", length = 10)
    private String price;//价格

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", buyTime=" + buyTime +
                ", goodsId='" + goodsId + '\'' +
                ", num='" + num + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
