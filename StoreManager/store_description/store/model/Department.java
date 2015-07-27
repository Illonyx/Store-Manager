package store.model;

import java.util.ArrayList;

public class Department{
	
	private String departmentName;
	private ArrayList<Shelf> shelvesList;
	
	public Department(){
		this.departmentName="default";
		this.shelvesList=new ArrayList<Shelf>(); 
	}
	
	public Department(String _departmentName, ArrayList<Shelf> _shelvesList){
		this.departmentName=_departmentName;
		this.shelvesList=_shelvesList; 
	}
	
	public String getDepartmentName() {
		return this.departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public ArrayList<Shelf> getShelvesList() {
		return this.shelvesList;
	}


	public void setShelvesList(ArrayList<Shelf> shelvesList) {
		this.shelvesList = shelvesList;
	}

}
