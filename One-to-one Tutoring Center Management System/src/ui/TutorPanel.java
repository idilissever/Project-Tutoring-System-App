package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import course.Course;
import course.CourseContainer;
import user.Tutor;
import user.TutorManager;

/**
 * The panel displayed after the Tutor logs in. Panel has variety of features
 * and buttons for Tutors to engage. Tutors can display, modify their
 * information, etc.
 * 
 * @author idilissever
 *
 */
public class TutorPanel extends JPanel {
	private MainFrame frame;
	private Tutor tutor;
	private BufferedImage profilePicture;
	private JLabel nameLabel = new JLabel("\t\tName:");
	private JTextField nameField = new JTextField();
	private JLabel usernameLabel = new JLabel("\t\tUsername:");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("\t\tPassword:");
	private JTextField passwordField = new JTextField();
	private JLabel balanceLabel = new JLabel("\t\tBalance:");
	private JTextField balanceField = new JTextField();
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
	private JButton logOutButton = new JButton("Log-out");
	private JButton addCourseButton = new JButton("Add Course");
	private JComboBox<String> comboCoursesBox;
	private JButton displayCoursesButton = new JButton("Display Courses");

	/**
	 * Creates a TutorPanel with the give Tutor object passed as argument.
	 * 
	 * @param frame
	 * @param tutor
	 */
	public TutorPanel(MainFrame frame, Tutor tutor) {
		this.frame = frame;
		this.tutor = tutor;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(15, 2));

		var courses = " | ";
		int j = 0;
		for (Course course : tutor.getCourses()) {
			if (j != 0 && j % 5 == 0) {
				courses += "\n|";
			}
			courses += course.getCourseId() + " | ";
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

		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openLoginPanel();

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

				var courses = " | ";
				int j = 0;
				for (Course course : tutor.getCourses()) {
					if (j != 0 && j % 5 == 0) {
						courses += "\n|";
					}
					courses += course.getCourseId() + " | ";
				}
				coursesArea.setText(courses);
				comboCoursesBox.removeItem(courseToAdd.getCourseId());

				revalidate();

			}
		});

		displayCoursesButton.addActionListener(new ActionListener() {
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
		add(logOutButton);
		add(displayCoursesButton);

		frame.add(this);
		frame.setVisible(true);
		repaint();

	}

}
