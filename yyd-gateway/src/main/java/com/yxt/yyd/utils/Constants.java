package com.yxt.yyd.utils;

/**
 * @author dimengzhe
 * @date 2020/12/2 9:56
 * @description 通用常量信息
 */

public class Constants {

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 5000;

    public static final boolean fail = false;
    public static final boolean success = true;

    /**
     * 令牌有效期（分钟）
     */
    public final static long TOKEN_EXPIRE = 4*60;
    public final static long TOKEN_EXPIRE_APP = 15*24*60;
}
