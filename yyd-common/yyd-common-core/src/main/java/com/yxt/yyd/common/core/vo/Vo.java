package com.yxt.yyd.common.core.vo;

import cn.hutool.core.bean.BeanUtil;
import com.yxt.yyd.common.core.domain.Entity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author dimengzhe
 * @date 2021/9/3 16:21
 * @description
 */

public interface Vo extends Serializable {

    default Map<String, Object> toMap() {
        return BeanUtil.beanToMap(this);
    }

    default <V extends Vo> V fromMap(Map<String, Object> map) {
        return (V) BeanUtil.fillBeanWithMap(map, this, false);
    }

    default <E extends Entity> void fromEntity(E e) {
        BeanUtil.copyProperties(e, this);
    }
}
