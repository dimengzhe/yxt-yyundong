package com.yxt.yyd.common.core.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author dimengzhe
 * @date 2021/9/3 16:06
 * @description
 */
public class Entity implements Serializable {

    @ApiModelProperty("ID，唯一编号")
    private Integer id;

    @ApiModelProperty("ID值的字符串形式")
    public String getIdStr() {
        if (null == id) {
            return "";
        }
        return "" + id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClassName:" + getClass().getName() + ";id:" + getId();
    }

    @Override
    public int hashCode() {
        int id2 = Long.hashCode(getId());
        return super.hashCode() + id2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) obj;
        return this.getId().equals(entity.getId());
    }
}
