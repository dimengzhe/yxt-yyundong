package com.yxt.yyd.system.api.api;

import com.yxt.yyd.common.core.result.ResultBean;
import com.yxt.yyd.system.api.fallback.CaptchaApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dimengzhe
 * @date 2021/9/3 17:33
 * @description 验证码
 */
@FeignClient(
        contextId = "yxt-yyd-system-Captcha",
        name = "yxt-yyd-system",
        path = "v1/captchas",
        fallback = CaptchaApiFallback.class)
public interface CaptchaApi {

    @GetMapping("/captchaImage")
    @ResponseBody
    ResultBean getCode(HttpServletResponse response);
}
