package com.yxt.yyd.system.api.domain;

import com.yxt.yyd.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dimengzhe
 * @date 2021/9/8 14:47
 * @description
 */
@ApiModel(value = "区域-市")
public class SystemAreaCity extends BaseEntity {

    private static final long serialVersionUID = 5718633731734574679L;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "省级sid")
    private String provinceSid;

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

    public String getProvinceSid() {
        return provinceSid;
    }

    public void setProvinceSid(String provinceSid) {
        this.provinceSid = provinceSid;
    }
}
