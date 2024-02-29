package com.zhl.dt.soccer.controller;

import com.zhl.dt.soccer.model.qo.StandingsQO;
import com.zhl.dt.soccer.model.vo.StandingsVO;
import com.zhl.dt.soccer.service.StandingsService;
import com.zhl.dt.util.rest.PageBean;
import com.zhl.dt.util.rest.PageParam;
import com.zhl.dt.util.rest.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "足球积分榜模块")
@RestController
@RequestMapping("/standing")
@Slf4j
public class StandingsController {

    @Autowired
    StandingsService standingsService;

    @Operation(summary = "查询积分榜分页列表")
    @GetMapping("/page")
    public RestResponse<PageBean<StandingsVO>> page(StandingsQO wordQO, PageParam pageParam) {
        PageBean<StandingsVO> pageBean = standingsService.selectVOPage(wordQO, pageParam);

        return RestResponse.ok(pageBean);
    }

    @Operation(summary = "查询积分榜列表")
    @GetMapping("/list")
    public RestResponse<List<StandingsVO>> list(StandingsQO wordQO) {
        List<StandingsVO> voList = standingsService.selectVOList(wordQO);

        return RestResponse.ok(voList);
    }
}
