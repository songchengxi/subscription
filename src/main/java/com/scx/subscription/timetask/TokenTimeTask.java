package com.scx.subscription.timetask;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.scx.subscription.model.AccessToken;
import com.scx.subscription.repository.AccessTokenRepository;
import com.scx.util.FormatDate;
import com.scx.util.HttpRequest;
import com.scx.util.SpringPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TokenTimeTask {

    private static final Logger log = LoggerFactory.getLogger(TokenTimeTask.class);

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    private static String ACCESS_TOKEN_URL;
    private static String APPID;
    private static String APPSECRET;

    static {
        try {
            ACCESS_TOKEN_URL = new SpringPropertiesUtil().getProperty("config", "ACCESS_TOKEN_URL");
            APPID = new SpringPropertiesUtil().getProperty("config", "APPID");
            APPSECRET = new SpringPropertiesUtil().getProperty("config", "APPSECRET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取accsee_token
     * 每隔7000秒执行一次
     */
    @Scheduled(fixedRate = 7000 * 1000)
    public AccessToken getToken() {
        AccessToken at = null;
        log.info("公众号 APPID:{}", APPID);
        log.info("公众号 APPSECRET:{}", APPSECRET);
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = HttpRequest.httpsRequest(requestUrl, "GET", null);
        // 请求成功
        if (null != jsonObject) {
            try {
                at = accessTokenRepository.findOne(APPID);
                at.setToken(jsonObject.getString("access_token"));
                at.setExpiresIn(jsonObject.getString("expires_in"));
                at.setTime(FormatDate.formatNowTime());
                accessTokenRepository.save(at);
                log.info("获取access_token成功，有效时长{}秒 token:{}", at.getExpiresIn(), at.getToken());
            } catch (JSONException e) {
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return at;
    }
}
