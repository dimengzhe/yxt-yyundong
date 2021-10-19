package com.yxt.yyd.common.base.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 封装各种格式的编码解码工具类. 1.Commons-Codec的 hex/base64 编码 2.自制的base62 编码 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 *
 * @author dimengzhe
 * @date 2020/9/11 13:38
 * @description 编码解码工具类
 */
public class Encodes {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    private static final char[] BASE62 =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String SALT = "jlzx@yxt?";

    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    /**
     * Hex解码.
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }

    /**
     * Base64解码.
     */
    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Base62编码。
     */
    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }

    /**
     * Html 转码.
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml10(xml);
    }

    /**
     * Xml 解码.
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     */
    public static String urlDecode(String part) {

        try {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String md5(String str) {
        return digest("MD5", str + SALT);
    }

    public static String sha1(CharSequence cs) {
        return digest("SHA1", cs);
    }

    public static String digest(String algorithm, CharSequence cs) {
        return digest(algorithm, StringUtils.getBytesUTF8(null == cs ? "" : cs), null, 1);
    }

    public static String digest(String algorithm, byte[] bytes, byte[] salt, int iterations) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                md.update(salt);
            }
            byte[] hashBytes = md.digest(bytes);
            for (int i = 1; i < iterations; i++) {
                md.reset();
                hashBytes = md.digest(hashBytes);
            }
            return fixedHexString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String fixedHexString(byte[] hashBytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hashBytes.length; i++) {
            sb.append(Integer.toString((hashBytes[i] & 0xFF) + 256, 16).substring(1));
        }

        return sb.toString();
    }

    public static String md5(String str, boolean isShort) {
        if (isShort) {
            return md5(str).substring(8, 24);
        }
        return md5(str);
    }
}
