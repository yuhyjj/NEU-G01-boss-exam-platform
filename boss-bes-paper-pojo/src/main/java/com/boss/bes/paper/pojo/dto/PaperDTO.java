package com.boss.bes.paper.pojo.dto;


import com.boss.bes.paper.pojo.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PaperDTO extends BaseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 试卷名
     */

    private String paperName;

    /**
     * 试卷类型
     */

    private Long paperType;

    /**
     * 试卷难度
     */
    private Long difficulty;

    /**
     * 组卷人
     */

    private String combExamMan;

    /**
     * 组卷时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
     * 下载次数
     */

    private Integer downloadTimes;

    /**
     * 发布次数
     */

    private Integer publishTimes;
    /**
     * 状态位
     */
    private Integer status;

    private Integer pageNum;

    private Integer pageSize;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private Boolean templateTag;


}
