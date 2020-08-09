package com.boss.bes.paper.pojo.vo;

import boss.brs.xtrain.dataconvention.pojo.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombExamConfigItemVO extends BaseVO {
    private Integer num;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long difficult;
    private String difficultName;
    private BigDecimal score;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    private String categoryName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long subjectTypeId;
    private String subjectTypeName;
}

