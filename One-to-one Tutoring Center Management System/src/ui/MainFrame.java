package ui;

import javax.swing.JFrame;
import course.Course;
import user.Administrator;
import user.Student;
import user.Tutor;

/**
 * This class is the main frame of the application. It controls what is
 * displayed on the screen at all times. All the panels are added on the
 * MainFrame and MainFrame is responsible for the transition between different
 * panels. That's why each of the panels have an instance of the MainFrame class
 * in their fields.
 * 
 * @author idilissever
 *
 */
public class MainFrame extends JFrame {
	/**
	 * Creates a MainFrame.
	 */
	public MainFrame() {
		setSize(1000, 400);

		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * Used for displaying the LoginPanel.
	 */
	public void openLoginPanel() {
		var content = getContentPane();

		content.removeAll();
		content.add(new LoginPanel(this));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying the StudentRegisterPanel.
	 */
	public void openStudentRegisterPanel() {
		var content = getContentPane();

		content.removeAll();
		content.add(new StudentRegisterPanel(this));
		content.repaint();
		revalidate();
	}

	/**
	 * Used for displaying the StudentPanel with the given Student passed as
	 * argument.
	 * 
	 * @param student
	 */
	public void openStudentPanel(Student student) {
		var content = getContentPane();
		content.removeAll();
		var studentPanel = new StudentPanel(this, student);
		content.add(studentPanel);
		content.repaint();
		revalidate();
	}

	/**
	 * Used for displaying the TutorPanel with the given Tutor passed as argument.
	 * 
	 * @param tutor
	 */
	public void openTutorPanel(Tutor tutor) {
		var content = getContentPane();

		content.removeAll();
		content.add(new TutorPanel(this, tutor));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying the TutorRegisterPanel.
	 */
	public void openTutorRegisterPanel() {
		var content = getContentPane();

		content.removeAll();
		content.add(new TutorRegisterPanel(this));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying the StudentPanelCourses with the given Student passed as
	 * argument.
	 * 
	 * @param student
	 */
	public void openStudentPanelCourses(Student student) {
		var content = getContentPane();

		content.removeAll();
		content.add(new StudentPanelCourses(this, student));
		content.repaint();
		revalidate();
	}

	/**
	 * Used for displaying StudentPanelSchedule with the given Student, Course and
	 * Tutor objects passed as argument.
	 * 
	 * @param student
	 * @param course
	 * @param tutor
	 */
	public void openStudentPanelSchedule(Student student, Course course, Tutor tutor) {
		var content = getContentPane();

		content.removeAll();
		content.add(new StudentPanelSchedule(this, student, course, tutor));
		content.repaint();
		revalidate();
	}

	/**
	 * Used for displaying TutorPanelStudent with the given Student, Course and
	 * Tutor objects passed as argument.
	 * 
	 * @param tutor
	 * @param student
	 * @param course
	 */
	public void openTutorPanelStudent(Tutor tutor, Student student, Course course) {
		var content = getContentPane();

		content.removeAll();
		content.add(new TutorPanelStudent(this, tutor, student, course));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying TutorPanelCourses with the given Tutor object passed as
	 * argument.
	 * 
	 * @param tutor
	 */
	public void openTutorPanelCourses(Tutor tutor) {
		var content = getContentPane();

		content.removeAll();
		content.add(new TutorPanelCourses(this, tutor));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying AdministratorPanel with the given Administrator object
	 * passed as argument.
	 * 
	 * @param admin
	 */
	public void openAdministratorPanel(Administrator admin) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorPanel(this, admin));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying StudentPanelSchedule with the given Student, Course and
	 * Tutor objects passed as argument.
	 * 
	 * @param administrator
	 */
	public void openAddCoursePanel(Administrator administrator) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AddCoursePanel(this, administrator));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying the AdministratorRegisterPanel.
	 */
	public void openAdministratorRegisterPanel() {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorRegisterPanel(this));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying CoursePanel with the given Administrator and Course
	 * objects passed as argument.
	 * 
	 * @param admin
	 * @param selectedCourse
	 */
	public void openCoursePanel(Administrator admin, Course selectedCourse) {
		var content = getContentPane();

		content.removeAll();
		content.add(new CoursePanel(this, admin, selectedCourse));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying AdministratorPanelCourses with the given Administrator
	 * object passed as argument.
	 * 
	 * @param administrator
	 */
	public void openAdministratorPanelCourses(Administrator administrator) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorPanelCourses(this, administrator));
		content.repaint();
		revalidate();
	}

	/**
	 * Used for displaying AdministratorPanelStudents with the given Administrator
	 * object passed as argument.
	 * 
	 * @param administrator
	 */
	public void openAdministratorPanelStudents(Administrator administrator) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorPanelStudents(this, administrator));
		content.repaint();
		revalidate();
	}

	/**
	 * Used for displaying AdministratorPanelStudent with the given Administrator,
	 * Student and Course objects passed as argument.
	 * 
	 * @param administrator
	 * @param course
	 * @param student
	 */
	public void openAdministratorPanelStudent(Administrator administrator, Course course, Student student) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorPanelStudent(this, administrator, course, student));
		content.repaint();
		revalidate();

	}

	/**
	 * Used for displaying AdministratorPanelTutors with the given Administrator
	 * object passed as argument.
	 * 
	 * @param administrator
	 */
	public void openAdministratorPanelTutors(Administrator administrator) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorPanelTutors(this, administrator));
		content.repaint();
		revalidate();

	}
	/**
	 * Used for displaying AdministratorPanelTutor with the given Administrator,
	 * Tutor and Course objects passed as argument.
	 * @param administrator
	 * @param course
	 * @param tutor
	 */
	public void openAdministratorPanelTutor(Administrator administrator, Course course, Tutor tutor) {
		var content = getContentPane();

		content.removeAll();
		content.add(new AdministratorPanelTutor(this, administrator, course, tutor));
		content.repaint();
		revalidate();
	}

}
