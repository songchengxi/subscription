package com.scx.subscription.service;

import com.alibaba.fastjson.JSONObject;
import com.scx.subscription.model.AccessToken;
import com.scx.subscription.model.User;
import com.scx.subscription.repository.AccessTokenRepository;
import com.scx.subscription.repository.UserRepository;
import com.scx.util.HttpReqUtil;
import com.scx.util.SpringPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private UserRepository userRepository;

    private static String GET_USER_INFO;
    private static String APPID;
    private static String APPSECRET;

    static {
        try {
            GET_USER_INFO = new SpringPropertiesUtil().getProperty("config", "getUserInfo");
            APPID = new SpringPropertiesUtil().getProperty("config", "APPID");
            APPSECRET = new SpringPropertiesUtil().getProperty("config", "APPSECRET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUserInfo(String fromUserName) {
        AccessToken ac = accessTokenRepository.findOne(APPID);
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", ac.getToken());
        params.put("openid", fromUserName);
        params.put("lang", "zh_CN");
        String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.GET, GET_USER_INFO, params, null);
        JSONObject j = JSONObject.parseObject(result);
        if (j != null) {
            User u = new User();
            u.setSubscribe(j.getString("subscribe"));
            u.setOpenid(j.getString("openid"));
            u.setNickname(j.getString("nickname"));
            u.setSex(j.getString("sex"));
            u.setLanguage(j.getString("language"));
            u.setCity(j.getString("city"));
            u.setProvince(j.getString("province"));
            u.setCountry(j.getString("country"));
            u.setHeadimgurl(j.getString("headimgurl"));
            u.setSubscribeTime(j.getString("subscribe_time"));
            u.setUnionid(j.getString("unionid"));
            u.setRemark(j.getString("remark"));
            u.setGroupid(j.getString("groupid"));
            u.setTagidList(j.getString("tagid_list"));
            userRepository.save(u);
        }
    }

    public void updateUserInfo(String fromUserName) {
        User one = userRepository.findOne(fromUserName);
        one.setSubscribe("0");
        userRepository.save(one);
    }
}