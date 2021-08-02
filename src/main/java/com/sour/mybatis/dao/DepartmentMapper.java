package com.sour.mybatis.dao;

import com.sour.mybatis.bean.Department;

public interface DepartmentMapper {

    Department getById(Integer id);

    /**
     * 把对应的员工也查询出来
     */
    Department getByIdPlus(Integer id);

    /**
     * 分步查询
     */
    Department getByIdStep(Integer id);

}
