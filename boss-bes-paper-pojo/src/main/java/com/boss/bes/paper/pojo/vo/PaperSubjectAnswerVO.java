package com.boss.bes.paper.pojo.vo;


import boss.brs.xtrain.dataconvention.pojo.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaperSubjectAnswerVO extends BaseVO {
    /**
     * 试卷试题答案id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 试题id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long subjectId;
    /**
     * 答案
     */
    private String answer;

    /**
     * 正确
     */
    private Boolean rightAnswer;

    /**
     * 图片URL
     */
    private String pictureUrl;
    /**
     * 版本
     */
    private Long version;

}
