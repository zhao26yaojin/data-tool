package com.zhl.dt.soccer.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhl.dt.soccer.model.entity.FbEvent;
import com.zhl.dt.soccer.model.qo.EventQO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper extends BaseMapper<FbEvent> {

    default LambdaQueryWrapper getQueryWrapper(EventQO qo) {
        LambdaQueryWrapper<FbEvent> wrapper = Wrappers.lambdaQuery(FbEvent.class);

        return wrapper;
    }
}