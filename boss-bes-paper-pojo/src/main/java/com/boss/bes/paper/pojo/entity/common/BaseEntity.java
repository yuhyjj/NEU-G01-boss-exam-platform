/**
 * @file:  BaseEntity.java
 * @author: Administrator    
 * @date:   2020-6-19 11:22
 */  
package com.boss.bes.paper.pojo.entity.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *  BaseEntity
 * 系统表包含的公用字段进行提取，统一切面也将使用该类型
 *
 * @author yhy
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    /**
     *  主键
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Id
    private Long id;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 记录所属公司ID
     */
    private Long companyId;
    /**
     * 组织机构ID ，一个组织机构包含多个公司
     */
    private Long organizationId;
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
     *  当前行的版初始为0 每次数据变动则加1
     */
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        BaseEntity baseEntity = (BaseEntity) o;
        return Objects.equals(id, baseEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
