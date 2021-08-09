package com.sour.mybatis.dao;

import com.sour.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态SQL
 *
 */
public interface EmployeeDynamicSqlMapper {

    /**
     * 携带了哪个字段就用哪个字段查询
     */
    List<Employee> getByConditionIf(Employee employee);

    /**
     * sql字符串前缀后缀规则
     */
    List<Employee> getByConditionTrim(Employee employee);

    /**
     * 类似switch
     */
    List<Employee> getByConditionChoose(Employee employee);

    /**
     * 根据实体类里面的id更新
     *  带了哪一列的值就更新哪一列
     */
    void updateEmp(Employee employee);

    /**
     * 根据多个id查询 (in查询)
     */
    List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    /**
     * 批量保存
     */
    void addEmps(@Param("emps") List<Employee> emps);

    /**
     * 使用 bind
     *       bind的使用: 把%xx%弄成一个整体
     */
    List<Employee> testBind(Employee employee);

}
