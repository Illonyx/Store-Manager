package store.model;

import java.util.ArrayList;

public class Store {
	
	private String storeName;
	private ArrayList<Department> departmentsList;
	
	public Store(){
		this.storeName="default";
		this.departmentsList=new ArrayList<Department>();
	}
	
	public Store(String _storeName,ArrayList<Department> _departmentsList){
		this.storeName=_storeName;
		this.departmentsList=_departmentsList;
	}
	
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public ArrayList<Department> getDepartmentsList() {
		return this.departmentsList;
	}

	public void setDepartmentsList(ArrayList<Department> departmentsList) {
		this.departmentsList = departmentsList;
	}
	
}
