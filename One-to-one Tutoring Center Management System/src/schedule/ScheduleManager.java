package schedule;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import course.Course;
import user.Student;
import user.Tutor;

/**
 * A concrete class ScheduleManager that each tutor has a unique instance of.
 * Each Schedule Manager also has it's own unique tutor in its fields.
 * 
 * @author idilissever
 *
 */
public class ScheduleManager {

	private List<ScheduledLesson> scheduledLessons;
	private static HashMap<String, ScheduleManager> scheduleManagers = new HashMap<>();
	private Tutor tutor;
	private List<Integer> startTimes;

	/**
	 *
	 * Schedule manager class has a static field that is an HashMap from tutor's
	 * user name to ScheduleManager objects which helps accessing any Tutor's
	 * Schedule Manager easily.
	 * 
	 * @param tutor
	 */

	public ScheduleManager(Tutor tutor) {
		this.scheduledLessons = new ArrayList<>();
		this.tutor = tutor;
		scheduleManagers.put(tutor.getUsername(), this);
	}

	/**
	 * 
	 * @return the static field that keeps a mapping from tutor's user name to their
	 *         schedule manager
	 */
	public static HashMap<String, ScheduleManager> getScheduleManagers() {
		return scheduleManagers;
	}

	/**
	 * 
	 * @return the list of scheduled lessons of the tutor
	 */
	public List<ScheduledLesson> getScheduledLessonsOfTutor() {
		return scheduledLessons;
	}

	/**
	 * 
	 * This method creates a scheduled lesson object with the given arguments if the
	 * tutor is available.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param student
	 * @param course
	 * @return true if the tutor is available, false otherwise
	 */
	public boolean scheduleLesson(LocalTime startTime, LocalTime endTime, Student student, Course course) {
		if (!isTimeAvailable(startTime)) {
			return false;
		}
		var scheduledLesson = new ScheduledLesson(startTime, endTime, student, tutor, course);
		scheduledLessons.add(scheduledLesson);
		student.addSession(scheduledLesson);
		return true;
	}

	/**
	 * This method sorts the scheduled lessons list using the overridden
	 * collections.sort method().
	 * 
	 */
	public void sortScheduledLessons() {
		Collections.sort(scheduledLessons);
	}

	/**
	 * 
	 * @param startTime
	 * @return true if the given start time is available for this schedule manager's
	 *         tutor, false otherwise
	 */
	public boolean isTimeAvailable(LocalTime startTime) {
		sortScheduledLessons();
		for (ScheduledLesson scheduledLesson : scheduledLessons) {
			if (scheduledLesson == null) {
				continue;
			}
			if (scheduledLesson.getStartTime().getHour() == startTime.getHour()) {
				return false;
			}
		}
		return true;

	}

	/**
	 * 
	 * This method returns the available start time's hour of the tutor as an
	 * integer list.
	 * 
	 * @return a list of integers that represent the start time's hour
	 * 
	 */
	public List<Integer> getAvailableTimes() {
		List<Integer> availableTimes = new ArrayList<>();
		availableTimes.addAll(getStartTimes());
		for (ScheduledLesson scheduledLesson : scheduledLessons) {
			if (scheduledLesson == null) {
				continue;
			}
			int lessonStart = scheduledLesson.getStartTime().getHour();
			availableTimes.remove(availableTimes.indexOf(lessonStart));
		}

		return availableTimes;
	}

	/**
	 * 
	 * This method returns start times of all the sessions the tutor holds in a list
	 * of integers.
	 * 
	 * @return a list of integers that represent the start time's hour
	 * 
	 */
	public List<Integer> getStartTimes() {
		if (startTimes == null) {
			startTimes = new ArrayList<>();
			for (int i = 8; i < 12; i++) {
				startTimes.add(i);
			}
			for (int i = 13; i < 21; i++) {
				startTimes.add(i);
			}
		}
		return startTimes;
	}

	/**
	 * This static method is a helper for separating the given student's scheduled
	 * lessons from all the scheduled lesson objects.
	 * 
	 * @param student
	 * @return list of all the scheduled lesson objects of the given student
	 * 
	 */
	public static List<ScheduledLesson> getscheduledLessonsOfStudent(Student student) {
		var scheduleManagers = getScheduleManagers();
		var scheduledLessonsOfStudent = new ArrayList<ScheduledLesson>();

		for (ScheduleManager scheduleManager : scheduleManagers.values()) {
			var sessions = scheduleManager.getScheduledLessonsOfTutor();
			for (ScheduledLesson scheduledLesson : sessions) {
				if (!(scheduledLesson.getStudent().getUsername().equals(student.getUsername()))) {
					continue;
				}
				scheduledLessonsOfStudent.add(scheduledLesson);

			}

		}
		Collections.sort(scheduledLessonsOfStudent);
		return scheduledLessonsOfStudent;

	}

}
