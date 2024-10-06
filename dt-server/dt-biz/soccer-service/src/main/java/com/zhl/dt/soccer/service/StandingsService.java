package com.zhl.dt.soccer.service;

import com.zhl.dt.soccer.model.entity.FbStandings;
import com.zhl.dt.soccer.model.qo.StandingsQO;
import com.zhl.dt.soccer.model.vo.StandingsVO;
import com.zhl.dt.util.rest.PageBean;
import com.zhl.dt.util.rest.PageParam;

import java.util.List;

public interface StandingsService {

    List<FbStandings> selectList(StandingsQO qo);

    List<StandingsVO> selectVOList(StandingsQO qo);

    PageBean<StandingsVO> selectVOPage(StandingsQO qo, PageParam pageParam);
}
