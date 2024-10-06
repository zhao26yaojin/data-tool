package com.zhl.dt.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhl.dt.soccer.mapper.EventMapper;
import com.zhl.dt.soccer.model.convert.EventConvertor;
import com.zhl.dt.soccer.model.entity.FbEvent;
import com.zhl.dt.soccer.model.qo.EventQO;
import com.zhl.dt.soccer.model.vo.EventVO;
import com.zhl.dt.soccer.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventServiceImpl  extends ServiceImpl<EventMapper, FbEvent> implements EventService {

    @Override
    public List<FbEvent> selectList(EventQO qo) {
        LambdaQueryWrapper<FbEvent> wrapper = baseMapper.getQueryWrapper(qo);

        List<FbEvent> entityList = this.list(wrapper);

        return entityList;
    }

    @Override
    public List<EventVO> selectVOList(EventQO qo) {
        List<FbEvent> entityList = selectList(qo);

        List<EventVO> voList = EventConvertor.entityListToVOList(entityList);

        return voList;
    }
}