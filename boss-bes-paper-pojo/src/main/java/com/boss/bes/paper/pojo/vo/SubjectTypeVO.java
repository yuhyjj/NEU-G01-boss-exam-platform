package com.boss.bes.paper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectTypeVO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String subjectName;
    private String attribute;
    private Long orgId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    private Long version;
    private String remark;
}

