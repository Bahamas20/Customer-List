package net.hamas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	private Long id;
	private String firstname;
	private String lastname;
	private float expenditure;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public float getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(float expenditure) {
		this.expenditure = expenditure;
	}
	
	public String toString() {
		return "Customer Number: " + id + "\n"
				 + "Customer Name: " + firstname + " " + lastname + "\n"
				 +  "Customer Expenditure: $" + expenditure + "\n";
				
	}
	 
	

}

