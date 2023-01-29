package course;

import java.util.List;
import user.Student;
import user.Tutor;

/**
 * Concrete subclass of the abstract class Course
 * 
 * @author idilissever
 *
 */
public class AdvancedCourse extends Course {
	
	/**
	 * 
	 * Creates an Advanced Course object with the given arguments
	 * using the super class Course's constructor.
	 * @param name
	 * @param courseId
	 * @param students
	 * @param prerequisites
	 * @param equipments
	 * @param tutors
	 */
	public AdvancedCourse(String name, String courseId, List<Student> students, List<Course> prerequisites,
			List<Equipment> equipments, List<Tutor> tutors) {
		super(name, courseId, students, prerequisites, equipments, tutors);
	}
	/**
	 Creates an Advanced Course object with the given two arguments
	 * using the super class Course's constructor.
	 * @param name
	 * @param courseId
	 */
	public AdvancedCourse(String name, String courseId) {
		super(name, courseId);
	}

}
