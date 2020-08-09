package com.boss.bes.paper.dao.impl;


import boss.brs.xtrain.dataconvention.dao.impl.AbstractBaseDao;
import com.boss.bes.paper.dao.PaperSubjectDao;
import com.boss.bes.paper.pojo.dto.PaperSubjectDTO;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.utils.mapper.PaperSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class IPaperSubjectDaoImpl extends AbstractBaseDao<PaperSubject, PaperSubjectMapper, PaperSubjectDTO> implements PaperSubjectDao {
    @Autowired
    PaperSubjectMapper paperSubjectMapper;
    @Override
    public PaperSubject getSubjectById(Long id) {
        return paperSubjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PaperSubject> getPaperSubject(Example example) {
        return paperSubjectMapper.selectByExample(example);
    }

    @Override
    public PaperSubject queryPaperWithAnswer(Long id) {
        return paperSubjectMapper.queryPaperWithAnswer(id);
    }
    // 未被使用
    @Override
    public int insertSubject(PaperSubject paperSubject) {
        return paperSubjectMapper.insert(paperSubject);
    }

    @Override
    public int deleteSubjects(Example example) {
        return paperSubjectMapper.deleteByExample(example);
    }

}
