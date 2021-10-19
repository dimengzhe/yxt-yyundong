package com.yxt.yyd.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxt.yyd.common.core.result.ResultBean;
import com.yxt.yyd.system.api.domain.SystemUser;

/**
 * @author dimengzhe
 * @date 2021/9/4 0:20
 * @description
 */

public interface IUserSerivce extends IService<SystemUser> {

    ResultBean getVerificationCode(String mobile, String verificationCode);
}
