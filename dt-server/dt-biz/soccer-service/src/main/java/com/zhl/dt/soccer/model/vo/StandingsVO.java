package com.zhl.dt.soccer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "足球积分榜")
public class StandingsVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "team id")
    private Long teamId;

    @Schema(description = "event id")
    private Long eventId;

    @Schema(description = "country id")
    private Long countryId;

    @Schema(description = "球队名称")
    private String teamName;

    @Schema(description = "比赛场次")
    private Integer gamePlay;

    @Schema(description = "胜")
    private Integer win;

    @Schema(description = "平")
    private Integer draw;

    @Schema(description = "负")
    private Integer loss;

    @Schema(description = "进球")
    private Integer goalsFor;

    @Schema(description = "失球")
    private Integer goalsAgainst;

    @Schema(description = "净胜球")
    private Integer goalsDifferential;

    @Schema(description = "分数")
    private Integer points;
}
