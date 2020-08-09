package com.boss.bes.paper.controller;

import boss.bes.log.annotation.LogApi;
import boss.bes.log.exception.code.enums.BaseCodeEnum;
import boss.bes.log.util.BuildResponse;
import boss.brs.xtrain.dataconvention.controller.BaseCRUDController;
import boss.brs.xtrain.dataconvention.convention.CommonRequest;
import boss.brs.xtrain.dataconvention.convention.CommonResponse;
import com.boss.bes.paper.api.PaperSubjectAnswerApi;
import com.boss.bes.paper.pojo.dto.PaperSubjectAnswerDTO;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.pojo.vo.PaperSubjectAnswerVO;
import com.boss.bes.paper.service.PaperSubjectAnswerService;
import com.boss.bes.paper.utils.BeanUtil;
import com.boss.bes.paper.utils.mapper.PaperSubjectAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/education/bes/v1/boss-bes-paper-center/PaperSubjectAnswer")
public class PaperSubjectAnswerController extends BaseCRUDController<PaperSubjectAnswerDTO, PaperSubjectAnswer, PaperSubjectAnswerDTO, PaperSubjectAnswerMapper, PaperSubjectAnswerVO> implements PaperSubjectAnswerApi {
    @Autowired
    PaperSubjectAnswerService paperSubjectAnswerService;

    @Override
    protected PaperSubjectAnswerVO doObjectTransf(Object o) {
        return BeanUtil.copyData(o, PaperSubjectAnswerVO.class);
    }

    /**
     * 展示题目答案
     * @param paperSubjectAnswerDTOCommonRequest
     * @return
     */
    @PostMapping("/showSubjectAnswer")
    @Override
    public CommonResponse<List<PaperSubjectAnswerVO>> getSubjectAnswer(@RequestBody CommonRequest<PaperSubjectAnswerDTO> paperSubjectAnswerDTOCommonRequest){
        List<PaperSubjectAnswerDTO> paperSubjectAnswerDTOS = paperSubjectAnswerService.query(paperSubjectAnswerDTOCommonRequest.getBody());
        return BuildResponse.build("200",BeanUtil.convertListToList(paperSubjectAnswerDTOS,PaperSubjectAnswerVO.class));
    }

    /**
     * 添加答案
     * @param paperSubjectAnswerDTOCommonRequest
     * @return
     */
    @PostMapping("/insertAnswer")
    @Override
    public CommonResponse<String> insertAnswer(@RequestBody CommonRequest<PaperSubjectAnswerDTO> paperSubjectAnswerDTOCommonRequest){
        int result = paperSubjectAnswerService.insertAnswer(paperSubjectAnswerDTOCommonRequest.getBody());
        if(result == 0){
            return BuildResponse.build(BaseCodeEnum.BASE_DATA_INSERT_EXCEPTION.getCode(), BaseCodeEnum.BASE_DATA_INSERT_EXCEPTION.getMessage());
        }
        return BuildResponse.build("200","答案添加成功！");
    }

    /**
     * 批量添加答案
     * @param paperSubjectAnswerDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/insertAnswerList")
    @Override
    public CommonResponse<String> insertAnswerList(@RequestBody CommonRequest<List<PaperSubjectAnswerDTO>> paperSubjectAnswerDTOCommonRequest){
        paperSubjectAnswerService.insertAnswerList(paperSubjectAnswerDTOCommonRequest.getBody());
        return BuildResponse.build("200","答案批量添加成功！");
    }

    /**
     * 批量删除答案
     * @param paperSubjectAnswerDTOCommonRequest
     * @return
     */
    @LogApi
    @PostMapping("/deleteAnswerList")
    @Override
    public CommonResponse<String> deleteAnswerList(@RequestBody CommonRequest<List<PaperSubjectAnswerDTO>> paperSubjectAnswerDTOCommonRequest){
        paperSubjectAnswerService.deleteAnswerList(paperSubjectAnswerDTOCommonRequest.getBody());
        return BuildResponse.build("200","批量删除成功！");
    }
}
