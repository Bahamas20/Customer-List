package net.hamas;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MssqlJpaApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepo customerRepo;

	public static void main(String[] args) {
		SpringApplication.run(MssqlJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);


		
		while(true) {
			
			System.out.println("Welcome to the Customer Management System!");
			System.out.println("Please enter your choice:");
			System.out.println("1. View all customers");
			System.out.println("2. Add a customer");
			System.out.println("3. View Customer by ID");
			System.out.println("4. Delete Customer by ID");
			System.out.println("5. Edit Customer details");
			System.out.println("0. Exit");
			
			int choice = scanner.nextInt();
	
			switch (choice) {
				case 1:
					viewCustomers();
					continue;
				case 2:
					addCustomer(scanner);
					continue;
				case 3:
					viewCustomer(scanner);
					continue;
				case 4:
					deleteCustomer(scanner);
					continue;
				case 5:
					editCustomer(scanner);
					continue;
				case 0:
					System.out.println("Exiting the program...");
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}

		scanner.close();
		}
	}

	private void viewCustomers() {
		List<Customer> customers = customerRepo.findAll();
		customers.forEach(System.out::println);
	}
	
	private void deleteCustomer(Scanner scanner) {
		System.out.println("Enter the ID of customer to delete:");
		String id = scanner.next();
		customerRepo.deleteById(Integer.parseInt(id));
	}

	private void addCustomer(Scanner scanner) {
		System.out.println("Enter customer First Name:");
		String firstname = scanner.next();
		System.out.println("Enter customer Last Name:");
		String lastname = scanner.next();
		System.out.println("Enter customer Expenditure:");
		String expenditure = scanner.next();

		Customer customer = new Customer();
		Long id = customerRepo.count() + 1;
		customer.setId(id);
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		customer.setExpenditure(Float.parseFloat(expenditure));

		Customer savedCustomer = customerRepo.save(customer);
		System.out.println("New customer added: " + savedCustomer);
	}
	
	private void viewCustomer(Scanner scanner) {
		System.out.println("Enter ID of customer to view:");
		String id = scanner.next();
	
		Optional<Customer> customer = customerRepo.findById(Integer.parseInt(id));
		System.out.println(customer.get());

	}
	
	private void editCustomer(Scanner scanner) {
		System.out.println("Enter ID of customer to edit:");
		String id = scanner.next();
		
		Customer editingCustomer = customerRepo.findById(Integer.parseInt(id)).get();
		
		System.out.println("Customer to edit details is given below");
		System.out.println(editingCustomer);
		
		System.out.println("Indicate which field you would like to edit");
		System.out.println("1.First Name");
		System.out.println("2.Last Name");
		System.out.println("3.Expenditure");
		
		int choice = scanner.nextInt();
		
		System.out.println("Indicate the new value:");
		String value = scanner.next();
		
		switch(choice) {
		
		case 1:
			editCustomerFirstname(editingCustomer,value);
			break;
		case 2:
			editCustomerLastname(editingCustomer,value);
			break;
		case 3:
			editCustomerExpenditure(editingCustomer,value);
			break;
		
		
		}
		
		System.out.println("Customer Details Succesfully Editted");
		

	}
	
	private void editCustomerFirstname(Customer editingCustomer,String value) {
		editingCustomer.setFirstname(value);
		customerRepo.save(editingCustomer);
		
	}
	
	private void editCustomerLastname(Customer editingCustomer,String value) {
		editingCustomer.setLastname(value);
		customerRepo.save(editingCustomer);

		
	}
	private void editCustomerExpenditure(Customer editingCustomer,String value) {
		editingCustomer.setExpenditure(Float.parseFloat(value));
		customerRepo.save(editingCustomer);
		 
	}

}
