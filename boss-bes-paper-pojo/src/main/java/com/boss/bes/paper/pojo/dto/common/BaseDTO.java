package com.boss.bes.paper.pojo.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *  BaseDTO
 * 考虑DTO从这里继承便于统一DTO接口和转型判断
 *
 * @author Administrator
 * date 2020-07-01 20:39
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO implements Serializable {
    /**
     *  主键
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Id
    private Long id;

    private Integer status;

    /**
     * 组织机构id
     */
    private Long organizationId;

    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     *  创建人ID 初始插入的时候创建后续不变用于追踪记录的操作人
     */
    private Long createdBy;
    /**
     *  更新时间记录便于追踪
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     *  更新人ID 后续的update更新此字典
     */
    private Long updatedBy;
    /**
     *  当前行的版初始为1 每次数据变动则加1
     */
    private Long version;

}
