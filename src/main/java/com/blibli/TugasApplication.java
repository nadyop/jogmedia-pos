package com.blibli;

import com.blibli.dao.category.EmployeeDao;
import com.blibli.dao_api.EmployeeDaoInterface;
import com.blibli.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TugasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TugasApplication.class, args);
		Employee employee= new Employee("manager", "manager","$2a$10$mdhjwE.eJGRXp6P59U.dUOkOtweSDNBIcWTM3Jo3uhhqAGjT69/S2","Manager");
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.insertCategory(employee);
	}
}
