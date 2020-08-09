package com.boss.bes.paper.utils.basemapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
@RegisterMapper
public interface MyPaperMapper<T> extends MySqlMapper<T>, Mapper<T> {
}
