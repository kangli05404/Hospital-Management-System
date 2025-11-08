package HospitalManagementSystem;

public class Validator {

	public static void check(boolean condition, String errorMessage) {
		if (!condition) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkNotEmpty(String value, String fieldName) {
		check(value != null && !value.trim().isEmpty(), fieldName + " is required.");
	}

	public static void checkSex(String value) {
		check(value.matches("M|F"), "Sex must be 'M' or 'F'.");
	}

	public static void checkPositiveInteger(String value, String fieldName) {
		try {
			int num = Integer.parseInt(value);
			check(num > 0, fieldName + " must be a positive number.");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(fieldName + " must be a valid positive integer.");
		}
	}

	public static void checkAlphabeticName(String value, String fieldName) {
		check(value.matches("[a-zA-Z ]+"), fieldName + " must contain only alphabets and spaces.");
	}

	public static void checkAlphaNumericWithLetters(String value, String fieldName) {
		// Must contain at least one letter, and only letters, digits, and spaces
		check(value.matches("(?=.*[a-zA-Z])[a-zA-Z0-9 ]+"),
				fieldName + " must include letters and can contain numbers and spaces, but not be only numbers.");
	}
	
	public static void checkDoctorId(String value, String fieldName) {
		if (!value.matches("^[Dd]\\d{3}$")) {
			throw new IllegalArgumentException(
					fieldName + " must start with 'D' or 'd' followed by exactly 3 digits (e.g., D123).");
		}
	}

	public static void checkPatientId(String value, String fieldName) {
		if (!value.matches("^[Pp]\\d{3}$")) {
			throw new IllegalArgumentException(
					fieldName + " must start with 'P' or 'p' followed by exactly 3 digits (e.g., P123).");
		}
	}
	
	public static void checkValidFacilityName(String value, String fieldName) {
	    // Must contain at least one letter, and can include letters, digits, spaces, hyphens, or slashes
	    check(value.matches("(?i)^(?=.*[a-z])[a-z0-9 \\-/]+$"),
	            fieldName + " must contain at least one letter, and can include digits, spaces, hyphens (-), and slashes (/).");
	}
	
	public static void checkStaffId(String value, String fieldName) {
		if (!value.matches("^[Ss]\\d{3}$")) {
			throw new IllegalArgumentException(
					fieldName + " must start with 'S' or 's' followed by exactly 3 digits (e.g., S123).");
		}
	}

}
