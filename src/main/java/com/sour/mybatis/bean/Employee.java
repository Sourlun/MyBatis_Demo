package com.sour.mybatis.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 别名
 */
@Alias("employee")
public class Employee implements Serializable {

    private Integer id;

    private String lastName;

    private String email;

    private String gender;

    private Integer deptId;

    private Department department;

    public Employee() {
    }

    public Employee(String lastName, String email, String gender) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                '}';
    }
}
