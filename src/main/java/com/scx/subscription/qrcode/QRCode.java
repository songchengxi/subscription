package com.scx.subscription.qrcode;

import com.alibaba.fastjson.JSONObject;
import com.scx.subscription.qrcode.pojo.WechatQRCode;
import com.scx.subscription.qrcode.pojo.WechatQRCodeShortUrl;
import com.scx.subscription.qrcode.pojo.WechatResult;
import com.scx.util.HttpReqUtil;
import com.scx.util.SpringPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class QRCode {

    private static final Logger log = LoggerFactory.getLogger(QRCode.class);

    private static String CREATE_TICKET_PATH;
    private static String SHOW_QRCODE_PATH;
    private static String SHORT_URL;
    private static String APPID;
    private static String APPSECRET;

    static {
        try {
            CREATE_TICKET_PATH = new SpringPropertiesUtil().getProperty("config", "CREATE_TICKET_PATH");
            SHOW_QRCODE_PATH = new SpringPropertiesUtil().getProperty("config", "SHOW_QRCODE_PATH");
            SHORT_URL = new SpringPropertiesUtil().getProperty("config", "SHORT_URL");
            APPID = new SpringPropertiesUtil().getProperty("config", "APPID");
            APPSECRET = new SpringPropertiesUtil().getProperty("config", "APPSECRET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        QRCode qrCode = new QRCode();

        //测试
        String APPID = "wxb3248a3e1c165a02";
        String APPSECRET = "51b599fafd1e0a381b3b2ae2c9339660";
        // 调用接口获取access_token
//        AccessToken at = WechatUtil.getAccessToken(APPID, APPSECRET);
        String token = "zuxDjube2wtoyXa0zaJwgBui3lxNEzsaDFGaSitDApI9NihGnjnKxYc6OsF-9iFNVSGOWargs2VGlll7h3pQZnQlSUaF8xGFKuxdV5VCK50REOhAEAHUE";
//        String tempTicket = qrCode.createTempTicket(at.getToken(), "10800", 101);//临时数字{"ticket":"gQH18DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAybE45bW82V1RkUjQxTHJEUE5wMVoAAgSrvbNZAwQwKgAA","expire_seconds":10800,"url":"http:\/\/weixin.qq.com\/q\/02lN9mo6WTdR41LrDPNp1Z"}
//        String tempTicket = qrCode.createTempStrTicket(at.getToken(), "10800", "sssssssss");//临时字符串{"ticket":"gQFN8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyY05HTm9RV1RkUjQxemtvUU5wY1IAAgSk7rNZAwQwKgAA","expire_seconds":10800,"url":"http:\/\/weixin.qq.com\/q\/02cNGNoQWTdR41zkoQNpcR"}
//        String aaa = qrCode.showQrcode("gQH18DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAybE45bW82V1RkUjQxTHJEUE5wMVoAAgSrvbNZAwQwKgAA");
//        WechatResult ppp = qrCode.showQrcode("gQFN8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyY05HTm9RV1RkUjQxemtvUU5wY1IAAgSk7rNZAwQwKgAA", "d:\\111.jpg");
//        System.out.println(ppp);
//        String foreverTicket = qrCode.createForeverTicket(at.getToken(), 11111);//永久数字{"ticket":"gQEB8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyeWxUN296V1RkUjQxMDAwME0wM1oAAgRC8bNZAwQAAAAA","url":"http:\/\/weixin.qq.com\/q\/02ylT7ozWTdR410000M03Z"}
//        System.out.println(foreverTicket);
//        WechatResult forever = qrCode.showQrcode("gQEB8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyeWxUN296V1RkUjQxMDAwME0wM1oAAgRC8bNZAwQAAAAA", "d:\\forever.jpg");
//        System.out.println(forever);
//        String foreverStrTicket = qrCode.createForeverStrTicket(at.getToken(), "123");//永久字符串{"ticket":"gQEn8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQnRLZW9HV1RkUjQxMDAwME0wNzQAAgR197NZAwQAAAAA","url":"http:\/\/weixin.qq.com\/q\/02BtKeoGWTdR410000M074"}
        String sort = qrCode.shortQRCodeurl(token, "gQEn8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQnRLZW9HV1RkUjQxMDAwME0wNzQAAgR197NZAwQAAAAA");
        System.out.println(sort);

    }

    /**
     * 创建临时二维码(数字)
     *
     * @param sceneId       场景Id
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    public String createTempTicket(String accessToken, String expireSeconds, int sceneId) {
        WechatQRCode wechatQRCode = null;
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("access_token", accessToken);
        String reqStr = "{\"expire_seconds\": " + expireSeconds + ", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + sceneId + "}}}";
        String data = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST, CREATE_TICKET_PATH, params, reqStr);
        try {
            wechatQRCode = JSONObject.parseObject(data, WechatQRCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 创建临时二维码(字符串)
     *
     * @param sceneStr      场景str
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    public String createTempStrTicket(String accessToken, String expireSeconds, String sceneStr) {
        WechatQRCode wechatQRCode = null;
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("access_token", accessToken);

        String reqStr = "{\"expire_seconds\": " + expireSeconds + ", \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": " + sceneStr + "}}}";
        String data = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST, CREATE_TICKET_PATH, params, reqStr);
        try {
            wechatQRCode = JSONObject.parseObject(data, WechatQRCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 创建永久二维码(数字)
     *
     * @param sceneId 场景Id
     */
    public String createForeverTicket(String accessToken, int sceneId) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("access_token", accessToken);

        String reqStr = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + sceneId + "}}}";
        String data = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST, CREATE_TICKET_PATH, params, reqStr);
        WechatQRCode wechatQRCode = null;
        try {
            wechatQRCode = JSONObject.parseObject(data, WechatQRCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 创建永久二维码(字符串)
     *
     * @param sceneStr 场景str
     */
    public String createForeverStrTicket(String accessToken, String sceneStr) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("access_token", accessToken);

        String reqStr = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": " + sceneStr + "}}}";
        String data = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST, CREATE_TICKET_PATH, params, reqStr);
        WechatQRCode wechatQRCode = null;
        try {
            wechatQRCode = JSONObject.parseObject(data, WechatQRCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 长链接转短链接
     */
    public String shortQRCodeurl(String accessToken, String longUrl) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("access_token", accessToken);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("action", "long2short");
        paramsMap.put("long_url", longUrl);
        String data = JSONObject.toJSONString(paramsMap);
        String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST, SHORT_URL, params, data);
        WechatQRCodeShortUrl wechatQRCodeShortUrl = JSONObject.parseObject(result, WechatQRCodeShortUrl.class);
        return wechatQRCodeShortUrl.getShort_url();
    }

    /**
     * 获取二维码ticket后，通过ticket换取二维码图片展示
     */
    public String showQrcode(String ticket) throws Exception {
        return HttpReqUtil.setParams(params(ticket), SHOW_QRCODE_PATH, null);
    }

    /**
     * 下载二维码
     *
     * @param ticket
     * @param savePath 保存的路径,例如 F:\\phil\phil.jpg
     * @return Result.success = true 表示下载图片下载成功
     */
    public WechatResult showQrcode(String ticket, String savePath) throws Exception {
        return HttpReqUtil.downMeaterMethod(params(ticket), HttpReqUtil.GET, SHOW_QRCODE_PATH, savePath);
    }

    private TreeMap<String, String> params(String ticket) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("ticket", HttpReqUtil.urlEncode(ticket, "utf-8"));
        return params;
    }
}
