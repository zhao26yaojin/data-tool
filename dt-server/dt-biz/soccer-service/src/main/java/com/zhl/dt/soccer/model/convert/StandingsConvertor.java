package com.zhl.dt.soccer.model.convert;

import com.zhl.dt.soccer.model.entity.FbStandings;
import com.zhl.dt.soccer.model.vo.StandingsVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class StandingsConvertor {

    public static StandingsVO entityToVO(FbStandings entity) {
        if (entity == null) {
            return null;
        }

        StandingsVO vo = new StandingsVO();
        BeanUtils.copyProperties(entity, vo);

        return vo;
    }

    public static List<StandingsVO> entityListToVOList(List<FbStandings> entityList) {
        if (entityList == null) {
            return null;
        }

        List<StandingsVO> voList = Lists.newArrayList();

        for (FbStandings entity : entityList) {
            StandingsVO vo = entityToVO(entity);

            voList.add(vo);
        }

        return voList;
    }
}
