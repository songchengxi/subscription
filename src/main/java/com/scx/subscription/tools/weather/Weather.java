package com.scx.subscription.tools.weather;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scx
 * @date 2017/4/19
 * @desc 百度天气   总请求数: 0.5万次/天
 */
public class Weather {

    private static final String AK = "d3L2fPy0CRqmqimlwl2tc25qdcNOrYih";

    private static final Logger log = LoggerFactory.getLogger(Weather.class);

    public static String getWeather(String city) {

        String url = "http://api.map.baidu.com/telematics/v3/weather";
        Map<String, String> params = new HashMap<>();
        params.put("location", city);
        params.put("output", "json");
        params.put("ak", AK);
        String json = HttpRequest.net(url, params, "GET");
        JSONObject jsonObject = JSONObject.parseObject(json);
        String error = jsonObject.getString("error");
        StringBuilder result = new StringBuilder();
        if ("0".equals(error)) {
            String currentCity = jsonObject.getJSONArray("results").getJSONObject(0).getString("currentCity");
            String pm25 = jsonObject.getJSONArray("results").getJSONObject(0).getString("pm25");
            JSONObject todayWeather = jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("weather_data").getJSONObject(0);
            result.append("城市：").append(currentCity).append("\n");
            result.append("PM2.5：").append(pm25).append("\n");
            result.append("日期：").append(todayWeather.getString("date")).append("\n");
            result.append("温度：").append(todayWeather.getString("temperature")).append("\n");
            result.append("天气：").append(todayWeather.getString("weather")).append("\n");
            result.append("风力：").append(todayWeather.getString("wind")).append("\n\n");
            JSONArray index = jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("index");
            for (Object j : index) {
                String tipt = ((JSONObject) j).getString("tipt");
                String zs = ((JSONObject) j).getString("zs");
                String des = ((JSONObject) j).getString("des");
                result.append(tipt).append("：").append(zs).append("\n").append(des).append("\n");
            }
        } else {
            result.append("查询失败\n");
        }
        log.info(result.toString());
        return result.substring(0, result.lastIndexOf("\n"));
    }

    public static void main(String[] args) {
        String 汾阳 = getWeather("汾阳");
        System.out.println(汾阳);
    }
}
