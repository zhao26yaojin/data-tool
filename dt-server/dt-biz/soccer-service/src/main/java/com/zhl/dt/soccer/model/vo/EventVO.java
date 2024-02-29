package com.zhl.dt.soccer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EventVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String name;
}
