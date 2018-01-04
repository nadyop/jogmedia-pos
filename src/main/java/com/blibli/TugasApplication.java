package com.blibli;

import com.blibli.dao.category.*;
import com.blibli.model.Employee;
import com.blibli.model.Store;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TugasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TugasApplication.class, args);
//		create employee
		Employee employee= new Employee("Manager atas", "manager01","a","Manager");
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.createTable();
		employeeDao.insertEmployee(employee);
//		create book
		BookDao bookDao = new BookDao();
		bookDao.createTableBook();

//		create category
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.createTableCategory();

//		create store
		StoreDao storeDao = new StoreDao();
		storeDao.deleteTableStore();
		storeDao.alterTableStore();
		Store store = new Store("Jogmedia","Jalan Kapten Tendean No.17 Yogyakarta","31.460.572.6-061.000","55182","contact@gmail.com");
		storeDao.createTableStore();
		storeDao.insertStore(store);

//		create transaction
		TransactionDao transactionDao = new TransactionDao();
		transactionDao.createTableTransaction();

//		create detil_transaction
		TransactionDao transactionDetil = new TransactionDao();
		transactionDetil.createTableDetilTransaction();

//		create temp_detil
		TransactionDao transactionTemp = new TransactionDao();
		transactionTemp.createTableTemp();

	}
}
