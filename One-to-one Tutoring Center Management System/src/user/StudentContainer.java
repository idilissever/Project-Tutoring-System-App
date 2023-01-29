package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import course.Course;
import course.CourseContainer;
import user.Student.Gender;
import user.StudentStatus.Status;

/**
 * This class generates all the students in the application. Uses a Singleton
 * pattern for making sure that there will be only one instance of this class.
 * 
 * @author idilissever
 *
 */
public class StudentContainer {

	private static StudentContainer instance;
	private HashMap<String, Student> students;
	private HashMap<String, Course> allCourses;
	private HashMap<Student, List<StudentStatus>> statusStudents = new HashMap<>();

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private StudentContainer() {
		this.students = new HashMap<>();
		var courseContainer = CourseContainer.getInstance();
		this.allCourses = courseContainer.getAllCourses();
	}

	/**
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of student container class.
	 * 
	 * @return the only instance of this class
	 */
	public static StudentContainer getInstance() {
		if (instance == null) {
			instance = new StudentContainer();
		}
		return instance;
	}

	/**
	 * This method creates the students in the system with passed, registered
	 * courses and adds the created students to the field students.
	 */
	public void setStudents() {
		var studentManager = StudentManager.getInstance();

		// setting student1
		var student1 = new Student("Alkan Akısu", "alkanakisu", "123", "19383194402", 22, 71445, 100000, Gender.MALE);
		addStudent(student1);
		addPassedCoursesToStudent(student1, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100", "ENGR200",
				"COMP132");
		addRegisteredCoursesToStudent(student1, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		// setting student2
		var student2 = new Student("İdil İşsever", "idilissever", "123", "19382294402", 21, 80447, 100000,
				Gender.FEMALE);
		addStudent(student2);
		addPassedCoursesToStudent(student2, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student2, "ENGR200", "MATH203", "COMP132", "MATH204", "INDR220");

		// setting student 3
		var student3 = new Student("Ata Göğüş", "atagogus", "123", "12345678901", 20, 80322, 10000, Gender.MALE);
		addStudent(student3);
		addPassedCoursesToStudent(student3, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100", "COMP132",
				"ENGR200");
		addRegisteredCoursesToStudent(student3, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		// setting student 4
		var student4 = new Student("Pelin Yağmur", "pelinyagmur", "123", "12345678910", 23, 23822, 10000,
				Gender.FEMALE);
		addStudent(student4);
		addPassedCoursesToStudent(student4, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100", "COMP132",
				"ENGR200");
		addRegisteredCoursesToStudent(student4, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		// setting student 5
		var student5 = new Student("Yasemin İşsever", "yaseminissever", "123", "12345678910", 35, 12628, 10000,
				Gender.FEMALE);
		addStudent(student5);
		addPassedCoursesToStudent(student5, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100", "COMP132",
				"ENGR200");
		addRegisteredCoursesToStudent(student5, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		// setting student 6
		var student6 = new Student("Barış Tan Yılmaz", "baristanyilmaz", "123", "12345678910", 24, 23253, 10000,
				Gender.MALE);
		addStudent(student6);
		addPassedCoursesToStudent(student6, "MATH106", "MATH107", "PHYS101", "COMP100", "ENGR200", "INDR100",
				"COMP132");
		addRegisteredCoursesToStudent(student6, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		var student7 = new Student("Gülfem Yılmaz", "gulfemyilmaz", "123", "12345678910", 26, 71223, 20000,
				Gender.FEMALE);
		addStudent(student7);
		addPassedCoursesToStudent(student7, "MATH106", "MATH107", "PHYS101", "COMP100", "ENGR200", "INDR100",
				"COMP132");
		addRegisteredCoursesToStudent(student7, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		var student8 = new Student("Feyyaz Yiğit", "feyyazyiğit", "123", "12345678910", 31, 23792, 20000000,
				Gender.FEMALE);
		addStudent(student8);
		addPassedCoursesToStudent(student8, "MATH106", "MATH107", "PHYS101", "COMP100", "ENGR200", "INDR100",
				"COMP132");
		addRegisteredCoursesToStudent(student8, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		var student9 = new Student("Orkan Akısu", "orkanakisu", "123", "12345678910", 30, 23210, 12000, Gender.MALE);
		addStudent(student9);
		addPassedCoursesToStudent(student9, "MATH106", "MATH107", "PHYS101", "COMP100", "ENGR200", "INDR100",
				"COMP132");
		addRegisteredCoursesToStudent(student9, "MATH203", "PHYS102", "MATH204", "INDR220", "COMP304", "COMP341");

		// the students below does not have any passed courses
		var student10 = new Student("Yılmaz Gibi", "yilmazgibi", "123", "12345678910", 35, 24832, 72000, Gender.MALE);
		addStudent(student10);
		addRegisteredCoursesToStudent(student10, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student11 = new Student("Bo Burnham", "boburnham", "123", "12345678910", 30, 72000, 350000, Gender.MALE);
		addStudent(student11);
		addRegisteredCoursesToStudent(student11, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student12 = new Student("Asrın Demirkıran", "asrindemirkiran", "123", "12345678910", 21, 80440, 10000,
				Gender.MALE);
		addStudent(student12);
		addRegisteredCoursesToStudent(student12, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student13 = new Student("Kaan Süleyman", "kaansuleyman", "123", "12345678910", 22, 72983, 10000,
				Gender.MALE);
		addStudent(student13);
		addRegisteredCoursesToStudent(student13, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student14 = new Student("Tolga İşsever", "tolgaissever", "123", "12345678910", 52, 97352, 10000,
				Gender.MALE);
		addStudent(student14);
		addRegisteredCoursesToStudent(student14, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student15 = new Student("Güldane İşsever", "guldaneissever", "123", "12345678910", 67, 97352, 20000,
				Gender.FEMALE);
		addStudent(student15);
		addRegisteredCoursesToStudent(student15, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student16 = new Student("İbrahim İşsever", "ibrahimissever", "123", "12345678910", 74, 97322, 15000,
				Gender.MALE);
		addStudent(student16);
		addRegisteredCoursesToStudent(student16, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");

		var student17 = new Student("Jülide İşsever", "julideissever", "123", "12345678910", 53, 97352, 10000,
				Gender.FEMALE);
		addStudent(student17);
		addRegisteredCoursesToStudent(student17, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		
		
		var student18 = new Student("Çiler Akısu", "cilerakisu", "123", "12345678910", 55, 97352, 100000,
				Gender.FEMALE);
		addPassedCoursesToStudent(student18, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student18, "ENGR200", "COMP132");
		
		var student19 = new Student("Mete Akısu", "meteakisu", "123", "12345678910", 56, 97452, 1000000,
				Gender.MALE);
		addPassedCoursesToStudent(student19, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student19, "ENGR200", "COMP132");
		
		var student20 = new Student("Morty Smith", "mortysmith", "123", "12345678910", 14, 97352, 100000,
				Gender.MALE);
		addPassedCoursesToStudent(student20, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student20, "ENGR200", "COMP132");
		
		var student21 = new Student("Jerry Smith", "jerrysmith", "123", "12345678910", 45, 97352, 100000,
				Gender.MALE);
		addPassedCoursesToStudent(student21, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student21, "ENGR200", "COMP132");
		
		var student22 = new Student("Summer Smith", "summersmith", "123", "12345678910", 17, 97352, 100000,
				Gender.FEMALE);
		addPassedCoursesToStudent(student22, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student22, "ENGR200", "COMP132");
		
		var student23 = new Student("Matthew Perry", "matthewperry", "123", "12345678910", 57, 97352, 100000,
				Gender.MALE);
		addPassedCoursesToStudent(student23, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student23, "ENGR200", "COMP132");
		
		var student24 = new Student("Jennifer Aniston", "jenniferaniston", "123", "12345678910", 45, 97352, 100000,
				Gender.FEMALE);
		addPassedCoursesToStudent(student24, "MATH106", "MATH107", "PHYS101", "COMP100", "INDR100");
		addRegisteredCoursesToStudent(student24, "ENGR200", "COMP132");
		
		for (Student student : getAllStudents().values()) {
			setStudentStatus(student);
		}
		setStudentsDebt();

	}

	/**
	 * This method is a shortcut for the StudentManager.addPassedCourse() method.
	 * Takes arguments: one student object and unlimited number of String courseId's
	 * that are already available in the system. It adds the related courses to the
	 * student's passedCourses field.
	 * 
	 * @param student
	 * @param courses
	 */
	private void addPassedCoursesToStudent(Student student, String... courses) {
		var studentManager = StudentManager.getInstance();
		for (int i = 0; i < courses.length; i++) {
			var course = allCourses.get(courses[i]);
			studentManager.addPassedCourses(student, course);
		}
	}

	/**
	 * This method is a shortcut for the StudentManager.addRegisteredCourse()
	 * method. Takes arguments: one student object and unlimited number of String
	 * courseId's that are already available in the system. It adds the related
	 * courses to the student's registeredCourses field.
	 * 
	 * @param student
	 * @param courses
	 */
	private void addRegisteredCoursesToStudent(Student student, String... courses) {
		var studentManager = StudentManager.getInstance();
		for (int i = 0; i < courses.length; i++) {
			var course = allCourses.get(courses[i]);
			studentManager.addRegisteredCourses(student, course);
		}
	}

	/**
	 * This method adds the given student to the field students with its student
	 * user name.
	 * 
	 * @param student
	 */
	public void addStudent(Student student) {
		students.put(student.getUsername(), student);
	}

	public HashMap<String, Student> getAllStudents() {
		return students;
	}

	/**
	 * This method creates all the Student Status objects of the given student
	 * passed as argument. It sets the student status object's status field; as PASS
	 * for passed courses and DEFAULT for registered courses.
	 * 
	 * @param student
	 */
	public void setStudentStatus(Student student) {
		var statuses = new ArrayList<StudentStatus>();
		var passedCourses = student.getPassedCourses();

		for (Course passedCourse : passedCourses) {

			var status = new StudentStatus(student, passedCourse);
			status.setStatus(Status.PASS);
			statuses.add(status);
		}

		var registeredCourses = student.getRegisteredCourses();
		for (Course registeredCourse : registeredCourses) {
			var status = new StudentStatus(student, registeredCourse);
			statuses.add(status);

		}
		statusStudents.put(student, statuses);
	}

	public HashMap<Student, List<StudentStatus>> getStatusStudents() {
		return statusStudents;
	}

	/**
	 * This method sets all the students debts to the value returned by the
	 * StudentManager class' calculateCost() method.
	 */
	public void setStudentsDebt() {
		var students = getAllStudents().values();

		for (Student student : students) {
			var debt = StudentManager.getInstance().calculateCost(student);
			student.setTotalDebt(debt);
		}
	}

}
