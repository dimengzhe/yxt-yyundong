package com.yxt.yyd.system.api.domain;

import com.yxt.yyd.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author dimengzhe
 * @date 2021/9/3 23:30
 * @description 用户真实信息(实名认证)
 */
@ApiModel(value = "用户真实信息")
public class SystemUserRealMess extends BaseEntity {
    private static final long serialVersionUID = 2967171578676663770L;

    @ApiModelProperty(value = "联系方式")
    private String mobile;
    @ApiModelProperty(value = "区域sid")
    private String regionSid;
    @ApiModelProperty(value = "通讯地址")
    private String address;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "生日")
    private Date birthday;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegionSid() {
        return regionSid;
    }

    public void setRegionSid(String regionSid) {
        this.regionSid = regionSid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
