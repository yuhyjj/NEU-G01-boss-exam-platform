package com.boss.bes.paper.dao.impl;



import boss.brs.xtrain.dataconvention.dao.impl.AbstractBaseDao;
import com.boss.bes.paper.dao.PaperDao;
import com.boss.bes.paper.pojo.dto.PaperDTO;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.utils.mapper.PaperMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class IPaperDaoImpl extends AbstractBaseDao<Paper, PaperMapper, PaperDTO> implements PaperDao {

    @Override
    public int delete(Example example) {
        return myMapper.deleteByExample(example);
    }

    @Override
    public List<Paper> queryWithPage(Example example) {
        return myMapper.selectByExample(example);
    }

    @Override
    public Paper getPaperById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePaper(Paper paper) {
        return myMapper.updateByPrimaryKeySelective(paper);
    }

    @Override
    public Object queryByExample(Example example) {
        return myMapper.selectOneByExample(example);
    }

    @Override
    public int insert(Paper paper) {
        return myMapper.insert(paper);
    }
}
