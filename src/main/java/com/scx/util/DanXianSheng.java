package com.scx.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自动回复工具类
 *
 * @author 宋程玺
 * @date 2015-08-10
 */
public class DanXianSheng {

    /**
     * 主菜单
     */
    public static String getMainMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("您好，欢迎关注蛋鲜生/鼓掌[呲牙]/::B，请回复数字选择服务：").append("\n\n");
        sb.append("1 <a href=\"www.baidu.com\">图文消息</a>").append("\n");
        sb.append("2 多图文消息\ue159").append("\n");
        sb.append("3 实时天气\ue13d").append("\n");
        sb.append("4 快递查询\ue03c").append("\n");
        sb.append("5 历史上的今天").append("\n");
        sb.append("6 翻译").append("\n");
        sb.append("7 人脸识别").append("\n");
        sb.append("回复\"?\"显示此帮助菜单").append("\n");
        return sb.toString();
    }

    /**
     * 判断是否是QQ表情,支持多个表情
     *
     * @param content
     * @return
     */
    public static boolean isQqFace(String content) {
        boolean result = false;
        // 判断QQ表情的正则表达式
        String qqfaceRegex = "(/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::\\$|/::X|/::Z|/::'\\(|/::-\\"
                + "||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\"
                + "|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\"
                + "(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|"
                + "/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|"
                + "/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|"
                + "/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>){1,}";

        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * 将微信消息中的CreateTime转换成标准格式时间（yyyy-MM-dd HH:mm:ss）
     *
     * @param createTime
     * @return
     */
    public static String formatTime(String createTime) {
        // 将微信传入的CreateTime转换成long类型，在乘1000成毫秒
        Long msgCreateTime = Long.parseLong(createTime) * 1000L;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(msgCreateTime));
    }

    /**
     * emoji表情转换（hex －> utf-16）
     *
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }

    /**
     * 翻译使用指南
     */
    public static String getTranslateUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(emoji(0xe148)).append("Q译通使用指南").append("\n\n");
        buffer.append("Q译通为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
        buffer.append("    中 -> 英").append("\n");
        buffer.append("    英 -> 中").append("\n");
        buffer.append("    日 -> 中").append("\n\n");
        buffer.append("使用示例：").append("\n");
        buffer.append("    翻译我是中国人").append("\n");
        buffer.append("    翻译dream").append("\n");
        buffer.append("    翻译さようなら").append("\n\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }

    /**
     * 人脸检测帮助菜单
     */
    public static String getFaceUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("人脸检测使用指南").append("\n\n");
        buffer.append("发送一张清晰的照片，就能帮你分析出种族、年龄、性别等信息").append("\n");
        buffer.append("快来试试你是不是长得太着急");
        return buffer.toString();
    }

    /**
     * 快递查询使用说明
     */
    public static String getKuaiDiUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("快递查询使用指南\ue112\ue10d").append("\n\n");
        buffer.append("发送：快递公司名称+快递单号，即可对快递信息进行查询").append("\n");
        buffer.append("例如：顺丰快递10001001012").append("\n");
        buffer.append("邮政01002010021").append("\n\n");
        buffer.append("快来试一试吧！");
        return buffer.toString();
    }

    /**
     * 实时天气使用说明
     */
    public static String getWeatherUsage(){
        StringBuilder sb = new StringBuilder();
        sb.append("实时天气使用指南\ue04a\ue04b").append("\n\n");
        sb.append("发送：天气+城市，即可对天气信息进行查询").append("\n");
        sb.append("例如：天气汾阳").append("\n\n");
        sb.append("快来试一试吧！");
        return sb.toString();
    }

}
