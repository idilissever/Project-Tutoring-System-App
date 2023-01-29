package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import course.Course;
import user.Student;
import user.StudentManager;
import user.Tutor;

/**
 * One of the panels that is used by the Tutors. Panel of student for the given
 * course from the view of Tutor. Has an tutor object in its fields for easy
 * return to the logged in Tutor's TutorPanelCourses panel.
 * 
 * @author idilissever
 *
 */
public class TutorPanelStudent extends JPanel {

	private MainFrame frame;
	private Student student;
	private Tutor tutor;
	private Course course;
	private JLabel nameLabel = new JLabel("Name:");
	private JTextField nameField = new JTextField();
	private JLabel usernameLabel = new JLabel("Username:");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password:");
	private JTextField passwordField = new JTextField();
	private JLabel TCLabel = new JLabel("TC:");
	private JTextField TCField = new JTextField();
	private JLabel ageLabel = new JLabel("Age:");
	private JTextField ageField = new JTextField();
	private JLabel studentIDLabel = new JLabel("Student ID:");
	private JTextField studentIDField = new JTextField();
	private JLabel balanceLabel = new JLabel("Balance:");
	private JTextField balanceField = new JTextField();
	private JLabel enrolledLabel = new JLabel("Enrolled Courses:");
	private JTextField enrolledField = new JTextField();
	private JLabel passedLabel = new JLabel("Passed Courses:");
	private JTextField passedField = new JTextField();
	private JLabel genderLabel = new JLabel("Gender: ");
	private JTextField genderTextField = new JTextField();
	private JLabel totalDebtLabel = new JLabel("Total Debt: ");
	private JTextField totalDebtTextField = new JTextField();
	private JButton cancelButton = new JButton("Cancel");
	private JButton setStudentStatusButton = new JButton("Set Student Status");

	/**
	 * Creates an TutorPanelStudent for the given MainFrame, Tutor, Course and
	 * Student objects passed as arguments.
	 * 
	 * @param frame
	 * @param tutor
	 * @param student
	 * @param course
	 */
	public TutorPanelStudent(MainFrame frame, Tutor tutor, Student student, Course course) {
		this.frame = frame;
		this.student = student;
		this.tutor = tutor;
		this.course = course;

		setLayout(new GridLayout(20, 2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		var passedCourses = " | ";
		int i = 0;
		for (Course course1 : student.getPassedCourses()) {
			if (i != 0 && i % 5 == 0) {
				passedCourses += "\n";
			}
			passedCourses += course1.getCourseId() + " | ";
			i++;
		}
		int j = 0;
		var enrolledCoursesString = " | ";
		for (Course course2 : student.getRegisteredCourses()) {
			if (j != 0 && j % 5 == 0) {
				enrolledCoursesString += "\n";
			}
			enrolledCoursesString += course2.getCourseId() + " | ";
			j++;
		}

		passedField.setText(passedCourses);
		enrolledField.setText(enrolledCoursesString);

		nameField.setText(student.getName());
		usernameField.setText(student.getUsername());
		passwordField.setText(student.getPassword());
		TCField.setText(student.gettCKN());
		ageField.setText(String.valueOf(student.getAge()));
		studentIDField.setText(String.valueOf(student.getIdNumber()));
		balanceField.setText(String.valueOf(student.getMonateryBalance()));
		totalDebtTextField.setText(String.valueOf(student.getTotalDebt()));
		genderTextField.setText(String.valueOf(student.getGender()));

		nameField.setEditable(false);
		usernameField.setEditable(false);
		passwordField.setEditable(false);
		TCField.setEditable(false);
		ageField.setEditable(false);
		studentIDField.setEditable(false);
		balanceField.setEditable(false);
		passedField.setEditable(false);
		totalDebtTextField.setEditable(false);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openTutorPanelCourses(tutor);
			}
		});

		add(nameLabel);
		add(nameField);
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(TCLabel);
		add(TCField);
		add(ageLabel);
		add(ageField);
		add(studentIDLabel);
		add(studentIDField);
		add(balanceLabel);
		add(balanceField);
		add(genderLabel);
		add(genderTextField);
		add(totalDebtLabel);
		add(totalDebtTextField);
		add(passedLabel);
		add(passedField);
		add(enrolledLabel);
		add(enrolledField);

		add(new JLabel(course.getName() + " | " + course.getCourseId() + " | Status:"));

		var comboStatusBox = new JComboBox<String>();
		comboStatusBox.addItem("DEFAULT");
		comboStatusBox.addItem("PASS");
		comboStatusBox.addItem("FAIL");
		var studentStatus = StudentManager.getInstance().getStudentStatus(student, course);
		comboStatusBox.setSelectedItem(studentStatus);
		add(comboStatusBox);

		var studentManager = StudentManager.getInstance();

		setStudentStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var statusString = String.valueOf(comboStatusBox.getSelectedItem());
				studentManager.setStudentStatus(student, course, statusString);
				comboStatusBox.setSelectedItem(statusString);

				var passedCourses = " | ";
				int i = 0;
				for (Course course1 : student.getPassedCourses()) {
					if (i != 0 && i % 5 == 0) {
						passedCourses += "\n";
					}
					passedCourses += course1.getCourseId() + " | ";
					i++;
				}

				int j = 0;
				var enrolledCoursesString = " | ";
				for (Course course2 : student.getRegisteredCourses()) {
					if (j != 0 && j % 5 == 0) {
						enrolledCoursesString += "\n";
					}
					enrolledCoursesString += course2.getCourseId() + " | ";
					j++;
				}

				passedField.setText(passedCourses);
				enrolledField.setText(enrolledCoursesString);

				revalidate();
				repaint();
			}
		});

		add(setStudentStatusButton);
		add(cancelButton);

	}

}
