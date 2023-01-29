package course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import user.Student;
import user.StudentManager;
import user.StudentStatus;
import user.Tutor;
import user.Tutor.TutorType;

/**
 * This class generates all the courses in the application. Uses a Singleton
 * pattern for making sure that there will be only one instance of this class.
 * 
 * @author idilissever
 *
 */
public class CourseContainer {

	private static CourseContainer courseContainer;
	private HashMap<String, Course> allCourses = new HashMap<>();
	private HashMap<Course, List<StudentStatus>> courseStatusHashMap = new HashMap<>();

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private CourseContainer() {
	}

	/**
	 * 
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of course container class.
	 * 
	 * @return the instance of course container class
	 * 
	 */
	public static CourseContainer getInstance() {
		if (courseContainer == null) {
			courseContainer = new CourseContainer();
		}
		return courseContainer;
	}

	/**
	 * This method creates the courses in the system with prerequisites and
	 * equipments every time the application is executed.
	 */
	public void setCourses() {
		var courseManager = CourseManager.getInstance();
		var equipmentContainer = EquipmentContainer.getInstance();
		var equipments = equipmentContainer.getAllEquipments();
		var bCourse1 = new BeginnerCourse("Calculus", "MATH106");
		courseManager.addEquipment(bCourse1, equipments.get("Calculator"));
		courseManager.addEquipment(bCourse1, equipments.get("Tablet"));

		var bCourse2 = new BeginnerCourse("Linear Algebra", "MATH107");
		courseManager.addEquipment(bCourse2, equipments.get("Calculator"));

		var bCourse3 = new BeginnerCourse("General Physics 1", "PHYS101");
		courseManager.addEquipment(bCourse3, equipments.get("Calculator"));

		var bCourse4 = new BeginnerCourse("Introduction to Computer Science and Programming", "COMP100");
		courseManager.addEquipment(bCourse4, equipments.get("Computer"));

		var bCourse5 = new BeginnerCourse("Introduction to Industrial Engineering", "INDR100");
		courseManager.addEquipment(bCourse5, equipments.get("Tablet"));

		var bCourse6 = new BeginnerCourse("Probability and Random Variables for Engineers", "ENGR200");
		courseManager.addPrerequisiteToCourse(bCourse1, bCourse6);
		courseManager.addEquipment(bCourse6, equipments.get("Calculator"));

		var aCourse1 = new AdvancedCourse("Multivariable Calculus", "MATH203");
		courseManager.addPrerequisiteToCourse(bCourse1, aCourse1);
		courseManager.addEquipment(aCourse1, equipments.get("Calculator"));

		var aCourse2 = new AdvancedCourse("General Physics 2", "PHYS102");
		courseManager.addPrerequisiteToCourse(bCourse3, aCourse2);
		courseManager.addEquipment(aCourse2, equipments.get("Tablet"));

		var aCourse3 = new AdvancedCourse("Differential Equations", "MATH204");
		courseManager.addPrerequisiteToCourse(bCourse2, aCourse3);
		courseManager.addEquipment(aCourse3, equipments.get("Mouse"));
		courseManager.addEquipment(aCourse3, equipments.get("Keyboard"));
		courseManager.addEquipment(aCourse3, equipments.get("Computer"));

		var aCourse4 = new AdvancedCourse("Advanced Programming", "COMP132");
		courseManager.addPrerequisiteToCourse(bCourse4, aCourse4);
		courseManager.addEquipment(aCourse4, equipments.get("Computer"));

		var aCourse5 = new AdvancedCourse("Introduction to Computing for Operations Research", "INDR220");
		courseManager.addPrerequisiteToCourse(bCourse5, aCourse5);
		courseManager.addEquipment(aCourse5, equipments.get("Keyboard"));
		courseManager.addEquipment(aCourse5, equipments.get("Computer"));

		var aCourse6 = new AdvancedCourse("Operating Systems", "COMP304");
		courseManager.addPrerequisiteToCourse(aCourse4, aCourse6);
		courseManager.addEquipment(aCourse6, equipments.get("Mouse"));
		courseManager.addEquipment(aCourse6, equipments.get("Computer"));

		var aCourse7 = new AdvancedCourse("Introduction to Artificial Intelligence", "COMP341");
		courseManager.addPrerequisiteToCourse(bCourse6, aCourse7);
		courseManager.addEquipment(aCourse7, equipments.get("Calculator"));

		addCourse("MATH106", bCourse1);
		addCourse("MATH107", bCourse2);
		addCourse("PHYS101", bCourse3);
		addCourse("COMP100", bCourse4);
		addCourse("INDR100", bCourse5);
		addCourse("ENGR200", bCourse6);

		addCourse("MATH203", aCourse1);
		addCourse("PHYS102", aCourse2);
		addCourse("MATH204", aCourse3);
		addCourse("COMP132", aCourse4);
		addCourse("INDR220", aCourse5);
		addCourse("COMP304", aCourse6);
		addCourse("COMP341", aCourse7);

	}

	/**
	 * 
	 * This method adds the given course to the field allCourses with the given
	 * course ID
	 * 
	 * @param courseId
	 * @param course
	 * 
	 */
	public void addCourse(String courseId, Course course) {
		allCourses.put(courseId, course);

	}

	/**
	 * 
	 * @return the field allcourses which is an HashMap from course ID to the
	 *         related course object
	 * 
	 * 
	 */
	public HashMap<String, Course> getAllCourses() {
		return allCourses;
	}

	/**
	 * 
	 * This method returns the available courses for first registered, only the
	 * beginner courses that don't have any prerequisites.
	 * 
	 * @return a HashMap from course ID to course object
	 * 
	 */
	public HashMap<String, Course> getAvailableCoursesForFirstRegistered() {
		var availableCoursesForFirstRegistered = new HashMap<String, Course>();
		for (String courseId : allCourses.keySet()) {
			var course = allCourses.get(courseId);
			if (!course.getPrerequisites().isEmpty()) {
				continue;
			}
			availableCoursesForFirstRegistered.put(courseId, course);
		}
		return availableCoursesForFirstRegistered;

	}

	/**
	 * 
	 * This method returns enrollable courses for the given student considering the
	 * courses they passed.
	 * 
	 * @param student
	 * @return a HashMap from course ID to course object
	 * 
	 */

	public HashMap<String, Course> getEnrollableCourses(Student student) {
		var studentManager = StudentManager.getInstance();
		var enrollableCourses = new HashMap<String, Course>();

		for (Course course : getAllCourses().values()) {
			var condition = !studentManager.isEligibleForCourse(student, course)
					|| student.getRegisteredCourses().contains(course) || student.getPassedCourses().contains(course);

			if (condition) {
				continue;
			}
			enrollableCourses.put(course.getCourseId(), course);
		}
		return enrollableCourses;
	}

	/**
	 * 
	 * This methods returns the tutorable courses by the given tutor considering the
	 * tutor's level.
	 * 
	 * @param tutor
	 * @return a HashMap from course ID to course object
	 * 
	 */
	public HashMap<String, Course> getTutorableCourses(Tutor tutor) {
		var tutorableCourses = new HashMap<String, Course>();

		for (Course course : getAllCourses().values()) {
			var condition1 = (course instanceof AdvancedCourse && tutor.getTutorType() == TutorType.B_LEVEL);
			var condition2 = tutor.getCourses().contains(course);
			if (condition1 || condition2) {
				continue;
			}
			tutorableCourses.put(course.getCourseId(), course);
		}
		return tutorableCourses;
	}

	/**
	 * 
	 * This method returns the beginner courses.
	 * 
	 * @return a HashMap from course ID to course object
	 * 
	 */
	public HashMap<String, Course> getBeginnerCourses() {
		var beginnerCourses = new HashMap<String, Course>();

		for (Course course : getAllCourses().values()) {
			if (course instanceof AdvancedCourse) {
				continue;
			}
			beginnerCourses.put(course.getCourseId(), course);
		}
		return beginnerCourses;
	}

	/**
	 * 
	 * This method separates the courses and returns Advanced Courses.
	 * 
	 * @return a HashMap from course ID to course object
	 */
	public HashMap<String, Course> getAdvancedCourses() {
		var advancedCourses = new HashMap<String, Course>();

		for (Course course : getAllCourses().values()) {
			if (course instanceof BeginnerCourse) {
				continue;
			}
			advancedCourses.put(course.getCourseId(), course);
		}
		return advancedCourses;

	}

	/**
	 * 
	 * This method helps mapping course objects to a List of Student Status objects
	 * and store the HashMap in the field courseStatusMap for the given Course.
	 * 
	 * @param course
	 * 
	 */
	public void setCourseStatus(Course course) {
		var statuses = new ArrayList<StudentStatus>();
		var students = course.getStudents();

		for (Student student : students) {
			var status = StudentManager.getInstance().getStudentStatus(student, course);
			statuses.add(status);
		}
		courseStatusHashMap.put(course, statuses);

	}

	/**
	 * 
	 * @return a map from course objects to Lists of Student Status objects
	 */
	public HashMap<Course, List<StudentStatus>> getStatusCourses() {
		return courseStatusHashMap;

	}

}
