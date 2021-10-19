package com.yxt.yyd.system.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxt.yyd.system.api.domain.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dimengzhe
 * @date 2021/9/4 0:23
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<SystemUser> {
}
