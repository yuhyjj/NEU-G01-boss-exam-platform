package com.boss.bes.paper.pojo.vo;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PSubjectVO extends SubjectVO {
    private BigDecimal score;
}
