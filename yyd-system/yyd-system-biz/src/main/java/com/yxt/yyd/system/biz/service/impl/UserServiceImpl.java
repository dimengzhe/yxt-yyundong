package com.yxt.yyd.system.biz.service.impl;

import com.yxt.yyd.common.base.redis.RedisUtil;
import com.yxt.yyd.common.base.service.MybatisBaseService;
import com.yxt.yyd.common.base.utils.MsgWs;
import com.yxt.yyd.common.core.result.ResultBean;
import com.yxt.yyd.system.api.domain.SystemUser;
import com.yxt.yyd.system.biz.mapper.UserMapper;
import com.yxt.yyd.system.biz.service.IUserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * @author dimengzhe
 * @date 2021/9/4 0:20
 * @description
 */
@Service
public class UserServiceImpl extends MybatisBaseService<UserMapper, SystemUser> implements IUserSerivce {

    /**
     * 验证码长度
     */
    static final int LENGTH_OF_CODE = 4;
    /**
     * 调用发送短信接口返回值
     */
    static final String RESULT_CODE = "1";

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发送短信验证码
     *
     * @param mobile           手机号
     * @param verificationCode 验证码
     * @return ResultBean
     */
    @Override
    public ResultBean getVerificationCode(String mobile, String verificationCode) {
        for (int i = 0; i < LENGTH_OF_CODE; i++) {
            // 定义随机类
            Random random = new Random();
            // 返回[0,10)集合中的整数，注意不包括10
            int result = random.nextInt(10);
            // +1后，[0,10)集合变为[1,11)集合，满足要求
            int num = result;
            verificationCode = verificationCode + num;
        }
        String verificationCode1 = "个人注册验证码：" + verificationCode + ",有效期5分钟，如非本人操作，请忽略该短信。";
        try {
            //============
            //当前时间秒数
            Long timemili = System.currentTimeMillis() / 1000;
            System.out.println("当前的秒数" + timemili);
            /*在这里写一个定时的for循环，用来取redis的手机号码信息，然后查询手机号码开始的时间，若是大于等于五分钟就给删除这个键值*/
            //创建多线程定时任务，延迟1s启动，每隔1s执行一次，是前一个任务开始时就开始计算时间间隔，但是会等上一个任务结束在开始下一个
            ScheduledExecutorService scheduledExecutorService = newScheduledThreadPool(10);
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    //判断这个键的值是不是超过五分钟，是的话就删除掉这个键
                    System.out.println("计算结果" + (System.currentTimeMillis() / 1000 - Long.parseLong(redisUtil.get(mobile).substring(4))));
                    if (System.currentTimeMillis() / 1000 - Long.parseLong(redisUtil.get(mobile).substring(4)) > 300) {
                        redisUtil.remove(mobile);
                    }
                    System.out.println(mobile);

                }
            }, 1, 2, TimeUnit.SECONDS);

            //设置键值的时候先查询是否存在这个键值对，存在的话查看时长，不存在的话直接发送短信
            boolean str = redisUtil.exists(mobile);
            if (!str) {
                redisUtil.set(mobile, (verificationCode + timemili));
                String result = MsgWs.SendWaitWorkMsg(mobile, verificationCode1);
                if (!RESULT_CODE.equals(result)) {
                    return ResultBean.fireFail().setMsg("发送短信验证码失败");
                }
            } else {
                String strT = redisUtil.get(mobile);
                //查看请求间隔,默认是一分钟,小于一分钟继续等待，超过一分钟发送短信
                if (timemili - Long.parseLong(strT.substring(4)) < 60) {
                    return ResultBean.fireFail().setMsg("请等待一分钟后再次重试!");
                } else {
                    //发送短信
                    redisUtil.set(mobile, (verificationCode + timemili));
                    String result = MsgWs.SendWaitWorkMsg(mobile, verificationCode1);
                    if (!RESULT_CODE.equals(result)) {
                        return ResultBean.fireFail().setMsg("发送短信验证码失败");
                    }
                }
            }
        } catch (Exception e) {
            return ResultBean.fireFail().setMsg("发送短信验证码失败");
        }
        return ResultBean.fireSuccess().setMsg("发送短信验证码成功");
    }

}
