package com.yxt.yyd.system.api.domain;

import com.yxt.yyd.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dimengzhe
 * @date 2021/9/8 14:45
 * @description
 */
@ApiModel(value = "区域-省")
public class SystemAreaProvince extends BaseEntity {

    private static final long serialVersionUID = -1698432455754107383L;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "编码")
    private String code;

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
}
