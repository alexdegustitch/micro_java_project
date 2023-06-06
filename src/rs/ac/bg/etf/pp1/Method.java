package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import rs.etf.pp1.symboltable.concepts.Struct;

public class Method { // pomocna klasa za klasu SemanticAnalyzer.java
		
	private String name;
	private ArrayList<Struct> params; 
			
	public Method(String name) {		
		this.name = name;
		this.params = new ArrayList<Struct>();
	}				
	
	public Method(String name, ArrayList<Struct> params) {		
		this.name = name;
		this.params = params;
	}				

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Struct> getParams() {
		return params;
	}		
	
	public void setParams(ArrayList<Struct> params) {
		this.params = params;
	}
		
}
