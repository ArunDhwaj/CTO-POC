package com.sbp.poc.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.stream.Collectors.*;

public class EmployeeStreamDriver {
    private static Logger Log = LoggerFactory.getLogger(EmployeeStreamDriver.class);

    public static void main(String[] args) {
        Log.info("Poc: EmployeeStreamDriver");

        //Create List of Employee
        List<Employee> employees = createEmployees();

        // 1) Do the GroupingBy Department
        // 2) In each Department Find the nth Highest salary employee
        NthHighestSalaryInDepartment(employees);

        // 1) Do the GroupingBy Department
        // 2) In each Department Find the nth Highest salary employee
        // 3) Then get the name of that employee
        NthHighestSalaryEmpDetails(employees);

    }

    private static List<Employee> createEmployees(){
                List<Employee> employees = Arrays.asList(
                        new Employee(1, 1, "emp1", 3000000.00f),
                        new Employee(1, 2, "emp2", 4100000.00f),
                        new Employee(1, 3, "emp3", 5100000.00f),
                        new Employee(2, 4, "emp4", 6100000.00f),
                        new Employee(2, 5, "emp5", 7100000.00f),
                        new Employee(2, 6, "emp6", 8100000.00f),
                        new Employee(3, 7, "emp7", 9100000.00f),
                        new Employee(3, 8, "emp8", 10100000.00f),
                        new Employee(3, 9, "emp9", 15100000.00f),
                        new Employee(4, 10, "emp10", 20000000.00f),
                        new Employee(4, 11, "emp11", 25000000.00f),
                        new Employee(4, 12, "emp12", 30000000.00f),
                        new Employee(5, 13, "emp13", 40000000.00f),
                        new Employee(5, 14, "emp14", 50000000.00f),
                        new Employee(5, 15, "emp15", 100000000.00f)
                );
        return employees;
    }

    /*
    Find the Nth highest salary employee in each Department
    N=2;
     */
    private static void NthHighestSalaryInDepartment(List<Employee> employees){

        Map<Integer, Optional<Employee>> nthHighestSalaryEmployee = employees.stream()
                .collect(groupingBy(
                        Employee::getDepartmentId,
                        collectingAndThen(toList(),
                                list -> list.stream()
                                                .sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst()

                )));

        Log.info("1. 2ndHighestSalaryEmployeeInEachDepartment: "+ nthHighestSalaryEmployee);
    }

    /*
   Find the Nth highest salary employee name and salary in each Department
   N=2;
    */
    private static void NthHighestSalaryEmpDetails(List<Employee> employees){

        Map<Integer, String> nthHighestSalaryEmployee = employees.stream()
                .collect(groupingBy(
                        Employee::getDepartmentId,
                        collectingAndThen(toList(),
                                list -> {
                            Optional<Employee> emp = list.stream()
                                            .sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst();
                                return emp.isEmpty()? "No such employee" : emp.get().getName();
                                }
                        )));

        Log.info("2. 2ndHighestSalaryEmployeeInEachDepartment: "+ nthHighestSalaryEmployee);
    }
}
