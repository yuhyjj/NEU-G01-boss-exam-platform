package com.boss.bes.paper.dao;

import com.boss.bes.paper.pojo.entity.Paper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface PaperDao<T> {
    /**
     * 通过example删除
     * @param example
     * @return
     */
    int delete(Example example);
    /**
     * 进行条件查询
     * @param example
     * @return
     */
    List<T> queryWithPage(Example example);

    /**
     * 通过id查询试卷信息
     * @param id
     * @return
     */
    T getPaperById(Long id);
    /**
     * 进行插入（上传、下载会用到）
     * @param paper
     * @return
     */
    int insert(Paper paper);
    int updatePaper(Paper paper);

    /**
     * 条件查询得到一条案例
     * @param example
     * @return
     */
    T queryByExample(Example example);
}
