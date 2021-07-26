package com.sour.mybatis.dao;

import com.sour.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  #{} 是以预编译的形式, 将参数设置到sql里面   select * from xxx where id = ?
 *  ${} 取出的值, 直接拼装在sql里面, 会有安全问题   select * from xxx where id = 1
 *      大多情况, 取参数的值都使用#{}
 *      比如分表,排序:
 *          原生jdbc不支持占位符的地方我们可以使用${}
 *          按照年份分表拆分
 *          select * from ${year}_salary
 *          select * from salary order by ${order}
 *
 */
public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);

    /**
     * mybatis允许增删改直接定义以下类型的返回值
     *  Integer Long Boolean Void
     * @param employee
     * @return
     */
    boolean updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    /**
     * 使用命名参数@Param
     *  #{指定的key}
     * @param id
     * @param lastName
     * @return
     */
    Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    /**
     * 使用实体查询
     *  实体的key就是#{key}
     * @param employee
     * @return
     */
    Employee getEmployeeByPojg(Employee employee);

    /**
     * 使用map查询
     *  map的key就是#{key}
     * @param map
     * @return
     */
    Employee getEmployeeByMap(Map<String, Object> map);

    /**
     * 返回List
     * @param lastName
     * @return
     */
    List<Employee> getEmployeesByLastNameLike(String lastName);


    /**
     * 返回Map
     * @param id
     * @return
     */
    Map<String, Object> getReturnMap(Integer id);

    /**
     * 返回Map (特殊)
     *  Map<主键, Employee>
     * @param lastName
     * @return
     */
    @MapKey("id")  //指定Map的key, 封装map的时候,用哪个属性做key
    Map<Integer, Employee> getReturnMapByLastNameLike(String lastName);

}
