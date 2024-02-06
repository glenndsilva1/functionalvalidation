package combinator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface CustomerValidation extends Function<Customer, List<CustomerValidation.ValidationResult>> {
	public static CustomerValidation isNameValid() {
		return customer -> {
         List<CustomerValidation.ValidationResult> errors = new ArrayList<>();
         if (customer.name().contains(" ")) {
             errors.add(CustomerValidation.ValidationResult.NAME_NOT_VALID);
         }
         return errors;
		};
	}

	public static CustomerValidation isEmailValid(){
		return customer -> {
	         List<CustomerValidation.ValidationResult> errors = new ArrayList<>();
	         if (!customer.email().contains("@")) {
	             errors.add(CustomerValidation.ValidationResult.EMAIL_NOT_VALID);
	         }
	         return errors;
			};
	}
	
	public static CustomerValidation isPhoneValid(){
		return customer -> {
	         List<CustomerValidation.ValidationResult> errors = new ArrayList<>();
	         if (customer.phone().contains("#")) {
	             errors.add(CustomerValidation.ValidationResult.PHONE_NOT_VALID);
	         }
	         return errors;
			};

	}

	default CustomerValidation and(CustomerValidation other){
		return customer -> {
            List<CustomerValidation.ValidationResult> errors = new ArrayList<>();
            errors.addAll(this.apply(customer));
            errors.addAll(other.apply(customer));
            return errors;
		};
	}
	enum ValidationResult {
        NAME_NOT_VALID,
        EMAIL_NOT_VALID,
        PHONE_NOT_VALID
    }
}
