package com.zhl.dt.soccer.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StandingsQO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "event id")
    private Long eventId;
}
