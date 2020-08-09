package com.boss.bes.paper.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class CombExamConfigItemDTO implements Serializable {
    private Integer num;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long difficult;

    private BigDecimal score;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long subjectTypeId;
}

