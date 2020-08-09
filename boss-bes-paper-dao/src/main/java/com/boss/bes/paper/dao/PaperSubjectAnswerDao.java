package com.boss.bes.paper.dao;


import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface PaperSubjectAnswerDao<T> {
    /**
     * 查询题目答案
     * @param example
     * @return
     */
    List<T> getSubjectAnswer(Example example);
    int insertAnswer(PaperSubjectAnswer paperSubjectAnswer);
    int deleteAnswerList(Example example);
}
