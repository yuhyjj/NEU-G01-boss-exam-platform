package com.boss.bes.paper.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:为考试系统提供的用户DTO
 * @author:zyz
 * time:2020/7/14-15:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserForExamDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long companyId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long organizationId;
    /**
     * 姓名
     */
    private String name;


}
