package com.boss.bes.paper.controller;

import boss.bes.log.annotation.LogApi;
import boss.bes.log.exception.code.enums.BaseCodeEnum;
import boss.bes.log.exception.code.enums.PaperManageCodeEnum;
import boss.bes.log.util.BuildResponse;

import boss.brs.xtrain.dataconvention.controller.BaseController;
import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.api.PaperApi;
import com.boss.bes.paper.pojo.dto.CombExamConfigDTO;
import com.boss.bes.paper.pojo.dto.PaperDTO;
import com.boss.bes.paper.pojo.dto.PaperSubjectDTO;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.pojo.vo.*;
import com.boss.bes.paper.service.ConfigItemService;
import com.boss.bes.paper.service.GetUserNameService;
import com.boss.bes.paper.service.PaperService;
import com.boss.bes.paper.service.PaperSubjectService;
import com.boss.bes.paper.utils.BeanUtil;
import com.boss.bes.paper.utils.mapper.PaperMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * description:试卷controller
 * @author yhy
 * time 2020.07.10 11:10
 */
@RestController
@RequestMapping("/education/bes/v1/boss-bes-paper-center/paper")
public class PaperController extends BaseController<PaperDTO,Paper,PaperDTO, PaperMapper, PaperVO> implements PaperApi {
    @Autowired
    private PaperService paperService;
    @Autowired
    GetUserNameService userNameService;
    @Autowired
    ConfigItemService configItemService;
    @Autowired
    PaperSubjectService paperSubjectService;
    List<PaperSubjectVO> list = null;
    @Override
    protected PaperVO doObjectTransferVo(Object o) {
        return BeanUtil.copyData(o, PaperVO.class);
    }

    /**
     * 展示试卷列表
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/findList")
    @Override
    public CommonResponse<PageInfo<PaperVO>> showPapers(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        doBeforePagination(paperDTOCommonRequest.getBody().getPageNum(),paperDTOCommonRequest.getBody().getPageSize());
        PageInfo<PaperDTO> pageInfo = paperService.queryList(paperDTOCommonRequest.getBody(),false);
        return BuildResponse.build("200",BeanUtil.convertPageInfo(pageInfo,PaperVO.class));
    }

    /**
     * 展示试卷模板列表
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/findTemplateList")
    @Override
    public CommonResponse<PageInfo<PaperVO>> showTemplates(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        doBeforePagination(paperDTOCommonRequest.getBody().getPageNum(),paperDTOCommonRequest.getBody().getPageSize());
        PageInfo<PaperDTO> pageInfo = paperService.queryList(paperDTOCommonRequest.getBody(),true);
        return BuildResponse.build("200",BeanUtil.convertPageInfo(pageInfo,PaperVO.class));
    }

    /**
     * 删除试卷
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/deletePaper")
    @Override
    public CommonResponse<String> deletePaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        if(paperDTOCommonRequest.getBody().getStatus()==5){
            return BuildResponse.build(PaperManageCodeEnum.DATA_IS_BEING_USED.getCode(),PaperManageCodeEnum.DATA_IS_BEING_USED.getMessage());
        }
        Integer deleteResult = paperService.delete(paperDTOCommonRequest.getBody());
        if(deleteResult == 0){
            return BuildResponse.build(BaseCodeEnum.BASE_DATA_DELETE_EXCEPTION.getCode(),BaseCodeEnum.BASE_DATA_DELETE_EXCEPTION.getMessage());
        }
        return BuildResponse.build("200","删除成功");
    }

    /**
     * 批量删除试卷
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/deletePapers")
    @Override
    public CommonResponse<String> deletePapers(@RequestBody CommonRequest<List<PaperDTO>> paperDTOCommonRequest){
        for(PaperDTO paperDTO:paperDTOCommonRequest.getBody()){
            if(paperDTO.getStatus()==5){
                return BuildResponse.build(PaperManageCodeEnum.DATA_IS_BEING_USED.getCode(),PaperManageCodeEnum.DATA_IS_BEING_USED.getMessage());
            }
        }
        paperService.delete(paperDTOCommonRequest.getBody());
        return BuildResponse.build("200","批量删除成功");
    }

    /**
     * 上传试卷
     * @param paperDTOCommonRequest
     * @return
     */
    @PostMapping("/uploadPaper")
    @Override
    public CommonResponse<String> uploadPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        // 同名判断
        if(paperService.hasSameNamePaper(paperDTOCommonRequest.getBody())) {
            return BuildResponse.build(PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getCode(), PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getMessage());
        }
        int uploadResult = paperService.uploadPaper(paperDTOCommonRequest.getBody());
        if(uploadResult == 0){
            return BuildResponse.build(PaperManageCodeEnum.UPLOAD_PAPER_FAILED.getCode(),PaperManageCodeEnum.UPLOAD_PAPER_FAILED.getMessage());
        }
        return BuildResponse.build("200","上传成功");
    }

    /**
     * 下载模板
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/downloadPaper")
    @Override
    public CommonResponse<String> downloadPaper(CommonRequest<PaperDTO> paperDTOCommonRequest) {
        // 同名判断
        if(paperService.hasSameNamePaper(paperDTOCommonRequest.getBody())) {
            return BuildResponse.build(PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getCode(), PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getMessage());
        }
        if(paperService.downloadPaper(paperDTOCommonRequest.getBody()) == 0){
            return BuildResponse.build(PaperManageCodeEnum.DOWNLOAD_PAPER_FAILED.getCode(),PaperManageCodeEnum.DOWNLOAD_PAPER_FAILED.getMessage());
        }
        return BuildResponse.build("200","下载成功");
    }

    /**
     * 获取试卷信息
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/getPaper")
    @Override
    public CommonResponse<PaperVO> getPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        PaperDTO paperDTO = paperService.getPaperById(paperDTOCommonRequest.getBody());
        Long companyId = paperDTO.getCompanyId();
        Long updatedBy = paperDTO.getUpdatedBy();
        PaperVO paperVO = doObjectTransferVo(paperDTO);
        paperVO.setCompanyName(userNameService.getCompanyNameById(companyId));
        paperVO.setUpdatedMan(userNameService.getUserWithOrgIdAndCompanyIdAndName(updatedBy).getName());
        return BuildResponse.build("200",paperVO);
    }

    /**
     * 通过id查询试卷名称
     * @param id
     * @return
     */
    @PostMapping("/getPaperNameWithId")
    @Override
    public String getPaperNameWithId(@RequestParam(value = "id") Long id) {
        return paperService.getPaperNameById(id);
    }

    /**
     * 试卷信息
     * @return
     */
    @PostMapping("/getAll")
    @Override
    public List<PaperVO> getAll() {
        List<PaperDTO> papers = paperService.getAll();
        return BeanUtil.convertListToList(papers,PaperVO.class);
    }

    /**
     * 根据试卷id，发布次数+1 ，状态改成已发布
     * @param id
     * @return
     */
    @PostMapping("/updatePaperStateAndPublishTimes")
    @Override
    public String updatePaperStateAndPublishTimes(@RequestParam(value = "id")Long id) {
        int result = paperService.updatePaperStateAndPublishTimes(id);
        if(result==0){
            return "发布失败";
        }
        return "发布成功";
    }

    /**
     * 更新试卷信息
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/updatePaper")
    @Override
    public CommonResponse<String> updatePaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        if(paperDTOCommonRequest.getBody().getStatus()==5){
            return BuildResponse.build(PaperManageCodeEnum.DATA_IS_BEING_USED.getCode(),PaperManageCodeEnum.DATA_IS_BEING_USED.getMessage());
        }
        int updateResult = paperService.updatePaper(paperDTOCommonRequest.getBody());
        if(updateResult == 0){
            return BuildResponse.build(BaseCodeEnum.BASE_DATA_UPDATE_EXCEPTION.getCode(), BaseCodeEnum.BASE_DATA_UPDATE_EXCEPTION.getMessage());
        }
        return BuildResponse.build("200","更新成功");
    }

    /**
     * 模板组卷添加试卷
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/insertPaper")
    @Override
    public CommonResponse<String> insertPaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        // 同名判断
        if(paperService.hasSameNamePaper(paperDTOCommonRequest.getBody())) {
            return BuildResponse.build(PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getCode(), PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getMessage());
        }
        int insertResult = paperService.insertPaper(paperDTOCommonRequest.getBody());
        if (insertResult == 0) {
            return BuildResponse.build(PaperManageCodeEnum.BASE_TEMPLATE_CREATE_PAPER_FAILD.getCode(), PaperManageCodeEnum.BASE_TEMPLATE_CREATE_PAPER_FAILD.getMessage());
        }
        return BuildResponse.build("200", "模板组卷成功");
    }

    /**
     * 快速组卷之获得题目与答案
     * @param commonRequest
     * @return
     */
    @PostMapping("/paperCombPaper")
    @Override
    public CommonResponse<List<PaperSubjectVO>> paperCombPaper(@RequestBody CommonRequest<Long> commonRequest){
        // feign获得List<PSubjectVO>
        Long configId=commonRequest.getBody();
        List<PSubjectVO> subjectVOS = configItemService.paperCombPaper(configId);
        list = new ArrayList<>();
        // 转换PSubjectVO———>PaperSubjectVO（与基础数据字段没对上，迫不得已）
        for(PSubjectVO subjectVO:subjectVOS){
            list.add(copyDataVO(subjectVO));
        }
        return BuildResponse.build("200",list);
    }

    /**
     * 工具类（字段没对上，迫不得已） PSubjectVO———PaperSubjectVO
     * @param subjectVO
     * @return
     */
    private PaperSubjectVO copyDataVO(PSubjectVO subjectVO){
        PaperSubjectVO paperSubjectVO = new PaperSubjectVO();
        paperSubjectVO.setId(subjectVO.getId());
        paperSubjectVO.setPaperId(paperService.getPaperId());
        paperSubjectVO.setSubject(subjectVO.getName());
        paperSubjectVO.setSubjectCategory(subjectVO.getCategoryName());
        paperSubjectVO.setQuestionType(subjectVO.getSubjectTypeId());
        paperSubjectVO.setDifficult(subjectVO.getDictionaryId());
        paperSubjectVO.setScore(subjectVO.getScore());
        paperSubjectVO.setVersion(subjectVO.getVersion());

        // 答案列表
        List<SubjectAnswerVO> subjectAnswerDTOList = subjectVO.getSubjectAnswerDTOList();
        // 待转换的答案列表
        List<PaperSubjectAnswerVO> paperSubjectAnswerVOS = new ArrayList<>();
        PaperSubjectAnswerVO paperSubjectAnswerVO;
        for(SubjectAnswerVO vo:subjectAnswerDTOList){
            paperSubjectAnswerVO = new PaperSubjectAnswerVO();
            paperSubjectAnswerVO.setId(vo.getId());
            paperSubjectAnswerVO.setAnswer(vo.getAnswer());
            if(vo.getRightAnswer() == 0){
                paperSubjectAnswerVO.setRightAnswer(false);
            }else {
                paperSubjectAnswerVO.setRightAnswer(true);
            }
            paperSubjectAnswerVOS.add(paperSubjectAnswerVO);
        }

        paperSubjectVO.setAnswers(BeanUtil.convertListToList(paperSubjectAnswerVOS, PaperSubjectAnswer.class));
        return paperSubjectVO;
    }

    /**
     * 组卷之添加试卷信息（包括题目及答案）到数据库
     * @param paperDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/quickCreatePaper")
    @Override
    public CommonResponse<String> quickCreatePaper(@RequestBody CommonRequest<PaperDTO> paperDTOCommonRequest){
        // 同名判断，同名调用异常方法
        if(paperService.hasSameNamePaper(paperDTOCommonRequest.getBody())) {
            return BuildResponse.build(PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getCode(), PaperManageCodeEnum.SAME_NAME_PAPER_EXIST.getMessage());
        }
        // 添加试卷
        int insertResult = paperService.quickInsertPaper(paperDTOCommonRequest.getBody());
        // 为获取到的试题（含答案）list赋值上方试卷id
        for(PaperSubjectVO paperSubjectVO:list){
            paperSubjectVO.setPaperId(paperService.getPaperId());
        }
        // 插入试题（含答案）list到数据库
        paperSubjectService.insertSubjectList(BeanUtil.convertListToList(list, PaperSubjectDTO.class));
        if (insertResult == 0) {
            // 组卷失败调用异常方法
            return BuildResponse.build(PaperManageCodeEnum.QUICK_CREATE_PAPER_FAILED.getCode(), PaperManageCodeEnum.QUICK_CREATE_PAPER_FAILED.getMessage());
        }
        return BuildResponse.build("200", "快速组卷成功");
    }

    /**
     * 标准组卷之获得题目与答案
     * @param combExamConfigDTO
     * @return
     */
    @LogApi
    @PostMapping("/paperStdCombPaper")
    @Override
    public CommonResponse<List<PaperSubjectVO>> paperStdCombPaper(@RequestBody CommonRequest<CombExamConfigDTO> combExamConfigDTO){
        List<PSubjectVO> subjectVOS = configItemService.paperStdCombPaper(combExamConfigDTO.getBody());
        list = new ArrayList<>();
        for(PSubjectVO subjectVO:subjectVOS){
            list.add(copyDataVO(subjectVO));
        }
        return BuildResponse.build("200",list);
    }
}
