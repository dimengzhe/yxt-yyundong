package com.yxt.yyd.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.UUID;

/**
 * @author dimengzhe
 * @date 2021/9/3 16:06
 * @description
 */
public class BaseEntity extends Entity {

    @ApiModelProperty("字符型编号")
    private String sid = UUID.randomUUID().toString();

    @ApiModelProperty("记录版本，锁")
    private Integer lockVersion = 0;

    @ApiModelProperty("记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = new Date();

    @ApiModelProperty("记录最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime = new Date();

    @ApiModelProperty("记录状态值")
    private Integer state = 1;

    @ApiModelProperty("记录是否可用，1:可用(默认)，0:不可用")
    private Integer isEnable = 1;

    @ApiModelProperty("记录是否被删除，0:未删除(默认)，1:已经删除")
    private Integer isDelete = 0;

    @ApiModelProperty("备注信息")
    private String remarks;

    @ApiModelProperty("创建者")
    private String createBySid;

    @ApiModelProperty("更新者")
    private String updateBySid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBySid() {
        return createBySid;
    }

    public void setCreateBySid(String createBySid) {
        this.createBySid = createBySid;
    }

    public String getUpdateBySid() {
        return updateBySid;
    }

    public void setUpdateBySid(String updateBySid) {
        this.updateBySid = updateBySid;
    }
}
