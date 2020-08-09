package com.boss.bes.paper.dao;


import com.boss.bes.paper.pojo.entity.PaperSubject;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface PaperSubjectDao<T> {
    /**
     * 单个查询,查询试卷的题目
     * @return
     */
    T getSubjectById(Long id);

    /**
     * 查询试卷题目
     * @param example
     * @return
     */
    List<T> getPaperSubject(Example example);

    PaperSubject queryPaperWithAnswer(Long id);

    int insertSubject(PaperSubject paperSubject);

    int deleteSubjects(Example example);
}
