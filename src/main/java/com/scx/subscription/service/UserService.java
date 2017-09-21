package com.scx.subscription.service;

import com.alibaba.fastjson.JSONObject;
import com.scx.subscription.model.AccessToken;
import com.scx.subscription.model.WXUser;
import com.scx.subscription.qrcode.QRCode;
import com.scx.subscription.repository.AccessTokenRepository;
import com.scx.subscription.repository.WXUserRepository;
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
    private WXUserRepository wxUserRepository;

    @Autowired
    private QRCode qrCode;

    private static String GET_USER_INFO;
    private static String APPID;

    static {
        try {
            GET_USER_INFO = new SpringPropertiesUtil().getProperty("config", "getUserInfo");
            APPID = new SpringPropertiesUtil().getProperty("config", "APPID");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WXUser saveUserInfo(String fromUserName, String eventKey) throws Exception {
        WXUser u = null;
        AccessToken ac = accessTokenRepository.findOne(APPID);
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", ac.getToken());
        params.put("openid", fromUserName);
        params.put("lang", "zh_CN");
        String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.GET, GET_USER_INFO, params, null);
        JSONObject j = JSONObject.parseObject(result);
        if (j != null) {
            u = wxUserRepository.findOne(fromUserName);
            if (u == null) {
                u = new WXUser();
                u.setOpenid(fromUserName);
                u.setRecommend(eventKey.split("_")[1]);
                int count = (int) wxUserRepository.count() + 1;
                u.setSceneId(String.valueOf(count));
                String ticket = qrCode.createForeverTicket(ac.getToken(), count);
                u.setTicket(ticket);
                qrCode.downQrcode(ticket, "/image/" + count);
            }
            u.setSubscribe("1");
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
            wxUserRepository.save(u);
        }
        return u;
    }

    public void updateUserInfo(String fromUserName) {
        WXUser one = wxUserRepository.findOne(fromUserName);
        one.setSubscribe("0");
        wxUserRepository.save(one);
    }
}