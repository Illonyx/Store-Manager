package com.bigbeard.storemanager.data;

import java.util.ArrayList;

import com.bigbeard.storemanager.data.Department;

public class Store {
	private String name;
	private ArrayList<Department> departmentsList;
	//TODO : Manage this another way
	//public String path = "";
	
	public Store(String name,ArrayList<Department> departmentsList){
		this.name = name;
		this.departmentsList = departmentsList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String storeName) {
		this.name = storeName;
	}

	public ArrayList<Department> getDepartmentsList() {
		return departmentsList;
	}

	public void setDepartmentsList(ArrayList<Department> departmentsList) {
		this.departmentsList = departmentsList;
	}
}
