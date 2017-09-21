package com.scx.subscription.model.dryclean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 32)
    private String id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "price", length = 10)
    private String price;//价格

    @Column(name = "stock", length = 10)
    private String stock;//库存

    @Column(name = "describe")
    private String describe;//描述

    @Column(name = "is_valid", length = 1)
    private String isValid;//是否有效   0：无效；1：有效

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", isValid='" + isValid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
