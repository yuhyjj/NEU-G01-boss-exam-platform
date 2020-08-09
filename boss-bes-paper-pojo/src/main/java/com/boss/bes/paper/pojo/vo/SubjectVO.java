package com.boss.bes.paper.pojo.vo;

import boss.brs.xtrain.dataconvention.pojo.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SubjectVO extends BaseVO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long status;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long version;
    private String name;
    private String imageUrl;
    private String remark;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long orgId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long companyId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long subjectTypeId;
    private String subjectTypeName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    private String categoryName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long dictionaryId;
    private String dictionaryName;
    private List<SubjectAnswerVO> subjectAnswerDTOList;
}
