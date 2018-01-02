package com.blibli.dao_api;

import com.blibli.model.Employee;

import java.util.List;

public interface EmployeeDaoInterface {

    void softDeleteEmployee(int id);
    void insertCategory(Employee E);
    Employee getIdEmployee(int idEmployee);
    List<Employee> getAllEmployee();
    List<Employee> search(String searchKey);
}