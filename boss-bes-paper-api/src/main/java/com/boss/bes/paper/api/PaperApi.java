package com.boss.bes.paper.api;


import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.pojo.dto.CombExamConfigDTO;
import com.boss.bes.paper.pojo.dto.PaperDTO;
import com.boss.bes.paper.pojo.vo.PaperSubjectVO;
import com.boss.bes.paper.pojo.vo.PaperVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * description: 提供试卷的Api
 * @author:yhy
 * time:2020/7/6-18:07
 */
public interface PaperApi {
    @ApiOperation("展示试卷列表")
    CommonResponse<PageInfo<PaperVO>> showPapers(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("展示试卷模板列表")
    CommonResponse<PageInfo<PaperVO>> showTemplates(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("删除试卷")
    CommonResponse<String> deletePaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("批量删除试卷")
    CommonResponse<String> deletePapers(@RequestBody CommonRequest<List<PaperDTO>> paperDTOCommonRequest);
    @ApiOperation("上传试卷")
    CommonResponse<String> uploadPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("下载模板")
    CommonResponse<String> downloadPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("更新试卷信息")
    CommonResponse<String> updatePaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("根据id获取试卷信息")
    CommonResponse<PaperVO> getPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("模板组卷添加试卷")
    CommonResponse<String> insertPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("快速组卷之获得题目与答案")
    CommonResponse<List<PaperSubjectVO>> paperCombPaper(@RequestBody CommonRequest<Long> commonRequest);
    @ApiOperation("组卷之添加试卷信息（包括题目及答案）到数据库")
    CommonResponse<String> quickCreatePaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest);
    @ApiOperation("标准组卷之获得题目与答案")
    CommonResponse<List<PaperSubjectVO>> paperStdCombPaper(@RequestBody CommonRequest<CombExamConfigDTO> combExamConfigDTO);

    @ApiOperation("输入试卷ID获得试卷名称")
    String getPaperNameWithId(@RequestParam(value = "id")Long id);
    @ApiOperation("获得全部试卷")
    List<PaperVO> getAll();
    @ApiOperation("根据试卷id，发布次数+1 ，状态改成已发布")
    String updatePaperStateAndPublishTimes(@RequestParam(value = "id")Long id);
}
