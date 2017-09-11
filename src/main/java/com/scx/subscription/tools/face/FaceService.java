package com.scx.subscription.tools.face;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 人脸检测服务
 *
 * @author 宋程玺
 * @date 2015-08-12
 */
public class FaceService {

    private static final Logger log = LoggerFactory.getLogger(FaceService.class);

    private static final String KEY = "ead7271327b9c73b6ad760e14c62c0fd";
    private static final String SECRET = "OFoPNXg7rcvhUWNS0aBc-QAhivoQ5-X5";

    /**
     * 调用Face++ API实现人脸检测
     *
     * @param picUrl 待检测图片的访问地址
     * @return List<Face> 人脸列表
     */
    private static List<Face> faceDetect(String picUrl) {
        List<Face> faceList = new ArrayList<Face>();

        // 拼接face++人脸检测的请求地址
        String queryUrl = "http://apicn.faceplusplus.com/v2/detection/detect";
        // 对URL进行编码
        Map params = new HashMap();
        params.put("api_key",KEY);
        params.put("api_secret",SECRET);
        params.put("url",picUrl);
        // 调用人脸检测接口
        String json = HttpRequest.net(queryUrl, params, "GET");
        // 解析返回json中的Face列表
        JSONArray jsonArray = JSONObject.parseObject(json).getJSONArray("face");
        // 遍历检测到的人脸
        for (int i = 0; i < jsonArray.size(); i++) {
            // Face
            JSONObject faceObject = (JSONObject) jsonArray.get(i);
            // attribute
            JSONObject attrObject = faceObject.getJSONObject("attribute");
            // position
            JSONObject posObject = faceObject.getJSONObject("position");

            Face face = new Face();
            face.setFaceId(faceObject.getString("face_id"));
            face.setAgeValue(attrObject.getJSONObject("age").getIntValue("value"));
            face.setAgeRange(attrObject.getJSONObject("age").getIntValue("range"));
            face.setGenderValue(genderConvert(attrObject.getJSONObject("gender").getString("value")));
            face.setGenderConfidence(attrObject.getJSONObject("gender").getDouble("confidence"));
            face.setRaceValue(raceConvert(attrObject.getJSONObject("race").getString("value")));
            face.setRaceConfidence(attrObject.getJSONObject("race").getDouble("confidence"));
            face.setSmilingValue(attrObject.getJSONObject("smiling").getDouble("value"));
            face.setCenterX(posObject.getJSONObject("center").getDouble("x"));
            face.setCenterY(posObject.getJSONObject("center").getDouble("y"));

            faceList.add(face);
        }
        // 使用Collections.sort()方法排序的前提是集合中的Face对象实现了Comparable接口
        // 将检测出的Face按从左至右的顺序排序
        Collections.sort(faceList);

        return faceList;
    }

    /**
     * 性别转换 （英文－>中文）
     *
     * @param gender
     * @return
     */
    private static String genderConvert(String gender) {
        String result = "男性";
        if ("Male".equals(gender)) {
            result = "男性";
        } else if ("Female".equals(gender)) {
            result = "女性";
        }
        return result;
    }

    /**
     * 人种转换（英文－>中文）
     *
     * @param race
     * @return
     */
    private static String raceConvert(String race) {
        String result = "黄色";
        if ("Asian".equals(race)) {
            result = "黄色";
        } else if ("White".equals(race)) {
            result = "白色";
        } else if ("Black".equals(race)) {
            result = "黑色";
        }
        return result;
    }

    /**
     * 根据人脸识别结果组装消息
     *
     * @param faceList 人脸列表
     * @return
     */
    private static String makeMessage(List<Face> faceList) {
        StringBuffer buffer = new StringBuffer();
        // 检测到一张脸
        if (faceList.size() == 1) {
            buffer.append("共检测到").append(faceList.size()).append("张人脸")
                    .append("\n");
            for (Face face : faceList) {
                buffer.append(face.getRaceValue()).append("人种");
                buffer.append(face.getGenderValue()).append(",");
                buffer.append(face.getAgeValue()).append("岁左右").append("\n");
            }
            // 检测到2-10张脸
        } else if (faceList.size() > 1 && faceList.size() <= 10) {
            buffer.append("共检测到").append(faceList.size())
                    .append("张人脸,按脸部中心位置从左至右依次为:").append("\n");
            for (Face face : faceList) {
                buffer.append(face.getRaceValue()).append("人种");
                buffer.append(face.getGenderValue()).append(",");
                buffer.append(face.getAgeValue()).append("岁左右").append("\n");
            }
            // 检测到10张脸以上
        } else if (faceList.size() > 10) {
            buffer.append("共检测到").append(faceList.size()).append("张人脸")
                    .append("\n");
            // 统计各人种、性别的人数
            int asiaMale = 0;
            int asiaFemale = 0;
            int whiteMale = 0;
            int whiteFemale = 0;
            int blackMale = 0;
            int blackFemale = 0;
            for (Face face : faceList) {
                if ("黄色".equals(face.getRaceValue()))
                    if ("男性".equals(face.getGenderValue()))
                        asiaMale++;
                    else
                        asiaFemale++;
                else if ("白色".equals(face.getRaceValue()))
                    if ("男性".equals(face.getGenderValue()))
                        whiteMale++;
                    else
                        whiteFemale++;
                else if ("黑色".equals(face.getRaceValue()))
                    if ("男性".equals(face.getGenderValue()))
                        blackMale++;
                    else
                        blackFemale++;
            }
            if (0 != asiaMale || 0 != asiaFemale)
                buffer.append("黄色人种：").append(asiaMale).append("男")
                        .append(asiaFemale).append("女").append("\n");
            if (0 != whiteMale || 0 != whiteFemale)
                buffer.append("白色人种：").append(whiteMale).append("男")
                        .append(whiteFemale).append("女").append("\n");
            if (0 != blackMale || 0 != blackFemale)
                buffer.append("黑色人种：").append(blackMale).append("男")
                        .append(blackFemale).append("女").append("\n");
        }
        // 移除末尾空格
        buffer = new StringBuffer(buffer.substring(0, buffer.lastIndexOf("\n")));
        return buffer.toString();
    }

    /**
     * 提供给外部调用到人脸检测方法
     *
     * @param picUrl 待检测图片待访问地址
     * @return String
     */
    public static String detect(String picUrl) {
        String result = "未识别到人脸，请换一张清晰的照片再试！";
        List<Face> faceList = faceDetect(picUrl);
        if (faceList != null && faceList.size() > 0) {
            result = makeMessage(faceList);
        }
        return result;
    }

    public static void main(String[] args) {
        String picUrl = "http://pic11.nipic.com/20101111/6153002_002722872554_2.jpg";
        System.out.println(detect(picUrl));
    }
}
