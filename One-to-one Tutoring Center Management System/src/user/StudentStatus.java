package user;

import course.Course;

/**
 * This class is created for each pair of ( Student, Course ) and has a Status
 * object as a field. Represents the status of a student for a course.
 * 
 * @author idilissever
 *
 */
public class StudentStatus {

	private Student student;
	private Course course;
	private Status status;

	/**
	 * Two argument constructor. Status is set to DEFAULT by default. Tutor's and
	 * Administrator's can only modify the status of a student for a course.
	 * 
	 * @param student
	 * @param course
	 */
	public StudentStatus(Student student, Course course) {
		this.student = student;
		this.course = course;
		status = Status.DEFAULT;
	}

	public enum Status {
		PASS, FAIL, DEFAULT
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;

	}

	/**
	 * This method updates the student status from FAIL to PASS if it is not already
	 * PASS.
	 * 
	 */
	public void updateStudentStatus() {
		if (student.getPassedCourses().contains(course)) {
			return;
		}
		student.getPassedCourses().add(course);
		student.getRegisteredCourses().remove(course);
	}

	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

}
