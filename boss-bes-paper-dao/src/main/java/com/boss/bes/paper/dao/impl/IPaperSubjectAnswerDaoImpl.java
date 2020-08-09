package com.boss.bes.paper.dao.impl;

import boss.brs.xtrain.dataconvention.dao.impl.AbstractBaseDao;
import com.boss.bes.paper.dao.PaperSubjectAnswerDao;
import com.boss.bes.paper.pojo.dto.PaperSubjectAnswerDTO;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.utils.mapper.PaperSubjectAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Repository
public class IPaperSubjectAnswerDaoImpl extends AbstractBaseDao<PaperSubjectAnswer, PaperSubjectAnswerMapper, PaperSubjectAnswerDTO> implements PaperSubjectAnswerDao<PaperSubjectAnswer> {
    @Autowired
    PaperSubjectAnswerMapper paperSubjectAnswerMapper;
    @Override
    public List<PaperSubjectAnswer> getSubjectAnswer(Example example) {
        return paperSubjectAnswerMapper.selectByExample(example);
    }

    @Override
    public int insertAnswer(PaperSubjectAnswer paperSubjectAnswer) {
        return paperSubjectAnswerMapper.insert(paperSubjectAnswer);
    }

    @Override
    public int deleteAnswerList(Example example) {
        return paperSubjectAnswerMapper.deleteByExample(example);
    }

}
