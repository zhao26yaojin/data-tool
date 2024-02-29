package com.zhl.dt.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhl.dt.soccer.mapper.StandingsMapper;
import com.zhl.dt.soccer.model.convert.StandingsConvertor;
import com.zhl.dt.soccer.model.entity.FbStandings;
import com.zhl.dt.soccer.model.qo.StandingsQO;
import com.zhl.dt.soccer.model.vo.StandingsVO;
import com.zhl.dt.soccer.service.StandingsService;
import com.zhl.dt.util.rest.PageBean;
import com.zhl.dt.util.rest.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zhl.dt.util.constant.DBConstant.LIMIT_ONE_SQL;

@Service
@Slf4j
public class StandingsServiceImpl extends ServiceImpl<StandingsMapper, FbStandings> implements StandingsService {

    @Override
    public List<FbStandings> selectList(StandingsQO qo) {
        LambdaQueryWrapper<FbStandings> wrapper = baseMapper.getQueryWrapper(qo);

        List<FbStandings> entityList = this.list(wrapper);

        return entityList;
    }

    @Override
    public List<StandingsVO> selectVOList(StandingsQO qo) {
        List<FbStandings> entityList = selectList(qo);

        List<StandingsVO> voList = StandingsConvertor.entityListToVOList(entityList);

        return voList;
    }

    @Override
    public PageBean<StandingsVO> selectVOPage(StandingsQO qo, PageParam pageParam) {
        LambdaQueryWrapper<FbStandings> wrapper = baseMapper.getQueryWrapper(qo);

        Page<FbStandings> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());

        baseMapper.selectPage(page, wrapper);

        List<StandingsVO> wordVOList = StandingsConvertor.entityListToVOList(page.getRecords());

        return new PageBean<>(pageParam.getCurrent(), pageParam.getSize(), page.getTotal(), wordVOList);
    }
}
