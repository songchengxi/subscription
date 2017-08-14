package com.scx.subscription.core.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scx.subscription.token.TokenThread;
import com.scx.subscription.token.WechatUtil;

/**
 * 初始化servlet
 *
 * @author 宋程玺
 * @date 2015-08-12
 */
public class InitServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(WechatUtil.class);

    public void init() throws ServletException {

        // 获取web.xml中配置的参数
        TokenThread.appid = getInitParameter("appid");
        TokenThread.appsecret = getInitParameter("appsecret");

        log.info("公众号 appid:{}", TokenThread.appid);
        log.info("公众号 appsecret:{}", TokenThread.appsecret);
        // 未配置appid，appsecret时给出提示
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
            log.error("appid and appsecret configuration error,please check carefully.");
        } else {
            // 启动定时器获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}
