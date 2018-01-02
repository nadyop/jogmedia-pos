package com.blibli.controller;

import com.blibli.model.Employee;
import com.blibli.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeServices employeeService;

    @RequestMapping("/tampilemp")
    public String EmployeeList(Model model){
        model = employeeService.showListEmployee(model);
        return "manager/show/tampilemp";
    }
    @RequestMapping(value = "/tampilemp/", method = RequestMethod.POST)
    public String simpanDataEmployee(Employee employee){
        System.out.println("ini id employee dr controller: "+employee.getEmployee_id());
        System.out.println("ini nama employee: "+employee.getEmployee_name());
        employeeService.save(employee);
        return "redirect:/tampilemp";
    }
    @RequestMapping(value = "/tampilemp/search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchKey") String searchKey){
        model = employeeService.searchCategoryByKeyword(model, searchKey);
        return "manager/show/tampilemp";
    }

    @RequestMapping(value = "/tampilemp/createEmployee", method = RequestMethod.GET)
    public String tampilFormCreateEmployee(Model model) {
        model = employeeService.manageFormCreateEmployee(model);
        return "manager/edit/createEmployee";
    }
    @RequestMapping(value = "/tampilemp/edit/{id}", method = RequestMethod.GET)
    public String editData(@PathVariable int id, Model model){
        model = employeeService.manageEditEmployee(model, id);
        return "manager/edit/createEmployee";
    }
    @RequestMapping(value = "/employee/softDelete/{id}", method = RequestMethod.GET)
    public String softDeleteEmployee(@PathVariable Integer id) {
        employeeService.softDeleteEmployee(id);
        return "redirect:/tampilemp";
    }
}
