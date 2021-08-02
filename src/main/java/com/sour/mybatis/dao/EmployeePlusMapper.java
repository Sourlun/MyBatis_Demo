package com.sour.mybatis.dao;

import com.sour.mybatis.bean.Employee;

/**
 *
 */
public interface EmployeePlusMapper {

    Employee getEmployeeById(Integer id);

    Employee getEmpAndDept(Integer id);

    Employee getEmpAndDeptByAssociation(Integer id);

    /**
     * 分步查询
     */
    Employee getEmployeeByIdStep(Integer id);

}
