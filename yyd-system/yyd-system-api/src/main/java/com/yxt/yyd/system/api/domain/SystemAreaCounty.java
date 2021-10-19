package com.yxt.yyd.system.api.domain;

import com.yxt.yyd.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dimengzhe
 * @date 2021/9/8 14:51
 * @description
 */
@ApiModel(value = "区域-县区")
public class SystemAreaCounty extends BaseEntity {
    private static final long serialVersionUID = -6211809776686543311L;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "市sid")
    private String citySid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCitySid() {
        return citySid;
    }

    public void setCitySid(String citySid) {
        this.citySid = citySid;
    }
}
