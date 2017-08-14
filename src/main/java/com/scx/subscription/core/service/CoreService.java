package com.scx.subscription.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scx.subscription.tools.face.FaceService;
import com.scx.subscription.tools.express.ExpressService;
import com.scx.subscription.tools.today.TodayInHistory;
import com.scx.subscription.tools.translate.service.TranslateService;
import com.scx.subscription.tools.tuling.TulingService;
import com.scx.subscription.tools.weather.Weather;
import com.scx.util.DanXianSheng;
import com.scx.util.MessageUtil;
import com.scx.subscription.message.resp.NewsMessage;
import com.scx.subscription.message.resp.TextMessage;
import com.scx.subscription.message.resp.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 核心服务类
 *
 * @author 宋程玺
 * @date 2015-08-09
 */
public class CoreService {

    private static Logger logger = LoggerFactory.getLogger(CoreService.class);

    /**
     * 处理微信发来都请求
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;

        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍后尝试！";
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方账号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            logger.info("fromUserName:" + fromUserName);
            // 公众账号
            String toUserName = requestMap.get("ToUserName");
            logger.info("toUserName:" + toUserName);
            // 消息创建时间 （整型）
            String createTime = requestMap.get("CreateTime");
            logger.info(DanXianSheng.formatTime(createTime));
            // 文本消息内容
            String content = requestMap.get("Content");
            logger.info("content:" + content);
            // 消息类型
            String msgType = requestMap.get("MsgType");
            logger.info("msgType:" + msgType);

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime() / 1000);
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            // textMessage.setFuncFlag(0);
            // 回复图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setFromUserName(toUserName);
            newsMessage.setToUserName(fromUserName);
            newsMessage.setCreateTime(new Date().getTime() / 1000);
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

            List<Article> articleList = new ArrayList<Article>();

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 回复？返回帮助菜单
                if (content.equals("?") || content.equals("？")) {
                    respContent = DanXianSheng.getMainMenu();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    // 回复图文消息（单图文消息）
                } else if ("1".equals(content)) {

                    Article article = new Article();
                    article.setTitle("欢迎关注蛋鲜生！");
                    article.setDescription("测试图文消息描述\n\ue159\ue03c");
                    article.setPicUrl("http://chengxi.duapp.com/wechat/image/image.jpg");
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5NTMzMTYxNA==&mid=200101937&idx=1&sn=717dc5bb91105ee7499d2adfb6459b46&scene=18#rd");

                    articleList.add(article);
                    newsMessage.setArticles(articleList);
                    newsMessage.setArticleCount(articleList.size());
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    // 回复图文消息（多图文消息）
                } else if ("2".equals(content)) {

                    Article article = new Article();
                    article.setTitle("欢迎关注蛋鲜生！");
                    article.setDescription("测试图文消息描述\n\ue159\ue03c");
                    article.setPicUrl("http://chengxi.duapp.com/wechat/image/image.jpg");
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5NTMzMTYxNA==&mid=200101937&idx=1&sn=717dc5bb91105ee7499d2adfb6459b46&scene=18#rd");

                    Article article2 = new Article();
                    article2.setTitle("欢迎关注蛋鲜生[呲牙]！");
                    article2.setDescription("测试图文消息描述\n[呲牙]/鼓掌");
                    article2.setPicUrl("http://chengxi.duapp.com/wechat/image/image.jpg");
                    article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5NTMzMTYxNA==&mid=200101937&idx=1&sn=717dc5bb91105ee7499d2adfb6459b46&scene=18#rd");

                    articleList.add(article);
                    articleList.add(article2);
                    newsMessage.setArticles(articleList);
                    newsMessage.setArticleCount(articleList.size());
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    //实时天气使用指南
                } else if ("3".equals(content)) {
                    respContent = DanXianSheng.getWeatherUsage();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    //快递查询使用指南
                } else if ("4".equals(content)) {
                    respContent = DanXianSheng.getKuaiDiUsage();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    //历史上的今天
                } else if ("5".equals(content)) {
                    respContent = TodayInHistory.getTodayInHistoryInfo();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    //翻译使用指南
                } else if ("6".equals(content)) {
                    respContent = DanXianSheng.getTranslateUsage();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    //人脸识别使用指南
                } else if ("7".equals(content)) {
                    respContent = DanXianSheng.getFaceUsage();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    //实时天气查询
                } else if (content.trim().startsWith("天气")) {
                    String keyWord = content.trim().replaceAll("^天气", "");
                    if ("".equals(keyWord)) {
                        respContent = DanXianSheng.getWeatherUsage();
                    } else {
                        respContent = Weather.getWeather(keyWord);
                    }
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    // 回复QQ表情返回QQ表情
                } else if (DanXianSheng.isQqFace(content)) {
                    respContent = "您发送的表情是：" + content;
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    // 调用翻译功能
                } else if (content.trim().startsWith("翻译")) {
                    String keyWord = content.trim().replaceAll("^翻译", "");
                    if ("".equals(keyWord)) {
                        respContent = DanXianSheng.getTranslateUsage();
                    } else {
                        respContent = TranslateService.youdaoTranslate(keyWord);
                    }
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    // 快递信息查询
                } else if (content.trim().startsWith("快递")) {
                    String keyWord = content.trim().replaceAll("^快递", "");
                    if (keyWord.equals("")) {
                        respContent = DanXianSheng.getKuaiDiUsage();
                    } else {
                        respContent = ExpressService.makeMessage(keyWord);
                    }
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    // 调用图灵接口
                } else {
                    // 获得回复内容
                    Object obj = TulingService.makeMessage(content);
                    // 文本类型回复
                    if (obj instanceof String) {
                        respContent = obj.toString();
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                        // 图文类型回复
                    } else {
                        List<Article> list = (List<Article>) obj;
                        newsMessage.setArticles(list);
                        newsMessage.setArticleCount(list.size());
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    }
                }
                // 图片消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                // 取得图片地址
                String picUrl = requestMap.get("PicUrl");
                // 人脸检测
                respContent = FaceService.detect(picUrl);
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                // 视频消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                // 音频消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                // 链接消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                // 地理位置消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                // 小视频消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                // 事件推送
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                logger.info("eventType:{}", eventType);
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = DanXianSheng.getMainMenu();
                    // 取消订阅（用户取消订阅收不到公众号发送的消息，不需要回复）
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

                    // 自定义菜单点击事件
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    logger.info("eventKey:{}", eventKey);
                    //历史上的今天
                    if (eventKey.equals("22")) {
                        respContent = TodayInHistory.getTodayInHistoryInfo();
                        // 调用翻译功能
                    } else if (eventKey.equals("23")) {
                        respContent = DanXianSheng.getTranslateUsage();
                    } else if (eventKey.equals("24")) {
                        respContent = DanXianSheng.getFaceUsage();
                    } else if (eventKey.equals("25")) {
                        respContent = DanXianSheng.getKuaiDiUsage();
                    } else if (eventKey.equals("31")) {
                        respContent = "Q友圈菜单项被点击！";
                    } else if (eventKey.equals("32")) {
                        respContent = "电影排行榜菜单项被点击！";
                    } else if (eventKey.equals("33")) {
                        respContent = "幽默笑话菜单项被点击！";
                    }
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    String Latitude = requestMap.get("Latitude");// 地理位置纬度
                    String Longitude = requestMap.get("Longitude");// 地理位置经度
                    String Precision = requestMap.get("Precision");// 地理位置精度
                    respContent = "地理位置纬度：" + Latitude + "地理位置经度：" + Longitude
                            + "地理位置精度：" + Precision;
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            e.printStackTrace();
        }
        return respMessage;
    }
}
