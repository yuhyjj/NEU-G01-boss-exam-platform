package com.boss.bes.paper.api;


import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.pojo.dto.PaperSubjectDTO;
import com.boss.bes.paper.pojo.vo.PaperSubjectVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PaperSubjectApi {
    @ApiOperation("展示试卷试题")
    CommonResponse<List<PaperSubjectVO>> showPaperSubjects(@RequestBody CommonRequest<PaperSubjectDTO> paperSubjectDTOCommonRequest);
    @ApiOperation("展示试卷试题及其答案")
    CommonResponse<List<PaperSubjectVO>> showSubjectWithAnswer(@RequestBody CommonRequest<PaperSubjectDTO> paperSubjectDTOCommonRequest);
    @ApiOperation("添加试题")
    CommonResponse<String> insertSubject(@RequestBody CommonRequest<PaperSubjectDTO> paperSubjectDTOCommonRequest);
    @ApiOperation("批量删除试题")
    CommonResponse<String> deleteSubjectList(@RequestBody CommonRequest<List<PaperSubjectDTO>> paperSubjectDTOCommonRequest);
    @ApiOperation("批量添加试卷及其答案，更新试卷总分")
    CommonResponse<String> insertSubjectList(@RequestBody CommonRequest<List<PaperSubjectDTO>> paperSubjectDTOCommonRequest);
    @ApiOperation("根据id查询试题及其答案")
    CommonResponse<PaperSubjectVO> getSubjectAndAnswerById(@RequestBody CommonRequest<Long> id);

    @ApiOperation("通过id查询试题")
    PaperSubjectVO getSubjectById(@RequestParam(value = "id")Long id);
    @ApiOperation("根据题型获得题目ID")
    List<Long> getSubjectIdsWithSubjectType(@RequestParam(value = "questionType")Long questionType);
    @ApiOperation("使用试卷ID获得试卷试题及其答案")
    List<PaperSubjectVO> showSubjectWithAnswerById(@RequestParam(value = "id")Long id);
}
