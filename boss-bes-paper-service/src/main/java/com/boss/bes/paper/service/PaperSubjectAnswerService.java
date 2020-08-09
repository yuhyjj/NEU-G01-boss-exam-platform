package com.boss.bes.paper.service;


import boss.bes.log.exception.code.enums.PaperManageCodeEnum;
import boss.bes.log.exception.type.BusinessException;

import boss.brs.xtrain.dataconvention.service.BaseCURDService;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.boss.bes.paper.dao.PaperSubjectAnswerDao;
import com.boss.bes.paper.pojo.dto.PaperSubjectAnswerDTO;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.utils.BeanUtil;
import com.boss.bes.paper.utils.mapper.PaperSubjectAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * description:试卷试题答案service
 * @author yhy
 * time:2020-07-06 17:55
 */
@Service
public class PaperSubjectAnswerService extends BaseCURDService<PaperSubjectAnswerDTO, PaperSubjectAnswer, PaperSubjectAnswerDTO, PaperSubjectAnswerMapper> {
    @Autowired
    PaperSubjectAnswerDao<PaperSubjectAnswer> paperSubjectAnswerDao;

    /**
     * 查询答案
     *
     * @param paperSubjectAnswerDTO
     * @return
     */
    @Override
    public List<PaperSubjectAnswerDTO> query(PaperSubjectAnswerDTO paperSubjectAnswerDTO) {
        Example example = new Example(PaperSubjectAnswer.class);
        example.createCriteria().andEqualTo("subjectId", paperSubjectAnswerDTO.getSubjectId());
        List<PaperSubjectAnswer> paperSubjectAnswers = paperSubjectAnswerDao.getSubjectAnswer(example);
        return BeanUtil.convertListToList(paperSubjectAnswers, PaperSubjectAnswerDTO.class);
    }

    @Override
    protected PaperSubjectAnswer doObjectTransf(PaperSubjectAnswerDTO paperSubjectAnswerDTO) {
        return null;
    }

    /**
     * 插入答案
     *
     * @param paperSubjectAnswerDTO
     * @return
     */
    public int insertAnswer(PaperSubjectAnswerDTO paperSubjectAnswerDTO) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        paperSubjectAnswerDTO.setId(snowflake.nextId());
        Date date = new Date();
        paperSubjectAnswerDTO.setCreatedTime(date);
        paperSubjectAnswerDTO.setUpdatedTime(date);
        paperSubjectAnswerDTO.setVersion(1L);
        return paperSubjectAnswerDao.insertAnswer(BeanUtil.copyData(paperSubjectAnswerDTO, PaperSubjectAnswer.class));
    }

    /**
     * 批量添加答案
     *
     * @param paperSubjectAnswerDTOs
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertAnswerList(List<PaperSubjectAnswerDTO> paperSubjectAnswerDTOs) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        // 成功的数量
        int num = 0;
        for (PaperSubjectAnswerDTO paperSubjectAnswerDTO : paperSubjectAnswerDTOs) {
            paperSubjectAnswerDTO.setId( snowflake.nextId());
            Date date = new Date();
            paperSubjectAnswerDTO.setCreatedTime(date);
            paperSubjectAnswerDTO.setUpdatedTime(date);
            int result = paperSubjectAnswerDao.insertAnswer(BeanUtil.copyData(paperSubjectAnswerDTO, PaperSubjectAnswer.class));
            if (result > 0) {
                num++;
            }
        }
        if (num == paperSubjectAnswerDTOs.size()) {
            return num;
        } else {
            throw new BusinessException(PaperManageCodeEnum.MULTI_INSERT_EXCEPTION);
        }
    }

    /**
     * 批量删除答案
     * @param paperSubjectAnswerDTOs
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteAnswerList(List<PaperSubjectAnswerDTO> paperSubjectAnswerDTOs) {
        Example example = new Example(PaperSubjectAnswer.class);
        for (PaperSubjectAnswerDTO paperSubjectAnswerDTO : paperSubjectAnswerDTOs) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("subjectId", paperSubjectAnswerDTO.getSubjectId())
                    .andEqualTo("version", paperSubjectAnswerDTO.getVersion());
            example.or(criteria);
        }
        List<PaperSubjectAnswer> subjectAnswer = paperSubjectAnswerDao.getSubjectAnswer(example);
        int result = paperSubjectAnswerDao.deleteAnswerList(example);
        if (result != subjectAnswer.size()) {
            throw new BusinessException(PaperManageCodeEnum.MULTI_DELETE_EXCEPTION);
        }
        return result;
    }
}
