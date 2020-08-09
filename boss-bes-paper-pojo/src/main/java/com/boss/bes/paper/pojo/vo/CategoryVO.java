package com.boss.bes.paper.pojo.vo;

import boss.brs.xtrain.dataconvention.pojo.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryVO extends BaseVO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long status;
    private String name;
    private String remark;
    private Long orgId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    private Long version;
}

