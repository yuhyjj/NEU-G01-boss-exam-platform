package com.boss.bes.paper.api;

import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.pojo.dto.PaperSubjectAnswerDTO;
import com.boss.bes.paper.pojo.vo.PaperSubjectAnswerVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaperSubjectAnswerApi {
    @ApiOperation("展示题目答案")
    CommonResponse<List<PaperSubjectAnswerVO>> getSubjectAnswer(@RequestBody CommonRequest<PaperSubjectAnswerDTO> paperSubjectAnswerDTOCommonRequest);
    @ApiOperation("添加答案")
    CommonResponse<String> insertAnswer(@RequestBody CommonRequest<PaperSubjectAnswerDTO> paperSubjectAnswerDTOCommonRequest);
    @ApiOperation("批量添加答案")
    CommonResponse<String> insertAnswerList(@RequestBody CommonRequest<List<PaperSubjectAnswerDTO>> paperSubjectAnswerDTOCommonRequest);
    @ApiOperation("批量删除答案")
    CommonResponse<String> deleteAnswerList(@RequestBody CommonRequest<List<PaperSubjectAnswerDTO>> paperSubjectAnswerDTOCommonRequest);
}
