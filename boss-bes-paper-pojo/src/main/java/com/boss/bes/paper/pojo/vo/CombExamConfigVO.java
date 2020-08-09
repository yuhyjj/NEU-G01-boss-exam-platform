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
public class CombExamConfigVO extends BaseVO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String name;
    private String remark;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long companyId;
    private String companyName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long version;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long status;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long totalDifficultId;
    private String totalDifficultName;
    private List<CombExamConfigItemVO> combExamConfigItemDTOList;
    private Long updatedBy;
    private String updateMan;
}
