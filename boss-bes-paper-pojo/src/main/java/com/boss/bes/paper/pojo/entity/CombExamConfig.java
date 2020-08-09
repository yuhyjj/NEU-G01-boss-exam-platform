package com.boss.bes.paper.pojo.entity;

import boss.brs.xtrain.dataconvention.pojo.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_comb_exam_config")
public class CombExamConfig extends BaseEntity {

    /**
     * 配置名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组织机构ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 版本
     */
    private Long version;

    /**
     * 标记位
     */
    private Long status;


    private Long totalDifficultId;


    /**
     * 获取配置名
     *
     * @return name - 配置名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置配置名
     *
     * @param name 配置名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取组织机构ID
     *
     * @return org_id - 组织机构ID
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 设置组织机构ID
     *
     * @param orgId 组织机构ID
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取公司ID
     *
     * @return company_id - 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取标记位
     *
     * @return status - 标记位
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 设置标记位
     *
     * @param status 标记位
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTotalDifficultId() {
        return totalDifficultId;
    }

    public void setTotalDifficultId(Long totalDifficultId) {
        this.totalDifficultId = totalDifficultId;
    }
}

