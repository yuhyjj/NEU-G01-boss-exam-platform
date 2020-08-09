package com.boss.bes.paper.service;

import boss.bes.log.exception.code.enums.PaperManageCodeEnum;
import boss.bes.log.exception.type.BusinessException;
import boss.brs.xtrain.dataconvention.service.BaseCURDService;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.boss.bes.paper.dao.PaperSubjectDao;
import com.boss.bes.paper.pojo.dto.PaperDTO;
import com.boss.bes.paper.pojo.dto.PaperSubjectAnswerDTO;
import com.boss.bes.paper.pojo.dto.PaperSubjectDTO;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.utils.BeanUtil;
import com.boss.bes.paper.utils.mapper.PaperSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description:试卷试题service
 * @author yhy
 * time:2020-07-06 17:55
 */
@Service
public class PaperSubjectService extends BaseCURDService<PaperSubjectDTO, PaperSubject, PaperSubjectDTO, PaperSubjectMapper> {
    @Autowired
    PaperSubjectDao<PaperSubject> paperSubjectDao;
    @Autowired
    PaperSubjectAnswerService paperSubjectAnswerService;
    @Autowired
    PaperService paperService;
    Snowflake snowflake;
    // 试卷总分
    private BigDecimal paperScore = null;

    public BigDecimal getPaperScore() {
        return paperScore;
    }

    /**
     * 展示试卷中的题目
     *
     * @param paperSubjectDTO
     * @return
     */
    public List<PaperSubjectDTO> showPaperSubjects(PaperSubjectDTO paperSubjectDTO) {
        Example example = new Example(PaperSubject.class);
        example.createCriteria().andEqualTo("paperId", paperSubjectDTO.getPaperId());
        List<PaperSubject> subjects = paperSubjectDao.getPaperSubject(example);
        return BeanUtil.convertListToList(subjects, PaperSubjectDTO.class);
    }

    /**
     * 根据id查询试题
     *
     * @param id
     * @return
     */
    public PaperSubjectDTO getSubjectById(Long id) {
        PaperSubject paperSubject = paperSubjectDao.getSubjectById(id);
        return BeanUtil.copyData(paperSubject, PaperSubjectDTO.class);
    }
    /**
     * 根据id查询试题及其答案
     *
     * @param id
     * @return
     */
    public PaperSubjectDTO getSubjectAndAnswerById(Long id){
        PaperSubject paperSubject = paperSubjectDao.getSubjectById(id);
        PaperSubjectAnswerDTO paperSubjectAnswerDTO = new PaperSubjectAnswerDTO();
        paperSubjectAnswerDTO.setSubjectId(id);
        List<PaperSubjectAnswerDTO> paperSubjectAnswerDTOList = paperSubjectAnswerService.query(paperSubjectAnswerDTO);
        PaperSubjectDTO paperSubjectDTO = BeanUtil.copyData(paperSubject, PaperSubjectDTO.class);
        paperSubjectDTO.setAnswers(BeanUtil.convertListToList(paperSubjectAnswerDTOList,PaperSubjectAnswer.class));
        return paperSubjectDTO;
    }
    @Override
    public List<?> query(PaperSubjectDTO paperSubjectDTO) {
        return null;
    }

    @Override
    protected PaperSubject doObjectTransf(PaperSubjectDTO paperSubjectDTO) {
        return null;
    }

    /**
     * 查询试卷中的试题，以及对应的答案
     * @param paperSubjectDTO
     * @return
     */
    public List<PaperSubjectDTO> showSubjectsWithAnswers(PaperSubjectDTO paperSubjectDTO) {
        Example example = new Example(PaperSubject.class);
        // 按照题型id进行排序，即可实现按照题型分类
        example.orderBy("questionType");
        example.createCriteria().andEqualTo("paperId", paperSubjectDTO.getPaperId());
        // 试卷包含的题目们，用来获取题目id
        List<PaperSubject> subjects = paperSubjectDao.getPaperSubject(example);
        List<PaperSubject> lists = new ArrayList<>();
        //
        for (PaperSubject subject : subjects) {
            // 根据得到的试题id添加试题信息及答案
            lists.add(paperSubjectDao.queryPaperWithAnswer(subject.getId()));
        }
        return BeanUtil.convertListToList(lists, PaperSubjectDTO.class);
    }
    /**
     * 使用试卷ID获得试卷试题及其答案
     * @param id
     * @return
     */
    public List<PaperSubjectDTO> showSubjectsWithAnswersById(Long id) {
        Example example = new Example(PaperSubject.class);
        example.createCriteria().andEqualTo("paperId", id);
        List<PaperSubject> subjects = paperSubjectDao.getPaperSubject(example);
        List<PaperSubject> lists = new ArrayList<>();
        for (PaperSubject subject : subjects) {
            lists.add(paperSubjectDao.queryPaperWithAnswer(subject.getId()));
        }
        return BeanUtil.convertListToList(lists, PaperSubjectDTO.class);
    }
    /**
     * 新增试题(未使用）
     * @param paperSubjectDTO
     * @return
     */
    public Integer insertSubject(PaperSubjectDTO paperSubjectDTO){
        snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        paperSubjectDTO.setId(id);
        Date date = new Date();
        paperSubjectDTO.setCreatedTime(date);
        paperSubjectDTO.setUpdatedTime(date);
        paperSubjectDTO.setVersion(1L);
        return paperSubjectDao.insertSubject(BeanUtil.copyData(paperSubjectDTO,PaperSubject.class));
    }

    /**
     * 批量删除试题
     * @param paperSubjectDTOS
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteSubjectList(List<PaperSubjectDTO> paperSubjectDTOS){
        Example example = new Example(PaperSubject.class);
        for(PaperSubjectDTO paperSubjectDTO:paperSubjectDTOS){
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("paperId",paperSubjectDTO.getPaperId())
            .andEqualTo("version",paperSubjectDTO.getVersion());
            example.or(criteria);
        }
        int result = paperSubjectDao.deleteSubjects(example);
        if(result != paperSubjectDTOS.size()){
            throw new BusinessException(PaperManageCodeEnum.MULTI_DELETE_EXCEPTION);
        }
        return result;
    }

    /**
     * 批量新增试题以及批量新增对应答案
     * @param paperSubjectDTOS
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertSubjectList(List<PaperSubjectDTO> paperSubjectDTOS){
        // 计算正确数量
        int num = 0;
        // 试卷总分
        paperScore = new BigDecimal("0");
        for(PaperSubjectDTO paperSubjectDTO:paperSubjectDTOS){
            //雪花算法生成试题id
            snowflake = IdUtil.createSnowflake(1, 1);
            Long id = snowflake.nextId();
            // 添加固定项（创建时间、修改时间）
            paperSubjectDTO.setId(id);
            Date date = new Date();
            paperSubjectDTO.setCreatedTime(date);
            paperSubjectDTO.setUpdatedTime(date);
            //计算试卷总分
            paperScore = paperScore.add(paperSubjectDTO.getScore());
            // 获取答案数组
            List<PaperSubjectAnswer> answerList = paperSubjectDTO.getAnswers();
            for(PaperSubjectAnswer answer:answerList){
                // 添加题目id到答案数组
                answer.setSubjectId(id);
            }
            List<PaperSubjectAnswerDTO> paperSubjectAnswerDTOs = BeanUtil.convertListToList(answerList,PaperSubjectAnswerDTO.class);
            // 调用批量添加答案方法
            paperSubjectAnswerService.insertAnswerList(paperSubjectAnswerDTOs);
            // 调用添加题目方法
            int subjectResult = paperSubjectDao.insertSubject(BeanUtil.copyData(paperSubjectDTO,PaperSubject.class));
            if(subjectResult>0){
                //计算成功个数
                num++;
            }
        }
        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setId(paperSubjectDTOS.get(0).getPaperId());
        paperDTO.setScore(paperScore);
        // 更新试卷分数
        paperService.updatePaperWithoutVersion(paperDTO);
        if(num == paperSubjectDTOS.size()){
            return num;
        }else {
            throw new BusinessException(PaperManageCodeEnum.MULTI_INSERT_EXCEPTION);
        }
    }

    /**
     * 根据题型获得题目id
     * @param questionType
     * @return
     */
    public List<Long> getPaperIdsWithSubjectType(Long questionType){
        Example example = new Example(PaperSubject.class);
        example.createCriteria().andEqualTo("questionType",questionType);
        List<PaperSubject> list = paperSubjectDao.getPaperSubject(example);
        List<Long> ids = new ArrayList<>();
        for(PaperSubject paperSubject:list){
            ids.add(paperSubject.getId());
        }
        return ids;
    }
}
