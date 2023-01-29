package ui;

import java.util.ArrayList;
import java.util.List;

import course.Course;
import course.CourseContainer;
import user.StudentContainer;
import user.Tutor;
import user.Student;
import user.Student.Gender;
import user.Tutor.TutorType;
import user.TutorContainer;

/**
 * A class for performing register process for users: Tutor and Student.
 * 
 * @author idilissever
 *
 */
public class Register {
	/**
	 * Creates a student with the given arguments and adds it to the
	 * StudentContainer.students.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param tCKN
	 * @param age
	 * @param idNumber
	 * @param monateryBalance
	 * @param gender
	 * @param passedCoursesString
	 */
	public static void RegisterStudent(String name, String username, String password, String tCKN, int age,
			int idNumber, double monateryBalance, Gender gender, List<String> passedCoursesString) {

		var studentContainer = StudentContainer.getInstance();
		var courseContainer = CourseContainer.getInstance();

		Student student = new Student(name, username, password, tCKN, age, idNumber, monateryBalance, gender, null,
				new ArrayList<>());
		studentContainer.addStudent(student);

		List<Course> passedCourses = new ArrayList<>();

		for (String string : passedCoursesString) {
			var courseToAdd = courseContainer.getAllCourses().get(string);
			passedCourses.add(courseToAdd);
		}
		student.setPassedCourses(passedCourses);

	}

	/**
	 * Creates a tutor with the given arguments and adds it to the
	 * TutorContainer.tutors.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param tutoringCost
	 * @param percentageCut
	 * @param tutorType
	 * @param courses
	 * @return
	 */
	public static Tutor RegisterTutor(String name, String username, String password, double tutoringCost,
			double percentageCut, TutorType tutorType, List<Course> courses) {
		var defaultImageURL = "jsll";
		var tutorContainer = TutorContainer.getInstance();

		Tutor tutor = new Tutor(name, username, password, defaultImageURL, 0.0, tutoringCost, percentageCut, tutorType,
				courses);
		tutorContainer.addTutor(tutor);

		return tutor;
	}

}
