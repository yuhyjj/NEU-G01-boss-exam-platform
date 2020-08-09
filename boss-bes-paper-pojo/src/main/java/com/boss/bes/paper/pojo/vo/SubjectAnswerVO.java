package com.boss.bes.paper.pojo.vo;

import boss.brs.xtrain.dataconvention.pojo.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SubjectAnswerVO extends BaseVO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String answer;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long rightAnswer;
}
