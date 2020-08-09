package com.boss.bes.paper.utils.mapper;;


import boss.brs.xtrain.dataconvention.dao.mapper.CommonMapper;
import com.boss.bes.paper.pojo.entity.PaperSubject;


public interface PaperSubjectMapper extends CommonMapper<PaperSubject> {

    PaperSubject queryPaperWithAnswer(Long id);

}