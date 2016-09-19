package com.bigbeard.storemanager.data;

import java.util.ArrayList;

import com.bigbeard.storemanager.data.Shelf;

public class Department {
	private String name;
	private ArrayList<Shelf> shelvesList;
	
	public Department(String name, ArrayList<Shelf> shelvesList){
		this.name = name;
		this.shelvesList = shelvesList; 
	}
	
	public String getName() {
		return name;
	}


	public void setName(String departmentName) {
		this.name = departmentName;
	}


	public ArrayList<Shelf> getShelvesList() {
		return shelvesList;
	}


	public void setShelvesList(ArrayList<Shelf> shelvesList) {
		this.shelvesList = shelvesList;
	}
}
