package user;

import java.util.HashSet;
import java.util.List;

import course.Course;
import course.CourseManager;
import course.Equipment;
import schedule.ScheduledLesson;
import system.TutoringCenterSystem;
import user.StudentStatus.Status;

/**
 * This class uses a Singleton pattern for making sure that there will be only
 * one instance of this class.
 * 
 * @author idilissever
 *
 */
public class StudentManager {

	private static StudentManager instance;

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private StudentManager() {
	}

	/**
	 * 
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of student manager class.
	 * 
	 * @return the only instance of this class
	 */
	public static StudentManager getInstance() {
		if (instance == null)
			instance = new StudentManager();
		return instance;
	}

	/**
	 * This method checks if the given student is eligible for the given course
	 * considering the students passed courses contains all the prerequisites of the
	 * given course.
	 * 
	 * @param student
	 * @param course
	 * @return true, if student is eligible for course, false otherwise.
	 */
	public boolean isEligibleForCourse(Student student, Course course) {
		List<Course> prerequisites = course.getPrerequisites();

		if (prerequisites != null) {
			for (Course prerequisite : prerequisites) {

				if (!(student.getPassedCourses().contains(prerequisite))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method checks the eligibility using isEligibleForCourse method and if
	 * eligible registers the student to the given course.
	 * 
	 * @param student
	 * @param course
	 * @return true, if student is eligible for course, false otherwise.
	 */
	public boolean registerToCourse(Student student, Course course) {
		if (!isEligibleForCourse(student, course)) {
			return false;
		}
		addRegisteredCourses(student, course);
		return true;
	}

	public double calculateCost(Student student) {
		var totalCost = 0.0;

		totalCost += calculateEquipmentCost(student);
		totalCost += calculateScheduleCost(student);
		student.setTotalDebt(totalCost);

		return totalCost;
	}

	/**
	 * This method calculates the unpaid equipment cost of student for the courses
	 * they take.
	 * 
	 * @param student
	 * @return the unpaid equipment cost
	 */
	public double calculateEquipmentCost(Student student) {
		var courses = student.getRegisteredCourses();
		var equipments = new HashSet<Equipment>();
		var totalCost = 0.0;

		for (Course course : courses) {
			if (course.getEquipments() == null)
				continue;
			for (Equipment equipment : course.getEquipments()) {
				if (equipment == null || student.getEquipments().contains(equipment))
					continue;
				equipments.add(equipment);
			}
		}

		for (Equipment equipment : equipments) {
			totalCost += equipment.getPrice();
		}
		return totalCost;

	}

	/**
	 * This method calculates the cost of unpaid lessons they took.
	 * 
	 * @param student
	 * @return the unpaid lesson cost
	 */
	public double calculateScheduleCost(Student student) {
		var unpaidSessions = student.getUnpaidLessons();
		var totalCost = 0.0;

		for (ScheduledLesson scheduledLesson : unpaidSessions) {
			totalCost += scheduledLesson.getTutor().getTutoringCost();
		}
		return totalCost;

	}

	/**
	 * This method makes the payments of unpaid equipments to the tutoring center
	 * system and updates the monetary balances of both student and system.
	 * 
	 * @param student
	 */
	public void payForEquipments(Student student) {
		var equipmentsStudentHas = student.getEquipments();
		var courses = student.getRegisteredCourses();
		var equipments = new HashSet<Equipment>();
		var system = TutoringCenterSystem.getInstance();

		for (Course course : courses) {
			if (course.getEquipments() == null)
				continue;
			for (Equipment equipment : course.getEquipments()) {
				if (equipment == null || equipmentsStudentHas.contains(equipment))
					continue;
				equipments.add(equipment);

			}
		}

		double paid = 0;
		for (Equipment equipment : equipments) {
			student.addEquipment(equipment);
			system.gainFromEquipmentSold(equipment);
			paid += equipment.getPrice();
		}
		student.setMonateryBalance(student.getMonateryBalance() - paid);
		student.setTotalDebt(student.getTotalDebt() - paid);
	}

	/**
	 * This method makes the payments of unpaid lessons to the tutor and updates the
	 * monetary balances of the student,the tutor and the system.
	 * 
	 * @param student
	 */
	public void payForSessionCost(Student student) {
		var unpaidSessions = student.getUnpaidLessons();
		var system = TutoringCenterSystem.getInstance();
		var tutorManager = TutorManager.getInstance();

		double paid = 0.0;
		for (ScheduledLesson scheduledLesson : unpaidSessions) {
			var tutor = scheduledLesson.getTutor();
			system.gainFromTutoringSession(tutor);
			tutorManager.gainFromSession(tutor);
			student.addPaidSession(scheduledLesson);
			paid += tutor.getTutoringCost();
		}
		student.setMonateryBalance(student.getMonateryBalance() - paid);
		student.setTotalDebt(student.getTotalDebt() - paid);

	}

	/**
	 * Makes all the required payments by the student to the relevant tutors and the
	 * system.
	 * 
	 * @param student
	 */
	public void payExpenses(Student student) {
		payForEquipments(student);
		payForSessionCost(student);

	}

	/**
	 * Adds the given course to the given student's field passedCourses.
	 * 
	 * @param student
	 * @param course
	 */
	public void addPassedCourses(Student student, Course course) {
		student.getPassedCourses().add(course);
	}

	/**
	 * Adds the given course to the given student's field registeredCourses.
	 * 
	 * @param student
	 * @param course
	 */
	public void addRegisteredCourses(Student student, Course course) {
		var courseManager = CourseManager.getInstance();
		student.getRegisteredCourses().add(course);
		courseManager.addStudent(course, student);
	}

	/**
	 * This method is a helper for checking a student's status for a certain course.
	 * 
	 * @param student
	 * @param course
	 * @return the StudentStatus object of the given course and the student
	 */
	public StudentStatus getStudentStatus(Student student, Course course) {
		var statusOfStudent = StudentContainer.getInstance().getStatusStudents().get(student);
		StudentStatus studentStatusForCourse = null;

		for (StudentStatus studentStatus : statusOfStudent) {
			if (!studentStatus.getCourse().getCourseId().equals(course.getCourseId())) {
				continue;
			}
			studentStatusForCourse = studentStatus;
		}

		return studentStatusForCourse;

	}

	/**
	 * This method is for updating the student status of a student for a given
	 * course. This method also organizes the student's passed and registered
	 * courses list accordingly with the update of status.
	 * 
	 * @param student
	 * @param course
	 * @param newStatus
	 */
	public void setStudentStatus(Student student, Course course, String newStatus) {
		var status = getStudentStatus(student, course);
		if (newStatus == "DEFAULT") {
			status.setStatus(Status.DEFAULT);
		}

		if (newStatus == "FAIL") {
			status.setStatus(Status.FAIL);
			student.getRegisteredCourses().remove(course);
			course.getStudents().remove(student);
		}
		if (newStatus == "PASS") {
			status.setStatus(Status.PASS);
			addPassedCourses(student, course);
			student.getRegisteredCourses().remove(course);
		}

	}

}
