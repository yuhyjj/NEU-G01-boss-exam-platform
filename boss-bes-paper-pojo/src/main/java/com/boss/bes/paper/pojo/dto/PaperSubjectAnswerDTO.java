package com.boss.bes.paper.pojo.dto;


import com.boss.bes.paper.pojo.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PaperSubjectAnswerDTO extends BaseDTO {
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
}
