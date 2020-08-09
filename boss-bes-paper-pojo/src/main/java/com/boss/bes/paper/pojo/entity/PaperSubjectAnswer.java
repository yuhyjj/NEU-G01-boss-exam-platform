package com.boss.bes.paper.pojo.entity;

import com.boss.bes.paper.pojo.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "tb_paper_subject_answer")
public class PaperSubjectAnswer extends BaseEntity {
    /**
     * 试卷试题答案id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Id
    private Long id;

    /**
     * 试题id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "subject_id")
    private Long subjectId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 正确
     */
    @Column(name = "right_answer")
    private Boolean rightAnswer;

    /**
     * 图片URL
     */
    @Column(name = "picture_url")
    private String pictureUrl;

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
}