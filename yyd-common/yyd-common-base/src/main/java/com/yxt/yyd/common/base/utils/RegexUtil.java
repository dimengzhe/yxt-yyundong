package com.yxt.yyd.common.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dimengzhe
 * @date 2021/9/4 0:13
 * @description 常用正则验证工具类
 */

public class RegexUtil {

    /**
     * 正则表达式: 验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
    /**
     * 验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 验证手机号
     *
     * @param mobile 手机号
     * @return
     */
    public static boolean isMobile(String mobile) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(mobile) && Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 验证邮箱
     *
     * @param email 邮箱证号
     * @return
     */
    public static boolean isEmail(String email) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(email) && Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 验证汉字
     *
     * @param chinese 汉字
     * @return
     */
    public static boolean isChinese(String chinese) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(chinese) && Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 验证身份证号
     *
     * @param idCard 身份证号
     * @return
     */
    public static boolean isIDCard(String idCard) {
        return StringUtils.isNotBlank(idCard) && Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 严格验证身份证号是否正确
     *
     * @param IDStr 身份证号
     * @return
     * @throws ParseException
     */
    public static String IDCardValidate(String IDStr) throws ParseException {
        IDStr = IDStr.toLowerCase();
        String errorInfo = "";
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";

        if ((IDStr.length() != 15) && (IDStr.length() != 18)) {
            errorInfo = "身份证号码长度应为15位或18位。";
            return errorInfo;
        }

        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (!isNumeric(Ai)) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }

        String strYear = Ai.substring(6, 10);
        String strMonth = Ai.substring(10, 12);
        String strDay = Ai.substring(12, 14);
        if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(1) - Integer.parseInt(strYear) > 150)
                || (gc.getTime().getTime()
                - s.parse(strYear + "-" + strMonth + "-" + strDay)
                .getTime() < 0L)) {
            errorInfo = "身份证生日不在有效范围。";
            return errorInfo;
        }
        if ((Integer.parseInt(strMonth) > 12)
                || (Integer.parseInt(strMonth) == 0)) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if ((Integer.parseInt(strDay) > 31) || (Integer.parseInt(strDay) == 0)) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }

        Hashtable<String, String> h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }

        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (!Ai.equals(IDStr)) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }


        return "";
    }

    private static Hashtable<String, String> GetAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        return isNum.matches();
    }

    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);

        return m.matches();
    }

}
