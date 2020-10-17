package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Test {

	public static void main(String[] args) {

		System.out.println("Test 1 - DepartmentDao findAll");
		DepartmentDao deptDao = DaoFactory.createDepartmentDao();
		List<Department> dept = deptDao.findAll();
		dept.forEach(System.out::println);
		
		System.out.println();
		System.out.println("Test 2 - DepartmentDao findById");
		Department department = deptDao.findById(3);
		System.out.println(department);
		
		System.out.println("Test 3 - DepartmentDao deleteById");
		deptDao.deleteById(6);
		System.out.println("Delete completed");
		
		System.out.println("Test 4 - DepartmentDao update");
		department = deptDao.findById(1);
		department.setName("Accessories");
		deptDao.update(department);
		System.out.println("Update completed");
		
		System.out.println("Test 5 - DepartmentDao insert");
		Department newDept = new Department(8, "Books");
		deptDao.insert(newDept);
		System.out.println("Insertion completed");
		
	}

}
