package com.boss.bes.paper.service;


import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.pojo.dto.CombExamConfigDTO;
import com.boss.bes.paper.pojo.entity.CombExamConfig;
import com.boss.bes.paper.pojo.query.BasePageQuery;
import com.boss.bes.paper.pojo.vo.*;
import com.boss.bes.paper.utils.CommonPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * description: feignclient接口，从基础模块调用配置项
 * @author yuhaiyuan
 * time:2020/7/18-10:04
 */
@FeignClient(value = "basedata")
public interface ConfigItemService {
    /**
     * 展示组卷配置列表
     * @param basePageQuery
     * @return
     */
    @PostMapping("/education/bes/v1/base-data-center/combexamconfig/paperQueryCombExamConfig")
    CommonPage<CombExamConfig, CombExamConfigVO> paperQueryCombExamConfig(@RequestBody BasePageQuery basePageQuery);

    /**
     * 添加配置项
     * @param request
     * @return
     */
    @PostMapping("/education/bes/v1/base-data-center/combexamconfig/paperAddCombExamConfig")
    CommonResponse<String> paperAddCombExamConfig(@RequestBody CombExamConfigDTO request);

    /**
     * 修改组卷配置(包括组卷配置明细)
     * @param request
     * @return
     */
    @PostMapping("/education/bes/v1/base-data-center/combexamconfig/paperUpdateCombExamConfig")
    CommonResponse<String> paperUpdateCombExamConfig(@RequestBody CombExamConfigDTO request);

    /**
     * 根据配置项id返回配置项明细列表
     * @param configId
     * @return
     */
    @GetMapping("/education/bes/v1/base-data-center/combexamconfig/paperQueryCombExamConfigItemListByConfigId")
    List<CombExamConfigItemVO> paperQueryCombExamConfigItemListByConfigId(@RequestParam(value = "configId") Long configId);

    /**
     *获得题目类别列表
     * @return
     */
    @PostMapping("/education/bes/v1/base-data-center/category/paperQueryCategoryList")
    List<CategoryVO> paperQueryCategoryList();

    /**
     *获取题目类型列表
     * @return
     */
    @PostMapping("/education/bes/v1/base-data-center/subjecttype/paperGetSubjectTypeList")
    List<SubjectTypeVO> paperGetSubjectTypeList();

    /**
     * 快速组卷
     * @param configId
     * @return
     */
    @GetMapping("/education/bes/v1/base-data-center/combexamconfig/paperCombPaper")
    List<PSubjectVO> paperCombPaper(@RequestParam("configId") Long configId);

    /**
     * 标准组卷
     * @param combExamConfigDTO
     * @return
     */
    @GetMapping("/education/bes/v1/base-data-center/combexamconfig/paperStdCombPaper")
    List<PSubjectVO> paperStdCombPaper(@RequestBody CombExamConfigDTO combExamConfigDTO);
}
