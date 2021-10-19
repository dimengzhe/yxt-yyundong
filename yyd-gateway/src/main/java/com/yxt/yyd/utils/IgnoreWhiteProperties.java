package com.yxt.yyd.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dimengzhe
 * @date 2020/12/2 9:54
 * @description 放行白名单配置
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {

    /**
     * 放行白名单配置，网关不校验此处的白名单
     */
    private List<String> whites = new ArrayList<>();

    public List<String> getWhites() {
        return whites;
    }

    public void setWhites(List<String> whites) {
        this.whites = whites;
    }

    private List<String> whitesTwo = new ArrayList<>();

    public List<String> getWhitesTwo() {
        return whitesTwo;
    }

    public void setWhitesTwo(List<String> whitesTwo) {
        this.whitesTwo = whitesTwo;
    }
}
