package com.boss.bes.paper.utils.mapper;


import boss.brs.xtrain.dataconvention.dao.mapper.CommonMapper;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;

import java.util.List;

public interface PaperSubjectAnswerMapper extends CommonMapper<PaperSubjectAnswer> {
    List<PaperSubjectAnswer> getAnswersBySubjectId(Long subjectId);
}