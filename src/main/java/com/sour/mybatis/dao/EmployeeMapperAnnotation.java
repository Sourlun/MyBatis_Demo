package com.sour.mybatis.dao;

import com.sour.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * 基于注解
 */
public interface EmployeeMapperAnnotation {

    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(Integer id);
}
