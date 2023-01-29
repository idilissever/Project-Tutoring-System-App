package user;

import java.util.HashMap;

import course.CourseContainer;
import course.CourseManager;
import user.Tutor.TutorType;

/**
 * This class generates all the tutors in the application. Uses a Singleton
 * pattern for making sure that there will be only one instance of this class.
 * 
 * @author idilissever
 *
 */
public class TutorContainer {

	private HashMap<String, Tutor> tutors = new HashMap<>();
	private static TutorContainer instance;
	private TutorManager tutorManager;
	private CourseContainer courseContainer;

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private TutorContainer() {
		CourseManager.getInstance();
		this.tutorManager = TutorManager.getInstance();
		this.courseContainer = CourseContainer.getInstance();

	}

	/**
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of tutor container class.
	 * 
	 * @return the only instance of this class.
	 */
	public static TutorContainer getInstance() {
		if (instance == null) {
			instance = new TutorContainer();
		}
		return instance;
	}

	/**
	 * This method creates the tutors in the system with courses and adds the
	 * created tutors to the field tutors.
	 */
	public void setTutors() {
		var tutor1 = new Tutor("Ender Okur", "enderokur", "123", "enter image URL here", 0.0, 1000, 5,
				TutorType.A_LEVEL);
		tutors.put(tutor1.getUsername(), tutor1);

		var allCourses = courseContainer.getAllCourses();

		tutorManager.addCourse(tutor1, allCourses.get("MATH106"));
		tutorManager.addCourse(tutor1, allCourses.get("MATH107"));
		tutorManager.addCourse(tutor1, allCourses.get("PHYS101"));
		tutorManager.addCourse(tutor1, allCourses.get("COMP100"));
		tutorManager.addCourse(tutor1, allCourses.get("INDR100"));
		tutorManager.addCourse(tutor1, allCourses.get("ENGR200"));
		tutorManager.addCourse(tutor1, allCourses.get("MATH203"));
		tutorManager.addCourse(tutor1, allCourses.get("PHYS102"));
		tutorManager.addCourse(tutor1, allCourses.get("MATH204"));
		tutorManager.addCourse(tutor1, allCourses.get("COMP132"));
		tutorManager.addCourse(tutor1, allCourses.get("INDR220"));
		tutorManager.addCourse(tutor1, allCourses.get("COMP304"));
		tutorManager.addCourse(tutor1, allCourses.get("COMP341"));

		var tutor2 = new Tutor("Burak Yıldırım", "burakyildirim", "123", "url", 0.0, 700, 15.0, TutorType.B_LEVEL);
		tutors.put(tutor2.getUsername(), tutor2);

		tutorManager.addCourse(tutor2, allCourses.get("MATH106"));
		tutorManager.addCourse(tutor2, allCourses.get("MATH107"));
		tutorManager.addCourse(tutor2, allCourses.get("PHYS101"));
		tutorManager.addCourse(tutor2, allCourses.get("COMP100"));
		tutorManager.addCourse(tutor2, allCourses.get("INDR100"));
		tutorManager.addCourse(tutor2, allCourses.get("ENGR200"));

		var tutor3 = new Tutor("Ege Erdem Özlü", "egeozlu", "123", "url", 0.0, 900.0, 10, TutorType.A_LEVEL);
		tutors.put(tutor3.getUsername(), tutor3);

		tutorManager.addCourse(tutor3, allCourses.get("MATH203"));
		tutorManager.addCourse(tutor3, allCourses.get("PHYS102"));
		tutorManager.addCourse(tutor3, allCourses.get("MATH204"));
		tutorManager.addCourse(tutor3, allCourses.get("COMP132"));
		tutorManager.addCourse(tutor3, allCourses.get("INDR220"));
		tutorManager.addCourse(tutor3, allCourses.get("COMP304"));
		tutorManager.addCourse(tutor3, allCourses.get("COMP341"));

		var tutor4 = new Tutor("Ceyda Özdeş", "ceydaozdes", "123", "url", 0, 700, 5, TutorType.B_LEVEL);
		tutors.put(tutor4.getUsername(), tutor4);

		tutorManager.addCourse(tutor4, allCourses.get("MATH106"));
		tutorManager.addCourse(tutor4, allCourses.get("MATH107"));
		tutorManager.addCourse(tutor4, allCourses.get("PHYS101"));
		tutorManager.addCourse(tutor4, allCourses.get("COMP100"));

		var tutor5 = new Tutor("Yiğit Pekçetin", "yigitpekcetin", "123", "url", 0, 800, 10, TutorType.A_LEVEL);
		tutors.put(tutor5.getUsername(), tutor5);

		tutorManager.addCourse(tutor5, allCourses.get("INDR100"));
		tutorManager.addCourse(tutor5, allCourses.get("ENGR200"));
		tutorManager.addCourse(tutor5, allCourses.get("MATH203"));
		tutorManager.addCourse(tutor5, allCourses.get("PHYS102"));
		tutorManager.addCourse(tutor5, allCourses.get("MATH204"));

		var tutor6 = new Tutor("Tuna Çimen", "tunacimen", "123", "url", 0, 1000, 10, TutorType.A_LEVEL);
		tutors.put(tutor6.getUsername(), tutor6);

		tutorManager.addCourse(tutor6, allCourses.get("COMP132"));
		tutorManager.addCourse(tutor6, allCourses.get("INDR220"));
		tutorManager.addCourse(tutor6, allCourses.get("COMP304"));
		tutorManager.addCourse(tutor6, allCourses.get("COMP341"));

	}

	public HashMap<String, Tutor> getAllTutors() {
		return tutors;
	}

	/**
	 * This method adds the given tutor to the field tutors with its tutor user
	 * name.
	 * 
	 * @param tutor
	 */
	public void addTutor(Tutor tutor) {
		tutors.put(tutor.getUsername(), tutor);

	}

	/**
	 * This method only returns the A_LEVEL type tutors.
	 * 
	 * @return a HashMap of A_LEVEL type tutors with tutor user names as keys.
	 */
	public HashMap<String, Tutor> getAdvancedTutors() {
		var advancedTutors = new HashMap<String, Tutor>();
		for (String tutorUsername : tutors.keySet()) {
			var tutor = tutors.get(tutorUsername);

			if (tutor.getTutorType() == TutorType.B_LEVEL) {
				continue;
			}
			advancedTutors.put(tutorUsername, tutor);
		}
		return advancedTutors;

	}

}
