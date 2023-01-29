package ui;

import java.awt.Dimension;
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
import course.CourseContainer;
import course.Equipment;
import user.Student;
import user.StudentContainer;
import user.StudentManager;

/**
 * The panel displayed after the Student logs in. Panel has variety of features
 * and buttons for Students to engage.
 * 
 * @author idilissever
 *
 */
public class StudentPanel extends JPanel {
	private MainFrame frame;
	private Student student;
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
	private JLabel equipmentsLabel = new JLabel("Equipments: ");
	private JTextField equipmentsField = new JTextField();
	private JButton saveButton = new JButton("Save");
	private JButton editButton = new JButton("Edit Information");
	private JButton logOutButton = new JButton("Log-out");
	private JButton enrollButton = new JButton("Enroll Course");
	private JLabel genderLabel = new JLabel("Gender: ");
	private JLabel totalDebtLabel = new JLabel("Total Debt: ");
	private JTextField totalDebtTextField = new JTextField();
	private JComboBox<String> genderComboBox;
	private JComboBox<String> comboEnrollableCoursesBox;
	private JButton payDebtButton = new JButton("Pay Expenses");
	private JButton scheduleLessonButton = new JButton("Schedule Lesson");

	/**
	 * Creates an StudentPanel with the given Student and MainFrame objects as
	 * arguments.
	 * 
	 * @param frame
	 * @param student
	 */
	public StudentPanel(MainFrame frame, Student student) {
		this.frame = frame;
		this.student = student;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(16, 2));

		var enrolledCourses = " | ";
		int i = 0;
		for (Course course : student.getRegisteredCourses()) {
			if (i != 0 && i % 5 == 0) {
				enrolledCourses += "\n";
			}
			enrolledCourses += course.getCourseId() + " | ";
			i++;
		}

		var passedCourses = " | ";
		int j = 0;
		for (Course course : student.getPassedCourses()) {
			if (j != 0 && j % 5 == 0) {
				passedCourses += "\n";
			}
			passedCourses += course.getCourseId() + " | ";
			j++;
		}

		var equipmentString = " | ";
		int k = 0;
		for (Equipment equipment : student.getEquipments()) {
			if (k != 0 && k % 5 == 0) {
				equipmentString += "\n";
			}
			equipmentString += equipment.getName() + " | ";
			k++;
		}

		var gendersString = new String[2];
		gendersString[0] = "FEMALE";
		gendersString[1] = "MALE";
		var genderComboBox = new JComboBox<>(gendersString);

		StudentManager.getInstance().calculateCost(student);

		nameField.setText(student.getName());
		usernameField.setText(student.getUsername());
		passwordField.setText(student.getPassword());
		TCField.setText(student.gettCKN());
		ageField.setText(String.valueOf(student.getAge()));
		studentIDField.setText(String.valueOf(student.getIdNumber()));
		balanceField.setText(String.valueOf(student.getMonateryBalance()));
		enrolledField.setText(enrolledCourses);
		enrolledField.setSize(new Dimension(400, 400));
		passedField.setText(passedCourses);
		passedField.setSize(new Dimension(400, 400));
		equipmentsField.setText(equipmentString);
		totalDebtTextField.setText(String.valueOf(student.getTotalDebt()));

		nameField.setEditable(false);
		usernameField.setEditable(false);
		passwordField.setEditable(false);
		TCField.setEditable(false);
		ageField.setEditable(false);
		studentIDField.setEditable(false);
		balanceField.setEditable(false);
		enrolledField.setEditable(false);
		passedField.setEditable(false);
		genderComboBox.setEnabled(false);
		totalDebtTextField.setEditable(false);

		var enrollableCourses = CourseContainer.getInstance().getEnrollableCourses(student).keySet();
		String[] coursesArray = new String[enrollableCourses.size() + 1];

		coursesArray[0] = "Do not enroll to any courses";
		int l = 1;
		for (String key : enrollableCourses) {
			coursesArray[l] = key;
			l++;
		}
		comboEnrollableCoursesBox = new JComboBox<String>(coursesArray);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				var studentContainer = StudentContainer.getInstance();
				var allStudents = studentContainer.getAllStudents();
				student.setName(nameField.getText());
				student.setUsername(usernameField.getText());
				student.setPassword(passwordField.getText());
				student.settCKN(TCField.getText());
				student.setAge(Integer.parseInt(ageField.getText()));
				student.setIdNumber(Integer.parseInt(studentIDField.getText()));
				student.setMonateryBalance(Double.parseDouble(balanceField.getText()));
				student.setGender((String) genderComboBox.getSelectedItem());

				if (!allStudents.containsKey(student.getUsername())) {
					allStudents.put(student.getUsername(), student);
				}

				nameField.setEditable(false);
				usernameField.setEditable(false);
				passwordField.setEditable(false);
				TCField.setEditable(false);
				ageField.setEditable(false);
				studentIDField.setEditable(false);
				balanceField.setEditable(false);
				enrolledField.setEditable(false);
				passedField.setEditable(false);
				genderComboBox.setEnabled(false);
			}
		});

		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var studentContainer = StudentContainer.getInstance();
				var allStudents = studentContainer.getAllStudents();
				allStudents.remove(student.getUsername());
				nameField.setEditable(true);
				usernameField.setEditable(true);
				passwordField.setEditable(true);
				TCField.setEditable(true);
				ageField.setEditable(true);
				studentIDField.setEditable(true);
				balanceField.setEditable(true);
				genderComboBox.setEnabled(true);
			}
		});

		logOutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.openLoginPanel();

			}
		});

		enrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboEnrollableCoursesBox.getSelectedItem() != "Do not enroll to any courses") {
					var studentManager = StudentManager.getInstance();
					var courseToAdd = CourseContainer.getInstance().getAllCourses()
							.get(comboEnrollableCoursesBox.getSelectedItem());
					if (student.getRegisteredCourses().contains(courseToAdd)) {
						return;
					}
					studentManager.addRegisteredCourses(student, courseToAdd);

					var enrolledCourses = "";
					for (Course course : student.getRegisteredCourses()) {
						enrolledCourses += " | " + course.getCourseId() + " | ";
					}

					enrolledField.setText(enrolledCourses);

					totalDebtTextField.setText(String.valueOf(student.getTotalDebt()));

					double totalCost = studentManager.calculateCost(student);
					totalDebtTextField.setText(String.valueOf(student.getTotalDebt()));
					revalidate();

				}

			}
		});

		payDebtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentManager.getInstance().payExpenses(student);
				totalDebtTextField.setText(String.valueOf(student.getTotalDebt()));
				balanceField.setText(String.valueOf(student.getMonateryBalance()));

				var equipmentString = "";
				int k = 0;
				for (Equipment equipment : student.getEquipments()) {
					if (k != 0 && k % 5 == 0) {
						equipmentString += "\n";
					}
					equipmentString += equipment.getName() + " | ";
					k++;
				}

				equipmentsField.setText(equipmentString);
				revalidate();
			}
		});

		scheduleLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openStudentPanelCourses(student);
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
		add(enrolledLabel);
		add(enrolledField);
		add(passedLabel);
		add(passedField);
		add(equipmentsLabel);
		add(equipmentsField);
		add(genderLabel);
		add(genderComboBox);
		add(totalDebtLabel);
		add(totalDebtTextField);
		add(comboEnrollableCoursesBox);
		add(enrollButton);

		add(payDebtButton);
		add(editButton);
		add(logOutButton);
		add(saveButton);
		add(scheduleLessonButton);

		frame.add(this);
		frame.setVisible(true);
		repaint();
		revalidate();
	}
}
