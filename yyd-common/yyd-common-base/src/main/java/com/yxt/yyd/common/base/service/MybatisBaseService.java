package com.yxt.yyd.common.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxt.yyd.common.core.domain.BaseEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dimengzhe
 * @date 2021/9/3 17:07
 * @description
 */

public class MybatisBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    @SuppressWarnings("unchecked")
    private T entity() {
        T instance = null;
        Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
        try {
            instance = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public int deleteBySid(String sid) {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", sid);
        return baseMapper.deleteByMap(map);
    }

    public int updateBySid(Map<String, Object> map, String sid) {
        T t = fetchBySid(sid);
        if (t == null) {
            return -1;
        }
        t = map2entity(map, t);

        if (t instanceof BaseEntity) {
            if (((BaseEntity) t).getLockVersion() != null) {
                ((BaseEntity) t).setLockVersion(((BaseEntity) t).getLockVersion() + 1);
            }
        }
        return baseMapper.updateById(t);
    }

    /**
     * 描述 : 将Map转换为实体对象. <br/>
     * <p>
     *
     * @param map
     * @param t
     * @return
     */
    @SuppressWarnings("unchecked")
    protected T map2entity(Map<String, Object> map, T t) {
        map.remove("id");
        map.remove("sid");
        map.remove("createTime");
        map.remove("modifyTime");
        if (t instanceof BaseEntity) {
            ((BaseEntity) t).setModifyTime(new Date());
            t = (T) (BaseEntity) t;
        }
        try {
            BeanUtils.populate(t, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    public T fetchBySid(String sid) {
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.eq("sid", sid);
        return baseMapper.selectOne(qw);
    }

}
