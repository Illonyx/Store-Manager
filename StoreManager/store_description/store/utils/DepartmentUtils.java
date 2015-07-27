package store.utils;

import java.util.ArrayList;

import store.model.Department;
import store.model.Store;

public class DepartmentUtils {
	
	public static Boolean isDepartmentAlreadyExisting(Store store, Department department)
	{
		Boolean isNew = true;
		ArrayList<Department> allDepartments = store.getDepartmentsList();
		
		for(Department d : allDepartments){
			if(d.getDepartmentName().equals(department.getDepartmentName()))
			{
				isNew = false;
			}
		}
		return isNew;
		
	}
	
	public static Store departmentHasBeenChanged(Store store, Department department)
	{
		ArrayList<Department> allDepartments = store.getDepartmentsList();
		
		if(isDepartmentAlreadyExisting(store, department)) allDepartments.add(department);
		else
		{
			for(Department d : allDepartments){
				if(d.getDepartmentName() == department.getDepartmentName()) d = department;
			}
		}
		
		return store;
	}
	
}
