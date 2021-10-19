package com.yxt.yyd.system.biz.controller;

import cn.hutool.core.io.FastByteArrayOutputStream;
import com.google.code.kaptcha.Producer;
import com.yxt.yyd.common.base.redis.RedisUtil;
import com.yxt.yyd.common.base.utils.Base64;
import com.yxt.yyd.common.base.utils.uuid.IdUtils;
import com.yxt.yyd.common.core.constant.Constants;
import com.yxt.yyd.common.core.result.ResultBean;
import com.yxt.yyd.system.api.api.CaptchaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dimengzhe
 * @date 2021/9/3 17:46
 * @description 验证码
 */
@Controller
@RequestMapping("v1/captchas")
public class CaptchaController implements CaptchaApi {

    @Autowired
    private RedisUtil redisUtil;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 验证码类型
     */
    @Value("${yyd.captchaType}")
    private String captchaType;

    /**
     * 超时时间3min
     */
    private static int captchaExpires = 2 * 60;

    /**
     * 生成验证码
     */
    @Override
    public ResultBean getCode(HttpServletResponse response) {
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisUtil.set(verifyKey, code);
        redisUtil.expire(verifyKey, captchaExpires);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return ResultBean.fireFail().setMsg(e.getMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("img", Base64.encode(os.toByteArray()));
        return ResultBean.fireSuccess().setData(map);
    }
}
