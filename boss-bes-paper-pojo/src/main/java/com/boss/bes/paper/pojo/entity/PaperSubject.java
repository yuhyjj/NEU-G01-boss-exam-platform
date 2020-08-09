package com.boss.bes.paper.pojo.entity;

import com.boss.bes.paper.pojo.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "tb_paper_subject")
public class PaperSubject extends BaseEntity {
    /**
     * 试题ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Id
    private Long id;
    /**
     * 试卷ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "paper_id")
    private Long paperId;

    /**
     * 题目
     */
    private String subject;

    /**
     * 题目类别
     */
    @Column(name = "subject_category")
    private String subjectCategory;

    /**
     * 题型
     */
    @Column(name = "question_type")
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
     * 保留字段1
     */
    private String field1;

    /**
     * 保留字段2
     */
    private String field2;

    /**
     * 保留字段3
     */
    private String field3;
    /**
     * 题目答案
     */
    private List<PaperSubjectAnswer> answers;
}