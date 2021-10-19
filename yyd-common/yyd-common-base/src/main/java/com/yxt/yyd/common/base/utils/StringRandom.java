package com.yxt.yyd.common.base.utils;

import java.util.Random;

/**
 * 生成随机字符串
 *
 * @author dimengzhe
 */
public class StringRandom {
    /**
     * 随机生成字符串-规避I,l,O,o,0,1容易混淆的字符
     *
     * @param length 用户要求产生字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(56);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
