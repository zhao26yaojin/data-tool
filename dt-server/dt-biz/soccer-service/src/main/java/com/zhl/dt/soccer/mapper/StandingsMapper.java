package com.zhl.dt.soccer.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhl.dt.soccer.model.entity.FbStandings;
import com.zhl.dt.soccer.model.qo.StandingsQO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StandingsMapper extends BaseMapper<FbStandings> {

    default LambdaQueryWrapper getQueryWrapper(StandingsQO qo) {
        LambdaQueryWrapper<FbStandings> wrapper = Wrappers.lambdaQuery(FbStandings.class);

        wrapper.eq(qo.getId() != null, FbStandings::getId, qo.getId())
                .eq(qo.getEventId() != null, FbStandings::getEventId, qo.getEventId());

        return wrapper;
    }
}
