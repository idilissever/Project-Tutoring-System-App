package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import course.CourseContainer;
import user.StudentContainer;
import user.Student.Gender;

/**
 * Panel displayed if the user presses the button Register as Student. Used for
 * taking the new Student's information and creating a new Student.
 * 
 * @author idilissever
 *
 */
public class StudentRegisterPanel extends JPanel {
	private JLabel labelName, labelUsername, labelPassword, labelTCKN, labelAge, labelBalance, labelIdNumber,
			labelGender;
	private JTextField fieldName, fieldUsername, fieldPassword, fieldTCKN, fieldAge, fieldBalance, fieldIdNumber;
	private JButton buttonSave, buttonCancel, buttonAddPassedCourse;
	private MainFrame frame;
	private JComboBox<String> comboGenderBox;
	private List<JComboBox<String>> passedCoursesComboBoxs = new ArrayList<>();
	/**
	 * Creates a new StudentRegisterPanel.
	 * @param frame
	 */
	public StudentRegisterPanel(MainFrame frame) {
		this.frame = frame;
		setSize(400, 400);
		setVisible(true);
		setLayout(new GridLayout(15, 2));

		labelName = new JLabel("\tName and Surname:");
		fieldName = new JTextField();

		labelUsername = new JLabel("\tUsername:");
		fieldUsername = new JTextField();

		labelPassword = new JLabel("\tPassword:");
		fieldPassword = new JTextField();

		labelTCKN = new JLabel("\tTCKN");
		fieldTCKN = new JTextField();

		labelAge = new JLabel("\tAge");
		fieldAge = new JTextField();

		labelIdNumber = new JLabel("\tID:");
		fieldIdNumber = new JTextField();

		labelBalance = new JLabel("\tMonatery Balance:");
		fieldBalance = new JTextField();

		labelGender = new JLabel("\tGender:");
		var gendersArray = new String[2];
		gendersArray[0] = "FEMALE";
		gendersArray[1] = "MALE";
		comboGenderBox = new JComboBox<>(gendersArray);

		var availableCourses = CourseContainer.getInstance().getAvailableCoursesForFirstRegistered().keySet();
		String[] coursesArray = new String[availableCourses.size() + 1];

		coursesArray[0] = "Select Course";
		int i = 1;
		for (String key : availableCourses) {
			coursesArray[i] = key;
			i++;

		}
		
		buttonAddPassedCourse = new JButton("Add Passed Courses");

		buttonSave = new JButton("Register");
		buttonCancel = new JButton("Cancel");

		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				var studentContainer = StudentContainer.getInstance();
				if (e.getSource() == buttonSave) {
					String name = fieldName.getText();
					String username = fieldUsername.getText();
					String password = fieldPassword.getText();
					String tCKN = fieldTCKN.getText();
					int age = Integer.valueOf(fieldAge.getText());
					int iD = Integer.valueOf(fieldIdNumber.getText());
					double monateryBalance = Double.parseDouble(fieldBalance.getText());
					String genderString = (String) comboGenderBox.getSelectedItem();

					List<String> passedCoursesString = new ArrayList<>();

					for (JComboBox jComboBox : passedCoursesComboBoxs) {
						var courseToAdd = (String) jComboBox.getSelectedItem();
						if (courseToAdd.equals("Select Course"))
							continue;

						if (passedCoursesString.contains(courseToAdd)) {
							continue;
						}
						passedCoursesString.add(courseToAdd);
					}

					var gender = Gender.FEMALE;

					if (genderString.equals("FEMALE")) {
						gender = Gender.FEMALE;
					} else if (genderString.equals("MALE")) {
						gender = Gender.MALE;
					}

					Register.RegisterStudent(name, username, password, tCKN, age, iD, monateryBalance, gender,
							passedCoursesString);
					var student = studentContainer.getAllStudents().get(username);
					frame.openStudentPanel(student);

				}

			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openLoginPanel();
			}
		});

		var selectedCourse = "";

		buttonAddPassedCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JComboBox<String> comboCoursesBox = new JComboBox<>(coursesArray);
				add(comboCoursesBox);
				passedCoursesComboBoxs.add(comboCoursesBox);
				revalidate();
			}
		});

		add(labelName);
		add(fieldName);
		add(labelUsername);
		add(fieldUsername);
		add(labelPassword);
		add(fieldPassword);
		add(labelTCKN);
		add(fieldTCKN);
		add(labelAge);
		add(fieldAge);
		add(labelIdNumber);
		add(fieldIdNumber);
		add(labelBalance);
		add(fieldBalance);
		add(labelGender);
		add(comboGenderBox);

		add(buttonSave);
		add(buttonCancel);
		add(buttonAddPassedCourse);

	}
}
