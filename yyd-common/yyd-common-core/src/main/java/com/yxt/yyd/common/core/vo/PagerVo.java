package com.yxt.yyd.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author dimengzhe
 * @date 2021/9/3 16:24
 * @description
 */
@ApiModel(description = "返回的分页结果")
public class PagerVo<T> implements Serializable {

    @ApiModelProperty("总页数")
    private long pages = 1L;
    @ApiModelProperty("总记录数")
    private long total = 0L;
    @ApiModelProperty("每页记录数")
    private long size = 15L;
    @ApiModelProperty("当前页号")
    private long current = 1L;

    @ApiModelProperty("当前页的数据")
    private List<T> records = Collections.emptyList();

    public PagerVo() {
        this.pages = 1L;
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.records = Collections.emptyList();
    }

    public PagerVo(long current) {
        this.pages = 1L;
        this.total = 0L;
        this.size = 10L;
        this.current = current;
        this.records = Collections.emptyList();
    }

    public long getPages() {
        return pages;
    }

    public PagerVo<T> setPages(long pages) {
        this.pages = pages;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PagerVo<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getSize() {
        return size;
    }

    public PagerVo<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return current;
    }

    public PagerVo<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public List<T> getRecords() {
        return records;
    }

    public PagerVo<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }


}
