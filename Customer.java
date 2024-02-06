package combinator;

import static combinator.CustomerValidation.*;

import java.util.List;

public class Customer {
	String name;
	String email;
	String phone;
	
public Customer(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String name() {
		return this.name;
	}
	
	public String email() {
		return this.email;
	}
	
	public String phone() {
		return this.phone;
	}
	
	public static void main(String args[]){
		Customer customer = new Customer("Alice","alice@gmail.com","123");

		List<CustomerValidation.ValidationResult> errors =
				CustomerValidation.isNameValid()
		        .and(CustomerValidation.isEmailValid())
		        .and(CustomerValidation.isPhoneValid())
		        .apply(customer);

		if(!errors.isEmpty()) {
		    for(CustomerValidation.ValidationResult error : errors) {
		        System.out.println(error);
		    }
		    // Handle the errors accordingly
		}else {
		     System.out.println("Registration is successful");
		}
	}
}
