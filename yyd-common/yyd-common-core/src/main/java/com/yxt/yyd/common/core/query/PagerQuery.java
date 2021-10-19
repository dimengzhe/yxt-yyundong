package com.yxt.yyd.common.core.query;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author dimengzhe
 * @date 2021/9/3 16:41
 * @description
 */

public class PagerQuery<T extends Query> implements Serializable {

    @ApiModelProperty(value = "当前页号", example = "1")
    private long current = 1L;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private long size = 10L;

    @ApiModelProperty("查询条件的项")
    private T params;

    public PagerQuery() {
    }

    public PagerQuery(long current) {
        this.current = current;
    }

    public PagerQuery(long current, long size) {
        this.size = size;
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public PagerQuery setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return current;
    }

    public PagerQuery setCurrent(long current) {
        this.current = current;
        return this;
    }

    public T getParams() {
        return params;
    }

    public PagerQuery setParams(T params) {
        this.params = params;
        return this;
    }

    public void fromPagerQuery(PagerQuery s_query) {
        this.setCurrent(s_query.getCurrent()).setSize(s_query.getSize()).setParams(params.fromMap(s_query.getParams().toMap()));
    }
}
