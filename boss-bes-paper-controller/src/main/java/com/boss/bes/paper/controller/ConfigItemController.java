package com.boss.bes.paper.controller;

import boss.bes.log.annotation.LogApi;
import boss.bes.log.util.BuildResponse;
import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.pojo.dto.CombExamConfigDTO;
import com.boss.bes.paper.pojo.entity.CombExamConfig;
import com.boss.bes.paper.pojo.query.BasePageQuery;
import com.boss.bes.paper.pojo.vo.*;
import com.boss.bes.paper.service.ConfigItemService;
import com.boss.bes.paper.service.GetUserNameService;
import com.boss.bes.paper.utils.CommonPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
/**
 * description:配置项controller
 * @author yhy
 * time:2020-07-21 17:55
 */
@RestController
@RequestMapping("/education/bes/v1/boss-bes-paper-center/configItem")
public class ConfigItemController {
    @Autowired
    ConfigItemService configItemService;
    @Autowired
    GetUserNameService userNameService;
    /**
     * 展示组卷配置列表
     * @param basePageQuery
     * @return
     */
    @PostMapping("/paperQueryCombExamConfig")
    public CommonResponse<CommonPage<CombExamConfig, CombExamConfigVO>> paperQueryCombExamConfig(@RequestBody CommonRequest<BasePageQuery> basePageQuery){
        CommonPage<CombExamConfig, CombExamConfigVO> pages = configItemService.paperQueryCombExamConfig(basePageQuery.getBody());
        List<CombExamConfigVO> configs = pages.getList();
        for(CombExamConfigVO configVO:configs){
            configVO.setCompanyName(userNameService.getCompanyNameById(configVO.getCompanyId()));
            configVO.setUpdateMan(userNameService.getUserWithOrgIdAndCompanyIdAndName(configVO.getUpdatedBy()).getName());
        }
        return BuildResponse.build("200",pages);
    }

    /**
     * 添加配置项
     * @param request
     * @return
     */
    @PostMapping("/paperAddCombExamConfig")
    public CommonResponse<String> paperAddCombExamConfig(@RequestBody CommonRequest<CombExamConfigDTO> request){
        return configItemService.paperAddCombExamConfig(request.getBody());
    }

    /**
     * 根据配置项id返回配置项明细列表
     * @param configId
     * @return
     */
    @PostMapping("/paperQueryCombExamConfigItemListByConfigId")
    public CommonResponse<List<CombExamConfigItemVO>> paperQueryCombExamConfigItemListByConfigId(@RequestBody CommonRequest<Long> configId){
        return BuildResponse.build("200",configItemService.paperQueryCombExamConfigItemListByConfigId(configId.getBody()));
    }

    /**
     * 修改组卷配置(包括组卷配置明细)
     * @param request
     * @return
     */
    @LogApi
    @PostMapping("/paperUpdateCombExamConfig")
    public CommonResponse<String> paperUpdateCombExamConfig(@RequestBody CommonRequest<CombExamConfigDTO> request){
        request.getBody().setUpdatedTime(new Date());
        return configItemService.paperUpdateCombExamConfig(request.getBody());
    }
    /**
     *获得题目类别列表
     * @return
     */
    @PostMapping("/paperQueryCategoryList")
    public CommonResponse<List<CategoryVO>> paperQueryCategoryList(){
        return BuildResponse.build("200",configItemService.paperQueryCategoryList());
    }
    /**
     *获取题目类型列表
     * @return
     */
    @PostMapping("/paperGetSubjectTypeList")
    public CommonResponse<List<SubjectTypeVO>> paperGetSubjectTypeList(){
        return BuildResponse.build("200",configItemService.paperGetSubjectTypeList());
    }

}
