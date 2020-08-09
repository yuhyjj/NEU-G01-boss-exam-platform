package com.boss.bes.paper.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询的基本实现
 * @author:zyz
 * 2020/7/4-9:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasePageQuery{
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    /*public Integer getPageNum() {
        return pageNum > 0 ? pageNum : 5;
    }

    public Integer getPageSize() {
        return pageSize > 0 ? pageSize : 1;
    }*/

}

