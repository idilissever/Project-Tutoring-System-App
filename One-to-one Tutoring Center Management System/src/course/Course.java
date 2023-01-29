package course;

import java.util.ArrayList;
import java.util.List;

import user.*;

/**
 * An abstract class Course that is only used for storing and modifying
 * information with it's fields.
 * 
 * @author idilissever
 *
 */
public abstract class Course {

	private String name;
	private String courseId;

	private List<Student> students;
	private List<Course> prerequisites;
	private List<Equipment> equipments;
	private List<Tutor> tutors;

	/**
	 * Constructor to be used by it's subclasses Advanced Course and Beginner
	 * Course.
	 * 
	 * @param name
	 * @param courseId
	 */
	public Course(String name, String courseId) {
		this(name, courseId, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	/**
	 * Constructor to be used by it's subclasses Advanced Course and Beginner
	 * Course.
	 * 
	 * @param name
	 * @param courseId
	 * @param students
	 * @param prerequisites
	 * @param equipments
	 * @param tutors
	 */
	public Course(String name, String courseId, List<Student> students, List<Course> prerequisites,
			List<Equipment> equipments, List<Tutor> tutors) {
		this.name = name;
		this.courseId = courseId;
		this.students = students;
		this.prerequisites = prerequisites;
		this.equipments = equipments;
		this.tutors = tutors;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public List<Tutor> getTutors() {
		return tutors;
	}

	public void setTutors(List<Tutor> tutors) {
		this.tutors = tutors;
	}

}
