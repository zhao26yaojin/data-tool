package com.zhl.dt.soccer.service;

import com.zhl.dt.soccer.model.entity.FbEvent;
import com.zhl.dt.soccer.model.qo.EventQO;
import com.zhl.dt.soccer.model.vo.EventVO;

import java.util.List;

public interface EventService {

    List<FbEvent> selectList(EventQO qo);

    List<EventVO> selectVOList(EventQO qo);
}
