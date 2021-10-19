package com.yxt.yyd.system.api.domain;

import com.yxt.yyd.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dimengzhe
 * @date 2021/9/3 23:06
 * @description 用户表
 */
@ApiModel(value = "用户表")
public class SystemUser extends BaseEntity {

    private static final long serialVersionUID = 2523985778789511425L;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "用户类型")
    private String userType;
    @ApiModelProperty(value = "用户头像")
    private String headImage;
    @ApiModelProperty(value = "是否是内测用户")
    private Integer isAlphaUser;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getIsAlphaUser() {
        return isAlphaUser;
    }

    public void setIsAlphaUser(Integer isAlphaUser) {
        this.isAlphaUser = isAlphaUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
