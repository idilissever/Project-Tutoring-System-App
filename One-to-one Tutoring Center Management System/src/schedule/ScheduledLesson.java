package schedule;

import user.*;

import java.time.LocalTime;

import course.Course;

/**
 * A Scheduled lesson object has 5 fields which are: Tutor, Student, Course and
 * two LocalTime objects representing the start time and end time.
 * 
 * @author idilissever
 *
 */
public class ScheduledLesson implements Comparable<ScheduledLesson> {

	private LocalTime startTime, endTime;
	private Student student;
	private Tutor tutor;
	private Course course;
	/**
	 * Creates a ScheduledLesson object with the given arguments.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param student
	 * @param tutor
	 * @param course
	 */
	public ScheduledLesson(LocalTime startTime, LocalTime endTime, Student student, Tutor tutor, Course course) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.student = student;
		this.tutor = tutor;
		this.course = course;
	}

	/**
	 * Overriding Comparable Interface's compareTo method for sorting the scheduled
	 * lesson objects according to their start time.
	 * 
	 * @param ScheduledLesson
	 * 
	 */
	@Override
	public int compareTo(ScheduledLesson o) {
		return this.startTime.compareTo(o.startTime);
	}


	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
