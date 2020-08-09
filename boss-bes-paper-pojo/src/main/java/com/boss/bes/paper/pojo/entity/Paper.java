package com.boss.bes.paper.pojo.entity;


import com.boss.bes.paper.pojo.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "tb_paper")
public class Paper extends BaseEntity {
    /**
     * 试卷ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Id
    private Long id;

    /**
     * 试卷名
     */
    @Column(name = "paper_name")
    private String paperName;

    /**
     * 试卷类型
     */
    @Column(name = "paper_type")
    private Long paperType;

    /**
     * 试卷难度
     */
    private Long difficulty;

    /**
     * 组卷人
     */
    @Column(name = "comb_exam_man")
    private String combExamMan;

    /**
     * 组卷时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "comb_exam_time")
    private Date combExamTime;

    /**
     * 试卷总分
     */
    private BigDecimal score;

    /**
     * 试卷描述
     */
    private String discribe;

    /**
     * 模版标记
     */
    @Column(name = "template_tag")
    private Boolean templateTag;

    /**
     * 下载次数
     */
    @Column(name = "download_times")
    private Integer downloadTimes;

    /**
     * 发布次数
     */
    @Column(name = "publish_times")
    private Integer publishTimes;

    /**
     * 状态位
     */
    private Integer status;

    /**
     * 组织机构id
     */
    @Column(name = "organization_id")
    private Long organizationId;

    /**
     * 公司id
     */
    @Column(name = "company_id")
    private Long companyId;



}