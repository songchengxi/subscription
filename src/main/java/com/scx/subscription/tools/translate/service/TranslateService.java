package com.scx.subscription.tools.translate.service;

import com.alibaba.fastjson.JSONObject;
import com.scx.subscription.tools.translate.entity.TranslateResult;
import com.scx.util.HttpRequest;
import com.scx.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * 翻译接口调用
 *
 * @author 宋程玺
 * @date 2015-08-11
 */
public class TranslateService {

    private static Logger log = LoggerFactory.getLogger(TranslateService.class);

    private static final String APP_ID = "20170408000044371";
    //密钥
    private static final String SECURITY_KEY = "okX6El9nlp097l87XK8b";

    /**
     * 百度翻译 （中->英 英->中 日->中）
     *
     * @param source
     * @return
     */
    public static String baiduTranslate(String source) {
        String dst;

        // 查询地址
        String requestUrl = "http://api.fanyi.baidu.com/api/trans/vip/translate";

        Map<String,String> params = new HashMap<>();
        params.put("appid", APP_ID);
        params.put("q", source);
        params.put("from", "auto");
        params.put("to", "auto");
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = APP_ID + source + salt + SECURITY_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        // 查询并获取返回结果
        String json = HttpRequest.net(requestUrl, params, "GET");
        // 将json转换成TranslateResult对象
        JSONObject object = JSONObject.parseObject(json);
        TranslateResult translateResult = JSONObject.toJavaObject(object, TranslateResult.class);
        // 取出translateResult中的译文
        dst = translateResult.getTrans_result().get(0).getDst();

        if (dst == null) {
            dst = "翻译系统异常，请稍后尝试！";
        }
        return dst;
    }

    /**
     * 有道翻译
     */
    public static String youdaoTranslate(String text) {
        String result = null;
        String url = "http://fanyi.youdao.com/openapi.do";//请求接口地址
        Map<String, String> params = new HashMap<>();//请求参数
        params.put("key", "269158696");//您申请的KEY
        params.put("keyfrom", "476214747");
        params.put("type", "data");
        params.put("doctype", "json");//返回结果的数据格式，xml或json或jsonp
        params.put("version", "1.1");
        params.put("q", text);//要翻译的内容

        try {
            result = HttpRequest.net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getIntValue("errorCode") == 0) {
                result = object.getString("translation");
            } else {
                log.warn("有道翻译，errorCode：",object.getString("errorCode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(youdaoTranslate("Hello world!"));
        System.out.println(youdaoTranslate("你好世界！"));
//        System.out.println(baiduTranslate("Hello world!"));
//        System.out.println(baiduTranslate("你好世界！"));
    }
}
