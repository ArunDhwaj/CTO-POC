package com.sbp.poc.stream;

import lombok.Data;
@Data
//@AllArgsConstructor
public class Employee {
    private Integer departmentId;
    private Integer id;
    private String name;
    private float salary;

    public Employee(Integer departmentId, Integer id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
