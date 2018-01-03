package com.blibli.services;

import com.blibli.dao_api.EmployeeDaoInterface;
import com.blibli.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class EmployeeServices {
    @Autowired
    EmployeeDaoInterface employeeDaoInterface;
    public Model showListEmployee(Model model){
        model.addAttribute("employee",employeeDaoInterface.getAllEmployee());
        return model;
    }
    public Model searchCategoryByKeyword(Model model, String searchKey){
        model.addAttribute("employee", employeeDaoInterface.search(searchKey));
        return model;
    }
    public Model manageFormCreateEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return model;
    }
    public Model manageEditEmployee(Model model, int id){
        model.addAttribute("employee",employeeDaoInterface.getIdEmployee(id));
        return  model;
    }

    public void save(Employee e){
        employeeDaoInterface.insertEmployee(e);
    }

    public void softDeleteEmployee(Integer id) {

        employeeDaoInterface.softDeleteEmployee(id);
    }
}
