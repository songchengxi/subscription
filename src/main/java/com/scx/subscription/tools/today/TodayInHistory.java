package com.scx.subscription.tools.today;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 历史上的今天查询服务
 *
 * @author 宋程玺
 * @date 2015-08-11
 */
public class TodayInHistory {

    private static Logger log = LoggerFactory.getLogger(TodayInHistory.class);

    /**
     * 从html中抽取出历史上的今天信息
     */
    private static String extract() {
        StringBuilder buffer = new StringBuilder();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.lssdjt.com/").get();
        } catch (IOException e) {
            log.error("TodayHistory ERROR :", e.getMessage());
            e.printStackTrace();
        }
        Element hd = doc.getElementsByClass("hd").first();
        String text1 = hd.text();
        buffer.append(text1).append("\n\n");
        Elements main = doc.getElementsByClass("main").first().child(0).getElementsByTag("li");
        for (Element e : main) {
            buffer.append(e.text()).append("\n");
        }

        //将buffer最后换行符移除并返回
        return buffer.substring(0, buffer.lastIndexOf("\n"));
    }

    /**
     * 获取前/后n天日期（M月d日）
     */
    private static String getMonthDay(int diff) {

        DateFormat df = new SimpleDateFormat("M月d日");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, diff);
        return df.format(c.getTime());
    }

    /**
     * 封装历史上的今天查询方法，供外部使用
     */
    public static String getTodayInHistoryInfo() {
        String extract = extract();
        log.info("TodayHistory:{}", extract);
        return extract;
    }

    public static void main(String[] args) {
        String info = getTodayInHistoryInfo();
        System.out.println(info);
    }
}
