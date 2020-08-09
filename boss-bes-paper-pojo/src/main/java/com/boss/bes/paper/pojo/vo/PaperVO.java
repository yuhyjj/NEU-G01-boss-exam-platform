package com.boss.bes.paper.pojo.vo;

import boss.brs.xtrain.dataconvention.pojo.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PaperVO extends BaseVO {
    /**
     * 试卷ID
     */
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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     * 版本
     */
    private Long version;
    /**
     * 组织机构id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long organizationId;

    /**
     * 公司id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long companyId;

    private String companyName;
    /**
     *  创建人ID 初始插入的时候创建后续不变用于追踪记录的操作人
     */
    private Long createdBy;
    /**
     *  更新人ID 后续的update更新此字典
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updatedBy;

    private String updatedMan;
}
