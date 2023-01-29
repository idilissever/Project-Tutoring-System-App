package course;

import user.*;

/**
 * This class uses a singleton pattern which makes the object of this course
 * unique.
 * 
 * @author idilissever
 *
 */
public class CourseManager {

	private static CourseManager instance;

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private CourseManager() {

	}

	/**
	 * 
	 * Other classes uses this static method to get the only instance of Course
	 * Manager class.
	 * 
	 * @return the only instance of the course manager class
	 * 
	 * 
	 */
	public static CourseManager getInstance() {
		if (instance == null) {
			instance = new CourseManager();
		}
		return instance;
	}

	/**
	 * 
	 * This method adds the given prerequisite to the given Course's prerequisites
	 * field.
	 * 
	 * @param prerequisitte
	 * @param course
	 * @return
	 * 
	 */
	public boolean addPrerequisiteToCourse(Course prerequisitte, Course course) {
		if (course instanceof BeginnerCourse && prerequisitte instanceof AdvancedCourse) {
			return false;
		}
		course.getPrerequisites().add(prerequisitte);
		return true;
	}

	/**
	 * This method adds the given student to the students field at the given course.
	 * 
	 * @param course
	 * @param student
	 * @return
	 */
	public boolean addStudent(Course course, Student student) {
		if (!StudentManager.getInstance().isEligibleForCourse(student, course)) {
			return false;
		}
		course.getStudents().add(student);
		return true;

	}

	/**
	 * This method adds the given tutor to the tutors field at the given course.
	 * 
	 * @param course
	 * @param tutor
	 * @return true if tutor is eligible for teaching that course, false otherwise
	 */
	public boolean addTutor(Course course, Tutor tutor) {
		var tutorManager = TutorManager.getInstance();
		if (course instanceof AdvancedCourse && !tutorManager.canTutorAdvanced(tutor)) {
			return false;
		}
		course.getTutors().add(tutor);
		return true;

	}

	/**
	 * this method adds the given equipment to the equipments field at the given
	 * course.
	 * 
	 * @param course
	 * @param equipment
	 * @return
	 */
	public boolean addEquipment(Course course, Equipment equipment) {
		course.getEquipments().add(equipment);
		return true;
	}

}
