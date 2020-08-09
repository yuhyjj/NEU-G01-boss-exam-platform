package com.boss.bes.paper.service;


import com.boss.bes.paper.pojo.dto.UserForExamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * description: feignclient接口,从系统管理模块获取用户姓名、公司名称
 * @author yuhaiyuan
 * time:2020/7/18-10:04
 */
@FeignClient(value = "boss-system-manage-center")
public interface GetUserNameService {
    @GetMapping("/education/bes/v1/system-manager-center/user/getUserWithOrgIdAndCompanyIdAndName")
    UserForExamDTO getUserWithOrgIdAndCompanyIdAndName(@RequestParam("id") Long id);
    @GetMapping("/education/bes/v1/system-manager-center/company/getCompanyNameById")
    String getCompanyNameById(@RequestParam("id") Long id);

}
