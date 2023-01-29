package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import course.Course;
import course.CourseContainer;
import user.Administrator;
import user.Tutor;
import user.TutorManager;

/**
 * One of the panels that is used by the Administrators. Panel of tutor for the
 * given course from the view of Administrator. Has an administrator object in
 * its fields for easy return to the logged in Administrator's
 * AdministratorPanelTutors panel.
 * 
 * @author idilissever
 *
 */
public class AdministratorPanelTutor extends JPanel {
	private MainFrame frame;
	private Administrator administrator;
	private Course course;
	private Tutor tutor;
	private JLabel nameLabel = new JLabel("\t\tName:");
	private JTextField nameField = new JTextField();
	private JLabel usernameLabel = new JLabel("\t\tUsername:");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("\t\tPassword:");
	private JTextField passwordField = new JTextField();
	private JLabel balanceLabel = new JLabel("\t\tBalance:");
	private JTextField balanceField = new JTextField();
	// Don't forget to add image
	private JLabel tutoringCostLabel = new JLabel("\t\tTutoring Cost:");
	private JTextField tutoringCostField = new JTextField();
	private JLabel percentageCutLabel = new JLabel("\t\tPercentage Cut:");
	private JTextField percentageCutField = new JTextField();
	private JLabel tutorTypeLabel = new JLabel("\t\tTutor Type:");
	private JTextField tutorTypeField = new JTextField();
	private JLabel coursesLabel = new JLabel("\t\tCourses:");
	private JTextArea coursesArea = new JTextArea();

	private JButton saveButton = new JButton("Save");
	private JButton editButton = new JButton("Edit");
	private JButton cancelButton = new JButton("Cancel");
	private JButton addCourseButton = new JButton("Add Course");
	private JComboBox<String> comboCoursesBox;

	/**
	 * Creates an AdministratorPanelTutor for the given MainFrame, Administrator,
	 * Course and Tutor objects passed as arguments.
	 * 
	 * @param frame
	 * @param administrator
	 * @param course
	 * @param tutor
	 */
	public AdministratorPanelTutor(MainFrame frame, Administrator administrator, Course course, Tutor tutor) {
		this.frame = frame;
		this.administrator = administrator;
		this.course = course;
		this.tutor = tutor;

		setLayout(new GridLayout(15, 2));

		var courses = "";
		int j = 0;
		for (Course courseOfTutor : tutor.getCourses()) {
			if (j != 0 && j % 5 == 0) {
				courses += "\n";
			}
			courses += " | " + courseOfTutor.getCourseId() + " | ";
			j++;
		}

		var tutorableCourses = CourseContainer.getInstance().getTutorableCourses(tutor).keySet();
		var tutorableCoursesArray = new String[tutorableCourses.size() + 1];
		tutorableCoursesArray[0] = "Do not add any new courses!";

		int i = 1;
		for (String courseString : tutorableCourses) {
			tutorableCoursesArray[i] = courseString;
			i++;
		}
		comboCoursesBox = new JComboBox<String>(tutorableCoursesArray);

		nameField.setText(tutor.getName());
		usernameField.setText(tutor.getUsername());
		passwordField.setText(tutor.getPassword());
		balanceField.setText(String.valueOf(tutor.getMonateryBalance()));
		tutoringCostField.setText(String.valueOf(tutor.getTutoringCost()));
		percentageCutField.setText(String.valueOf(tutor.getPercentageCut()));
		tutorTypeField.setText(String.valueOf(tutor.getTutorType()));

		coursesArea.setText(courses);

		nameField.setEditable(false);
		usernameField.setEditable(false);
		passwordField.setEditable(false);
		balanceField.setEditable(false);
		tutoringCostField.setEditable(false);
		percentageCutField.setEditable(false);
		tutorTypeField.setEditable(false);
		coursesArea.setEditable(false);

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tutor.setName(nameField.getText());
				tutor.setUsername(usernameField.getText());
				tutor.setPassword(passwordField.getText());
				tutor.setMonateryBalance(Double.parseDouble(balanceField.getText()));
				tutor.setTutoringCost(Double.parseDouble(tutoringCostField.getText()));
				tutor.setPercentageCut(Double.parseDouble(percentageCutField.getText()));

				nameField.setEditable(false);
				usernameField.setEditable(false);
				passwordField.setEditable(false);
				balanceField.setEditable(false);
				tutoringCostField.setEditable(false);
				percentageCutField.setEditable(false);
				tutorTypeField.setEditable(false);

			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.setEditable(true);
				usernameField.setEditable(true);
				passwordField.setEditable(true);
				balanceField.setEditable(true);
				tutoringCostField.setEditable(true);
				percentageCutField.setEditable(true);

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanelTutors(administrator);
			}
		});

		var tutorManager = TutorManager.getInstance();
		var courseContainer = CourseContainer.getInstance();

		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboCoursesBox.getSelectedItem() == "Do not add any new courses!") {
					return;
				}
				Course courseToAdd = courseContainer.getAllCourses().get(comboCoursesBox.getSelectedItem());
				tutorManager.addCourse(tutor, courseToAdd);

				var courses = "";
				for (Course course : tutor.getCourses()) {
					courses += " | " + course.getCourseId() + " | ";
				}
				coursesArea.setText(courses);
				comboCoursesBox.removeItem(courseToAdd.getCourseId());

				revalidate();

			}
		});

		add(nameLabel);
		add(nameField);
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(balanceLabel);
		add(balanceField);
		add(tutoringCostLabel);
		add(tutoringCostField);
		add(percentageCutLabel);
		add(percentageCutField);
		add(tutorTypeLabel);
		add(tutorTypeField);
		add(coursesLabel);
		add(coursesArea);
		add(saveButton);
		add(editButton);
		add(addCourseButton);
		add(comboCoursesBox);
		add(cancelButton);

		frame.add(this);
		frame.setVisible(true);
		repaint();

	}

}
