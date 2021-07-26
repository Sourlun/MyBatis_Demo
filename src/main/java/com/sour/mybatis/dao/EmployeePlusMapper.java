package com.sour.mybatis.dao;

import com.sour.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface EmployeePlusMapper {

    Employee getEmployeeById(Integer id);

}
