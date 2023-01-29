package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import course.Course;
import course.CourseContainer;
import user.Administrator;

/**
 * One of the panels that is used by the Administrators. Used for selecting a
 * course to display by the logged in Administrator. Has an administrator object
 * in its fields for easy return to the logged in Administrator's panel.
 * 
 * @author idilissever
 *
 */
public class AdministratorPanelCourses extends JPanel {

	private MainFrame frame;
	private Administrator administrator;
	private JLabel titleLabel = new JLabel("Please select the course you would like to display.");
	private JComboBox<String> coursesBox = new JComboBox<String>();
	private JButton displayCourseButton = new JButton("Display Course");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Creates an AdministratorPanelCourses panel with the given Administrator and
	 * MainFrame objects as arguments.
	 * 
	 * @param frame
	 * @param administrator
	 */
	public AdministratorPanelCourses(MainFrame frame, Administrator administrator) {
		this.frame = frame;
		this.administrator = administrator;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridLayout(12, 2));
		setVisible(true);

		coursesBox.addItem("Select Course");
		var allCourses = CourseContainer.getInstance().getAllCourses();

		for (String courseId : allCourses.keySet()) {
			coursesBox.addItem(courseId);
		}

		displayCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var selectedCourseString = String.valueOf(coursesBox.getSelectedItem());
				if (selectedCourseString.equals("Select Course")) {
					return;
				}

				Course selectedCourse = CourseContainer.getInstance().getAllCourses().get(selectedCourseString);
				frame.openCoursePanel(administrator, selectedCourse);

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanel(administrator);

			}
		});

		add(titleLabel);
		add(new JLabel());
		add(coursesBox);
		add(displayCourseButton);
		add(cancelButton);

		frame.add(this);
		frame.setVisible(true);

	}
}
