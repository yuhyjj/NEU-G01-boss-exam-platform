package com.boss.bes.paper.controller;


import boss.bes.log.annotation.LogApi;
import boss.bes.log.exception.code.enums.BaseCodeEnum;
import boss.bes.log.util.BuildResponse;
import boss.brs.xtrain.dataconvention.controller.BaseCRUDController;
import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.api.PaperSubjectApi;
import com.boss.bes.paper.pojo.dto.PaperSubjectDTO;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.vo.PaperSubjectVO;
import com.boss.bes.paper.service.PaperSubjectService;
import com.boss.bes.paper.utils.BeanUtil;
import com.boss.bes.paper.utils.mapper.PaperSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description:试卷试题controller
 * @author yhy
 * Time:2020-7-11 11:28
 */
@RestController
@RequestMapping("/education/bes/v1/boss-bes-paper-center/paperSubject")
public class PaperSubjectController extends BaseCRUDController<PaperSubjectDTO, PaperSubject, PaperSubjectDTO, PaperSubjectMapper, PaperSubjectVO> implements PaperSubjectApi {
    @Autowired
    PaperSubjectService paperSubjectService;

    @Override
    protected PaperSubjectVO doObjectTransf(Object o) {
        return BeanUtil.copyData(o, PaperSubjectVO.class);
    }

    /**
     * 展示试卷试题
     * @param paperSubjectDTOCommonRequest
     * @return
     */
    @PostMapping("/showPaperSubjects")
    @Override
    public CommonResponse<List<PaperSubjectVO>> showPaperSubjects(@RequestBody CommonRequest<PaperSubjectDTO> paperSubjectDTOCommonRequest) {
        List<PaperSubjectDTO> subjectDTOS = paperSubjectService.showPaperSubjects(paperSubjectDTOCommonRequest.getBody());
        return BuildResponse.build("200",BeanUtil.convertListToList(subjectDTOS, PaperSubjectVO.class));
    }

    /**
     * 通过id查询试题
     * @param id
     * @return
     */
    @PostMapping("/getSubjectById")
    @Override
    public PaperSubjectVO getSubjectById(@RequestParam(value = "id")Long id) {
        PaperSubjectDTO paperSubjectDTO = paperSubjectService.getSubjectById(id);
        return BeanUtil.copyData(paperSubjectDTO, PaperSubjectVO.class);
    }

    /**
     * 通过id查询试题及其答案
     * @param id
     * @return
     */
    @PostMapping("/getSubjectAndAnswerById")
    @Override
    public CommonResponse<PaperSubjectVO> getSubjectAndAnswerById(@RequestBody CommonRequest<Long> id) {
        PaperSubjectDTO paperSubjectDTO = paperSubjectService.getSubjectAndAnswerById(id.getBody());
        return BuildResponse.build("200",BeanUtil.copyData(paperSubjectDTO, PaperSubjectVO.class));
    }
    /**
     * 展示试卷试题及其答案
     * @param paperSubjectDTOCommonRequest
     * @return
     */
    @PostMapping("/showSubjectWithAnswer")
    @Override
    public CommonResponse<List<PaperSubjectVO>> showSubjectWithAnswer(@RequestBody CommonRequest<PaperSubjectDTO> paperSubjectDTOCommonRequest) {
        List<PaperSubjectDTO> subjectDTOS = paperSubjectService.showSubjectsWithAnswers(paperSubjectDTOCommonRequest.getBody());
        return BuildResponse.build("200",BeanUtil.convertListToList(subjectDTOS, PaperSubjectVO.class));
    }

    /**
     * 添加试题
     * @param paperSubjectDTOCommonRequest
     * @return
     */
    @PostMapping("/insertSubject")
    @Override
    public CommonResponse<String> insertSubject(@RequestBody CommonRequest<PaperSubjectDTO> paperSubjectDTOCommonRequest){
        int result = paperSubjectService.insertSubject(paperSubjectDTOCommonRequest.getBody());
        if(result == 0){
            return BuildResponse.build(BaseCodeEnum.BASE_DATA_INSERT_EXCEPTION.getCode(), BaseCodeEnum.BASE_DATA_INSERT_EXCEPTION.getMessage());
        }
        return BuildResponse.build("200","试题添加成功！");
    }

    /**
     * 批量删除试题
     * @param paperSubjectDTOCommonRequest
     * @return
     */
    @PostMapping("/deleteSubjectList")
    @Override
    public CommonResponse<String> deleteSubjectList(@RequestBody CommonRequest<List<PaperSubjectDTO>> paperSubjectDTOCommonRequest){
        paperSubjectService.deleteSubjectList(paperSubjectDTOCommonRequest.getBody());
        return BuildResponse.build("200","试题批量删除成功！");
    }

    /**
     *  批量添加试卷及其答案，更新试卷总分
     * @param paperSubjectDTOCommonRequest
     * @return
     */
    @PostMapping("/insertSubjectList")
    @Override
    public CommonResponse<String> insertSubjectList(@RequestBody CommonRequest<List<PaperSubjectDTO>> paperSubjectDTOCommonRequest){
        paperSubjectService.insertSubjectList(paperSubjectDTOCommonRequest.getBody());
        return BuildResponse.build("200","批量添加成功！");
    }

    /**
     * 根据题型获得题目id
     * @param questionType
     * @return
     */
    @LogApi
    @PostMapping("/getSubjectIdsWithSubjectType")
    @Override
    public List<Long> getSubjectIdsWithSubjectType(@RequestParam(value = "questionType")Long questionType) {
        return paperSubjectService.getPaperIdsWithSubjectType(questionType);
    }

    /**
     * 使用试卷ID获得试卷试题及其答案
     * @param id
     * @return
     */
    @PostMapping("/showSubjectWithAnswerById")
    @Override
    public List<PaperSubjectVO> showSubjectWithAnswerById(@RequestParam(value = "id")Long id) {
        List<PaperSubjectDTO> subjectDTOS = paperSubjectService.showSubjectsWithAnswersById(id);
        return BeanUtil.convertListToList(subjectDTOS, PaperSubjectVO.class);
    }
}
