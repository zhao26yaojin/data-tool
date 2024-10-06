package com.zhl.dt.soccer.model.convert;

import com.zhl.dt.soccer.model.entity.FbEvent;
import com.zhl.dt.soccer.model.vo.EventVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class EventConvertor {

    public static EventVO entityToVO(FbEvent entity) {
        if (entity == null) {
            return null;
        }

        EventVO vo = new EventVO();
        BeanUtils.copyProperties(entity, vo);

        return vo;
    }

    public static List<EventVO> entityListToVOList(List<FbEvent> entityList) {
        if (entityList == null) {
            return null;
        }

        List<EventVO> voList = Lists.newArrayList();

        for (FbEvent entity : entityList) {
            EventVO vo = entityToVO(entity);

            voList.add(vo);
        }

        return voList;
    }
}
