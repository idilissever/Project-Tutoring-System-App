package course;

import java.util.List;

import user.Student;
import user.Tutor;

/**
 * A concrete subclass of the abstract class Course. Only used to create objects
 * and store information.
 * 
 * @author idilissever
 *
 */
public class BeginnerCourse extends Course {
	/**
	 * Creates a Beginner Course object with the given arguments using super class'
	 * constructor.
	 * 
	 * @param name
	 * @param courseId
	 * @param students
	 * @param prerequisites
	 * @param equipments
	 * @param tutors
	 */
	public BeginnerCourse(String name, String courseId, List<Student> students, List<Course> prerequisites,
			List<Equipment> equipments, List<Tutor> tutors) {
		super(name, courseId, students, prerequisites, equipments, tutors);
	}

	/**
	 * Creates a Beginner Course object with the given arguments using super class'
	 * constructor.
	 * 
	 * @param name
	 * @param courseId
	 */
	public BeginnerCourse(String name, String courseId) {
		super(name, courseId);
	}

}
