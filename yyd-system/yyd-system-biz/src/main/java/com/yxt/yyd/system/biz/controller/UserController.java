package com.yxt.yyd.system.biz.controller;

import com.yxt.yyd.common.base.utils.RegexUtil;
import com.yxt.yyd.common.core.result.ResultBean;
import com.yxt.yyd.system.api.api.UserApi;
import com.yxt.yyd.system.biz.service.IUserSerivce;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author dimengzhe
 * @date 2021/9/4 0:11
 * @description 用户管理
 */
@Controller
public class UserController implements UserApi {

    @Autowired
    private IUserSerivce userSerivce;


    @Override
    public ResultBean getCode(String mobile) {
        if (StringUtils.isBlank(mobile) || !RegexUtil.isMobile(mobile)) {
            return new ResultBean().fail().setMsg("请输入正确的手机号");
        }
        String verificationCode = "";
        return userSerivce.getVerificationCode(mobile, verificationCode);
    }
}
