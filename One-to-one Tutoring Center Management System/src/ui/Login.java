package ui;

import user.Administrator;
import user.AdministratorContainer;
import user.Student;
import user.StudentContainer;
import user.Tutor;
import user.TutorContainer;

/**
 * A class for performing login logic of users.
 * 
 * @author idilissever
 *
 */
public class Login {

	private static StudentContainer studentContainer = StudentContainer.getInstance();
	private static TutorContainer tutorContainer = TutorContainer.getInstance();
	private static AdministratorContainer administratorContainer = AdministratorContainer.getInstance();

	/**
	 * This method checks login information passed as arguments and returns the
	 * resulting boolean.
	 * 
	 * @param username
	 * @param password
	 * @param userType
	 * @return true, if the login information is correct for the given userType,
	 *         false otherwise.
	 */
	public static boolean login(String username, String password, String userType) {

		var students = studentContainer.getAllStudents();
		var tutors = tutorContainer.getAllTutors();
		var admins = administratorContainer.getAllAdministrators();

		switch (userType) {
		case "Student":
			if (!students.containsKey(username)) {
				return false;
			}
			Student student = students.get(username);
			return password.equals(student.getPassword());
		case "Tutor":
			if (!tutors.containsKey(username)) {
				return false;
			}
			Tutor tutor = tutors.get(username);
			return password.equals(tutor.getPassword());

		case "Administrator":
			if (!admins.containsKey(username)) {
				return false;
			}
			Administrator administrator = admins.get(username);
			return password.equals(administrator.getPassword());
		}
		return false;

	}

}
