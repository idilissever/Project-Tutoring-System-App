package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import course.Course;
import course.CourseContainer;
import user.TutorContainer;
import user.Tutor.TutorType;

/**
 * Panel displayed if the user presses the button Register as Tutor. Used for
 * taking the new Tutor's information and creating a new Tutor.
 * 
 * @author idilissever
 *
 */
public class TutorRegisterPanel extends JPanel {
	private MainFrame frame;
	private JLabel nameLabel = new JLabel("\t\tName and Surname:");
	private JTextField nameField = new JTextField();
	private JLabel usernameLabel = new JLabel("\t\tUsername:");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("\t\tPassword:");
	private JTextField passwordField = new JTextField();
	private JLabel balanceLabel = new JLabel("\t\tBalance:");
	private JTextField balanceField = new JTextField();
	private JLabel tutoringCostLabel = new JLabel("\t\tTutoring Cost: (please enter a value greater than 700)");
	private JTextField tutoringCostField = new JTextField();
	private JLabel percentageCutLabel = new JLabel("\t\tPercentage Cut: (please enter a value lower than 10)");
	private JTextField percentageCutField = new JTextField();
	private JLabel tutorTypeLabel = new JLabel("\t\tTutor Type:");
	private JComboBox<String> tutorTypeComboBox = new JComboBox<String>(new String[] { "A_LEVEL", "B_LEVEL" });

	private JLabel coursesLabel = new JLabel("\t\tCourses:");
	private JTextArea coursesArea = new JTextArea();

	private JComboBox<String> comboCoursesBox;
	private JButton saveButton = new JButton("Register");
	private JButton cancelButton = new JButton("Cancel");
	private JButton addCourseButton = new JButton("Add Selected Course");
	private List<Course> courses = new ArrayList<>();
	private JButton deleteButton = new JButton("New Register Form");
	/**
	 * Creates a new TutorRegisterPanel.
	 * @param frame
	 */
	public TutorRegisterPanel(MainFrame frame) {
		this.frame = frame;

		setSize(400, 400);
		setVisible(true);
		setLayout(new GridLayout(15, 2));

		coursesArea.setEditable(false);

		var tutorableCourses = CourseContainer.getInstance().getBeginnerCourses().keySet();
		var tutorableCoursesArray = new String[tutorableCourses.size()];

		int i = 0;
		for (String courseString : tutorableCourses) {
			tutorableCoursesArray[i] = courseString;
			i++;
		}

		comboCoursesBox = new JComboBox<String>(tutorableCoursesArray);

		tutorTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (String.valueOf(tutorTypeComboBox.getSelectedItem()) == "A_LEVEL") {
					var tutorableCourses = CourseContainer.getInstance().getAllCourses().keySet();

					comboCoursesBox.removeAllItems();

					for (String courseString : tutorableCourses) {
						comboCoursesBox.addItem(courseString);
					}

					tutoringCostLabel.setText("\t\tTutoring Cost: (please enter a value greater than 700)");
					percentageCutLabel.setText("\t\tPercentage Cut: (please enter a value lower than 10)");

				} else if (String.valueOf(tutorTypeComboBox.getSelectedItem()) == "B_LEVEL") {
					var tutorableCourses = CourseContainer.getInstance().getBeginnerCourses().keySet();
					comboCoursesBox.removeAllItems();

					for (String courseString : tutorableCourses) {
						comboCoursesBox.addItem(courseString);
					}

					tutoringCostLabel.setText("\t\tTutoring Cost: (please enter a value lower than 1000)");
					percentageCutLabel.setText("\t\tPercentage Cut: (please enter a value lower than 15)");

				}
				revalidate();
				repaint();
			}

		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var tutorContainer = TutorContainer.getInstance();
				String name = nameField.getText();
				String username = usernameField.getText();
				String password = passwordField.getText();
				double balance = Double.parseDouble(balanceField.getText());
				double tutoringCost = Double.parseDouble(tutoringCostField.getText());
				double percentageCut = Double.parseDouble(percentageCutField.getText());
				String tutorTypeString = String.valueOf(tutorTypeComboBox.getSelectedItem());

				TutorType tutorType = null;

				if (tutorTypeString.equals("A_LEVEL")) {
					tutorType = TutorType.A_LEVEL;
				} else if (tutorTypeString.equals("B_LEVEL")) {
					tutorType = TutorType.B_LEVEL;
				}
				var courseList = courses;

				var tutor = Register.RegisterTutor(name, username, password, tutoringCost, percentageCut, tutorType,
						courseList);

				frame.openTutorPanel(tutor);

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openLoginPanel();

			}
		});

		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var courseContainer = CourseContainer.getInstance();
				var allCourses = courseContainer.getAllCourses();
				var courseIdString = String.valueOf(comboCoursesBox.getSelectedItem());
				Course courseToAdd = allCourses.get(courseIdString);
				if (!courses.contains(courseToAdd)) {
					courses.add(courseToAdd);
				}

				var coursesString = " | ";
				int i = 0;
				for (Course course : courses) {
					if (i != 0 && i % 5 == 0) {
						coursesString += "\n";
					}
					coursesString += course.getCourseId() + " | ";
					i++;
				}

				coursesArea.setText(coursesString);
				revalidate();
				repaint();

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openTutorRegisterPanel();

			}
		});

		add(tutorTypeLabel);
		add(tutorTypeComboBox);
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

		add(coursesLabel);
		add(coursesArea);
		add(comboCoursesBox);
		add(addCourseButton);
		add(cancelButton);
		add(saveButton);
		add(deleteButton);

	}

}
