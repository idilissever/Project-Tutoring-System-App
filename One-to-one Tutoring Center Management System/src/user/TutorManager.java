package user;

import course.*;

/**
 * This class uses a Singleton pattern for making sure that there will be only
 * one instance of this class.
 * 
 * @author idilissever
 *
 */
public class TutorManager {

	private static TutorManager instance;

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private TutorManager() {

	}

	/**
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of tutor manager class.
	 * 
	 * @return the only instance of Tutor Manager class
	 */
	public static TutorManager getInstance() {
		if (instance == null) {
			instance = new TutorManager();
		}
		return instance;
	}

	/**
	 * Checks if the tutor can teach advanced lessons.
	 * 
	 * @param tutor
	 * @return true, if tutor can teach advanced lessons, false otherwise.
	 */
	public boolean canTutorAdvanced(Tutor tutor) {
		return tutor.getTutorType() == Tutor.TutorType.A_LEVEL;
	}

	/**
	 * This method adds the given course to the Tutor's courses field and adds the
	 * tutor to the given Course's tutors field if tutor can teach the given course.
	 * 
	 * @param tutor
	 * @param course
	 * @return true, if tutor can teach the given course, false otherwise.
	 */
	public boolean addCourse(Tutor tutor, Course course) {
		var courseManager = CourseManager.getInstance();
		if (!canTutorAdvanced(tutor) && course instanceof AdvancedCourse) {
			return false;
		}
		tutor.getCourses().add(course);
		courseManager.addTutor(course, tutor);
		return true;

	}

	/**
	 * This method helps the tutor gain from a lesson. After the system's monetary
	 * cut is subtracted, tutor's monetary balance is updated.
	 * 
	 * @param tutor
	 */
	public void gainFromSession(Tutor tutor) {
		var recentGain = tutor.getTutoringCost() - tutor.getTutoringCost() * tutor.getPercentageCut() * 0.01;
		tutor.setMonateryBalance(tutor.getMonateryBalance() + recentGain);
	}

}
