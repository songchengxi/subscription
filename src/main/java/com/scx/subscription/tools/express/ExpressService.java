package com.scx.subscription.tools.express;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 快递查询
 *
 * @author 宋程玺
 * @date 2015-08-17
 */
public class ExpressService {

    private final static Logger log = LoggerFactory.getLogger(ExpressService.class);

    private final static String KEY = "501075796ac0787f3198e2636da45ff6";

    /**
     * 封装获取的信息
     */
    private static String express(String text) {

        String url = "http://v.juhe.cn/exp/index";
        String[] textFormat = textFormat(text).split("-");
        Map params = new HashMap();
        params.put("key",KEY);
        params.put("com",textFormat[0]);
        params.put("no",textFormat[1]);
        String json = HttpRequest.net(url, params, "GET");

        int resultcode = JSONObject.parseObject(json).getIntValue("resultcode");
        StringBuffer buffer = new StringBuffer();
        // 查询成功
        if (resultcode == 200) {
            JSONArray list = JSONObject.parseObject(json).getJSONObject("result").getJSONArray("list");
            for (int i = 0; i < list.size(); i++) {
                JSONObject obj = (JSONObject) list.get(i);
                buffer.append(obj.get("datetime")).append(obj.get("remark")).append(obj.get("zone")).append("\n\n");
            }
            // 移除末尾空格
            buffer = new StringBuffer(buffer.substring(0, buffer.lastIndexOf("\n\n")));
            // 查询失败
        } else if (resultcode == 204) {
            int error_code = JSONObject.parseObject(json).getIntValue("error_code");
            if (error_code == 204304 || error_code == 204302) {
                buffer.append("请填写正确的运单号");
            } else if (error_code == 204301) {
                buffer.append("未被识别的快递公司！");
            } else {
                buffer.append("查询失败，请稍后再试！");
            }
        } else {
            buffer.append("查询失败");
        }
        log.info(buffer.toString());
        return buffer.toString();
    }

    /**
     * 对接收的文本进行格式转换
     */
    private static String textFormat(String text) {

        if (text.trim().startsWith("顺丰")) {
            text = text.replace("顺丰", "sf-");
        } else if (text.trim().startsWith("申通")) {
            text = text.replace("申通", "sto-");
        } else if (text.trim().startsWith("圆通")) {
            text = text.replace("圆通", "yt-");
        } else if (text.trim().startsWith("韵达")) {
            text = text.replace("韵达", "yd-");
        } else if (text.trim().startsWith("天天")) {
            text = text.replace("天天", "tt-");
        } else if (text.trim().startsWith("EMS")
                || text.trim().startsWith("ems")
                || text.trim().startsWith("邮政")) {
            text = text.replace("ems", "ems-").replace("EMS", "ems-")
                    .replace("邮政", "ems-");
        } else {
            text = "不支持此快递公司查询！";
        }
        log.info(text);
        return text;
    }

    /**
     * 提供外部调用的查询方法
     */
    public static String makeMessage(String text) {
        String result;
        String str;
        if ((str = express(text)) != "" || (str = express(text)) != null) {
            result = str;
        } else {
            result = "请按正确格式输入查询信息！";
        }
        return result;
    }

    public static void main(String[] args) {
        String s = makeMessage("顺丰70924954199997");
        System.out.println(s);
    }
}
