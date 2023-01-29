package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import course.Course;
import course.CourseContainer;
import user.Administrator;
import user.Tutor;
import user.TutorContainer;

/**
 * One of the panels that is used by the Administrators. Used for selecting a
 * tutor to display for any course. Has an administrator object in its fields
 * for easy return to the logged in Administrator's panel.
 * 
 * @author idilissever
 *
 */
public class AdministratorPanelTutors extends JPanel {
	MainFrame frame;
	Administrator administrator;
	private JLabel titleLabel = new JLabel("Please select the tutor you would like to display.");
	private JButton cancelButton = new JButton("Cancel");
	private JButton displayTutorButton = new JButton("Display Tutor");
	private HashMap<String, JComboBox<String>> courseComboMap = new HashMap<>();

	/**
	 * Creates an AdministratorPanelTutors for the given MainFrame and Administrator
	 * objects.
	 * 
	 * @param frame
	 * @param administrator
	 */
	public AdministratorPanelTutors(MainFrame frame, Administrator administrator) {
		this.frame = frame;
		this.administrator = administrator;

		setLayout(new GridLayout(20, 2));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(titleLabel);
		add(new JLabel());

		var courses = CourseContainer.getInstance().getAllCourses();

		for (Course course : courses.values()) {
			var tutors = course.getTutors();
			JComboBox<String> tutorsBox = new JComboBox<>();
			tutorsBox.addItem("Select Tutor");
			for (Tutor tutor : tutors) {
				tutorsBox.addItem(tutor.getUsername());
			}
			tutorsBox.setSelectedItem("Select Tutor");
			courseComboMap.put(course.getCourseId(), tutorsBox);
			add(new JLabel(course.getName() + " | " + course.getCourseId()));
			add(tutorsBox);
		}

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanel(administrator);
			}
		});

		displayTutorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String courseId : courseComboMap.keySet()) {
					var comboTutorBox = courseComboMap.get(courseId);

					var course = CourseContainer.getInstance().getAllCourses().get(courseId);

					if (comboTutorBox.getSelectedItem() == "Select Tutor") {
						continue;
					}

					var tutor = TutorContainer.getInstance().getAllTutors()
							.get(String.valueOf(comboTutorBox.getSelectedItem()));

					frame.openAdministratorPanelTutor(administrator, course, tutor);
				}

			}
		});

		add(cancelButton);
		add(displayTutorButton);
		frame.add(this);
		frame.setVisible(true);
	}
}
