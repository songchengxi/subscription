package com.scx.subscription.tools.translate.entity;

import java.util.List;

/**
 * 调用百度api查询结果
 *
 * @author 宋程玺
 * @date 2015-08-11
 */
public class TranslateResult {
    //实际采用的源语言
    private String from;
    //实际采用的目标语言
    private String to;
    //结果体
    private List<ResultPair> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<ResultPair> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<ResultPair> trans_result) {
        this.trans_result = trans_result;
    }

}
