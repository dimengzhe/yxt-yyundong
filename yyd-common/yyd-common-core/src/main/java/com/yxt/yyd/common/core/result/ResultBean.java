package com.yxt.yyd.common.core.result;

import com.yxt.yyd.common.core.constant.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author dimengzhe
 * @date 2021/8/23 16:42
 * @description
 */
@ApiModel(description = "返回结果")
public class ResultBean<T> implements Serializable {

  @ApiModelProperty("返回结果的说明")
  private String msg;

  @ApiModelProperty("结果状态码")
  private String code;

  @ApiModelProperty("业务数据")
  private T data;

  @ApiModelProperty(value = "是否成功")
  private Boolean success;

  public boolean getSuccess() {
    return success;
  }

  public ResultBean<T> setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public ResultBean<T> setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ResultBean<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public T getData() {
    return data;
  }

  public ResultBean<T> setData(T data) {
    this.data = data;
    return this;
  }

  public ResultBean() {}

  public ResultBean<T> success() {
    this.setSuccess(true);
    this.setCode(String.valueOf(HttpStatus.SUCCESS));
    this.setMsg("操作成功！");
    return this;
  }

  public ResultBean<T> fail() {
    this.setSuccess(false);
    this.setCode(String.valueOf(HttpStatus.ERROR));
    this.setMsg("操作失败！");
    return this;
  }

  public static <T> ResultBean<T> fireSuccess() {
    ResultBean<T> rb = new ResultBean<T>();
    rb.setSuccess(true);
    rb.setCode(String.valueOf(HttpStatus.SUCCESS));
    rb.setMsg("操作成功！");
    return rb;
  }

  public static <T> ResultBean<T> fireFail() {
    ResultBean<T> rb = new ResultBean<T>();
    rb.setSuccess(false);
    rb.setCode(String.valueOf(HttpStatus.ERROR));
    rb.setMsg("操作失败！");
    return rb;
  }
}
