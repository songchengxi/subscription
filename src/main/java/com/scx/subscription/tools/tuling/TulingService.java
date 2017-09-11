package com.scx.subscription.tools.tuling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scx.subscription.tools.tuling.pojo.Flight;
import com.scx.subscription.tools.tuling.pojo.Menu;
import com.scx.subscription.tools.tuling.pojo.News;
import com.scx.subscription.tools.tuling.pojo.Train;
import com.scx.subscription.message.resp.entity.Article;

/**
 * 图灵机器人接口调用
 *
 * @author 宋程玺
 * @date 2015-08-13
 */
public class TulingService {

    private static Logger log = LoggerFactory.getLogger(TulingService.class);

    private static final String KEY = "edce687da1175bd110a447be44e15a3d";

    /**
     * 获得返回消息
     *
     * @param input 传入文字信息
     * @return
     */
    private static Object getInfo(String input) {
        // 存放处理好的数据
        StringBuffer info = new StringBuffer();

        String requestUrl = "http://www.tuling123.com/openapi/api";
        Map params = new HashMap();
        params.put("key", KEY);
        params.put("info", input);
        String json = HttpRequest.net(requestUrl, params, "GET");
        // 获得状态码
        int code = (Integer) JSONObject.parseObject(json).get("code");
        // 文本类数据
        if (code == 100000) {
            info.append(JSONObject.parseObject(json).get("text"));
            // 网址类数据
        } else if (code == 200000) {
            String text = (String) JSONObject.parseObject(json).get("text");
            String url = (String) JSONObject.parseObject(json).get("url");
            info.append("<a href=\"").append(url).append("\">").append(text).append("</a>");
            // 错误提示
        } else if (code == 40002) {
            info.append("请求内容为空！");
        } else if (code == 40004) {
            info.append("当天请求次数已用完！");
        } else if (code == 40005) {
            info.append("暂不支持该功能！");
        } else if (code == 40006) {
            info.append("服务器升级中！");
            // 其他类型消息（图文等...）
        } else {
            Object obj = getList(code, json);
            return obj;
        }

        log.info("tuling getInfo:{}", info.toString());
        return info.toString();
    }

    /**
     * 图文类消息
     *
     * @param code 状态码
     * @param json
     * @return
     */
    private static Object getList(int code, String json) {

        List ortherList = new ArrayList();
        JSONArray list = JSONObject.parseObject(json).getJSONArray("list");
        // 新闻
        if (code == 302000) {
            if (list.size() >= 10) {
                int size = 10;
                for (int i = 0; i < size; i++) {
                    News news = new News();
                    JSONObject listObject = (JSONObject) list.get(i);
                    news.setArticle(listObject.getString("article"));
                    news.setSource(listObject.getString("source"));
                    news.setDetailurl(listObject.getString("detailurl"));
                    news.setIcon(listObject.getString("icon"));
                    ortherList.add(news);
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    News news = new News();
                    JSONObject listObject = (JSONObject) list.get(i);
                    news.setArticle(listObject.getString("article"));
                    news.setSource(listObject.getString("source"));
                    news.setDetailurl(listObject.getString("detailurl"));
                    news.setIcon(listObject.getString("icon"));
                    ortherList.add(news);

                }
            }
            // 列车
        } else if (code == 305000) {
            if (list.size() >= 10) {
                int size = 10;
                for (int i = 0; i < size; i++) {
                    Train train = new Train();
                    JSONObject listObject = (JSONObject) list.get(i);
                    train.setTrainnum(listObject.getString("trainnum"));
                    train.setStart(listObject.getString("start"));
                    train.setTerminal(listObject.getString("terminal"));
                    train.setStarttime(listObject.getString("starttime"));
                    train.setEndtime(listObject.getString("endtime"));
                    train.setDetailurl(listObject.getString("detailurl"));
                    train.setIcon(listObject.getString("icon"));
                    ortherList.add(train);
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Train train = new Train();
                    JSONObject listObject = (JSONObject) list.get(i);
                    train.setTrainnum(listObject.getString("trainnum"));
                    train.setStart(listObject.getString("start"));
                    train.setTerminal(listObject.getString("terminal"));
                    train.setStarttime(listObject.getString("starttime"));
                    train.setEndtime(listObject.getString("endtime"));
                    train.setDetailurl(listObject.getString("detailurl"));
                    train.setIcon(listObject.getString("icon"));
                    ortherList.add(train);

                }
            }
            // 航班
        } else if (code == 306000) {
            if (list.size() >= 10) {
                int size = 10;
                for (int i = 0; i < size; i++) {
                    Flight flight = new Flight();
                    JSONObject listObject = (JSONObject) list.get(i);
                    flight.setFlight(listObject.getString("flight"));
//					flight.setRoute(listObject.getString("route"));
                    flight.setStarttime(listObject.getString("starttime"));
                    flight.setEndtime(listObject.getString("endtime"));
//					flight.setState(listObject.getString("state"));
//					flight.setDetailurl(listObject.getString("detailurl"));
                    flight.setIcon(listObject.getString("icon"));
                    ortherList.add(flight);
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Flight flight = new Flight();
                    JSONObject listObject = (JSONObject) list.get(i);
                    flight.setFlight(listObject.getString("flight"));
//					flight.setRoute(listObject.getString("route"));
                    flight.setStarttime(listObject.getString("starttime"));
                    flight.setEndtime(listObject.getString("endtime"));
//					flight.setState(listObject.getString("state"));
//					flight.setDetailurl(listObject.getString("detailurl"));
                    flight.setIcon(listObject.getString("icon"));
                    ortherList.add(flight);

                }
            }
            // 菜谱、视频、小说
        } else if (code == 308000) {
            if (list.size() >= 10) {
                int size = 10;
                for (int i = 0; i < size; i++) {
                    Menu menu = new Menu();
                    JSONObject listObject = (JSONObject) list.get(i);
                    menu.setName(listObject.getString("name"));
                    menu.setInfo(listObject.getString("info"));
                    menu.setDetailurl(listObject.getString("detailurl"));
                    menu.setIcon(listObject.getString("icon"));
                    ortherList.add(menu);
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Menu menu = new Menu();
                    JSONObject listObject = (JSONObject) list.get(i);
                    menu.setName(listObject.getString("name"));
                    menu.setInfo(listObject.getString("info"));
                    menu.setDetailurl(listObject.getString("detailurl"));
                    menu.setIcon(listObject.getString("icon"));
                    ortherList.add(menu);
                }
            }
        }
        log.info("tuling getList:{}", json);
        return ortherList;
    }

    /**
     * 组装图文类型消息
     */
    private static List<?> makeMessageList(List<?> list) {
        List messageList = new ArrayList();
        //新闻类型
        if (list.get(0) instanceof News) {
            for (int i = 0; i < list.size(); i++) {
                News news = (News) list.get(i);
                Article article = new Article();
                article.setTitle(news.getArticle());
                article.setDescription(news.getSource());
                article.setPicUrl(news.getIcon());
                article.setUrl(news.getDetailurl());
                messageList.add(article);
            }
            //列车类型
        } else if (list.get(0) instanceof Train) {
            for (int i = 0; i < list.size(); i++) {
                Train train = (Train) list.get(i);
                Article article = new Article();
                article.setTitle(train.getTrainnum());
                article.setDescription(train.getStart() + "--"
                        + train.getTerminal() + "\n" + train.getStarttime()
                        + "--" + train.getEndtime());
                article.setPicUrl(train.getIcon());
                article.setUrl(train.getDetailurl());
                messageList.add(article);
            }
            //航班类型
        } else if (list.get(0) instanceof Flight) {
            for (int i = 0; i < list.size(); i++) {
                Flight flight = (Flight) list.get(i);
                Article article = new Article();
                article.setTitle(flight.getFlight());
                article.setDescription(flight.getStarttime() + "--"
                        + flight.getEndtime());
                article.setPicUrl(flight.getIcon());
                article.setUrl("http://touch.qunar.com/h5/flight/");
                messageList.add(article);
            }
            //菜谱，视频，小说
        } else if (list.get(0) instanceof Menu) {
            for (int i = 0; i < list.size(); i++) {
                Menu menu = (Menu) list.get(i);
                Article article = new Article();
                article.setTitle(menu.getName());
                article.setDescription(menu.getInfo());
                article.setPicUrl(menu.getIcon());
                article.setUrl(menu.getDetailurl());
                messageList.add(article);
            }
        }
        return messageList;
    }

    /**
     * 组装消息
     */
    public static Object makeMessage(String text) {
        String result;
        Object obj = getInfo(text);
        // 文本类型消息
        if (obj instanceof String) {
            result = obj.toString();
            return result;
            // 图文类型消息
        } else {
            List<?> list = (List<?>) obj;
            List<?> listMessage = makeMessageList(list);
            return listMessage;
        }
    }

    public static void main(String[] args) {
        System.out.println(makeMessage("明天北京飞太原的飞机"));
    }
}
