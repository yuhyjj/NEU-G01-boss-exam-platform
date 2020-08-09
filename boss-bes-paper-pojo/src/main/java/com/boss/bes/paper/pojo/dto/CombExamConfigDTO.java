package com.boss.bes.paper.pojo.dto;

import boss.brs.xtrain.dataconvention.pojo.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombExamConfigDTO extends BaseDTO {
    private String name;
    private String remark;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long totalDifficultId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long orgId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long companyId;

    private List<CombExamConfigItemDTO> combExamConfigItemDTOList;
}

