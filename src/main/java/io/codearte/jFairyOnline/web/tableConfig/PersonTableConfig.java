package io.codearte.jFairyOnline.web.tableConfig;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/27/17
 */
public class PersonTableConfig {

	private boolean firstName;
	private boolean lastName;

	public boolean isFirstName() {
		return firstName;
	}

	public void setFirstName(boolean firstName) {
		this.firstName = firstName;
	}

	public boolean isLastName() {
		return lastName;
	}

	public void setLastName(boolean lastName) {
		this.lastName = lastName;
	}
}
