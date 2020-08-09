package com.boss.bes.paper.pojo.dto;

import com.boss.bes.paper.pojo.dto.common.BaseDTO;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PaperSubjectDTO extends BaseDTO {

    /**
     * 试卷试题ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long paperId;
    /**
     * 题目
     */
    private String subject;

    /**
     * 题目类别
     */
    private String subjectCategory;

    /**
     * 题型
     */
    private Long questionType;

    /**
     * 题目难度
     */
    private Long difficult;

    /**
     * 分数
     */
    private BigDecimal score;
    /**
     * 题目数量
     */
    private Integer num;


    /**
     * 题目答案
     */
    private List<PaperSubjectAnswer> answers;
}
