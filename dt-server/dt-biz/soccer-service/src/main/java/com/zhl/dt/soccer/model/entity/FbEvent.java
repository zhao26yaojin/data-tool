package com.zhl.dt.soccer.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FbEvent {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "是否删除")
    @TableLogic
    private Integer isDel;
}
