package com.scx.subscription.menu;

import com.alibaba.fastjson.JSONObject;
import com.scx.subscription.model.AccessToken;
import com.scx.subscription.timetask.TokenTimeTask;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scx.subscription.menu.pojo.Button;
import com.scx.subscription.menu.pojo.CommonButton;
import com.scx.subscription.menu.pojo.ComplexButton;
import com.scx.subscription.menu.pojo.Menu;
import com.scx.subscription.menu.pojo.ViewButton;

/**
 * 菜单管理器类
 *
 * @author 宋程玺
 * @date 2015-08-10
 */
public class MenuManager {

    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static void main(String[] args) {
        // 测试号
        String appId = "wxb3248a3e1c165a02";
        String appSecret = "51b599fafd1e0a381b3b2ae2c9339660";

        // 蛋鲜生
//         String appId="wx409ab16ce4d77d9e";
//         String appSecret="eacc29a885a5f3eee09108eb88b66dc2";

        // 调用接口获取access_token
        AccessToken at = new TokenTimeTask().getToken();

        if (null != at) {
            // 调用接口创建菜单
            int result = createMenu(getMenu(), at.getToken());
            // 判断菜单创建结果
            if (0 == result) {
                log.info("菜单创建成功！");
            } else {
                log.info("菜单创建失败，错误码：" + result);
            }
        }
    }

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        //拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        //将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString(menu);
        //调用接口创建菜单
        JSONObject jsonObject = HttpRequest.httpsRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 组转菜单数据
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("扫码带提示");
        btn11.setType("scancode_waitmsg");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("扫码推事件");
        btn12.setType("scancode_push");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("系统拍照发图");
        btn13.setType("pic_sysphoto");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("拍照或者相册发图");
        btn14.setType("pic_photo_or_album");
        btn14.setKey("14");

        CommonButton btn15 = new CommonButton();
        btn15.setName("微信相册发图");
        btn15.setType("pic_weixin");
        btn15.setKey("15");

        CommonButton btn21 = new CommonButton();
        btn21.setName("发送位置");
        btn21.setType("location_select");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("历史上的今天");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("翻译");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("人脸识别");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("快递查询");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q友圈");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("电影排行榜");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("幽默笑话");
        btn33.setType("click");
        btn33.setKey("33");

        ViewButton btn34 = new ViewButton();
        btn34.setName("VIEW");
        btn34.setType("view");
        btn34.setUrl("http://chengxi.duapp.com/wechat/index.html");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("扫码拍照");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("生活助手");
        mainBtn2.setSub_button(new Button[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33, btn34});

		/*
         * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
