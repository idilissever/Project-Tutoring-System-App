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
import user.Student;
import user.StudentContainer;

/**
 * One of the panels that is used by the Administrators. Used for selecting a
 * student to display for any course. Has an administrator object in its fields
 * for easy return to the logged in Administrator's panel.
 * 
 * @author idilissever
 *
 */
public class AdministratorPanelStudents extends JPanel {

	MainFrame frame;
	Administrator administrator;
	private JLabel titleLabel = new JLabel("Please select the student you would like to display.");
	private JButton cancelButton = new JButton("Cancel");
	private JButton displayStudentButton = new JButton("Display Student");
	private HashMap<String, JComboBox<String>> courseComboMap = new HashMap<>();

	/**
	 * Creates an AdministratorPanelStudents for the given MainFrame and
	 * Administrator objects.
	 * 
	 * @param frame
	 * @param administrator
	 */
	public AdministratorPanelStudents(MainFrame frame, Administrator administrator) {
		this.frame = frame;
		this.administrator = administrator;

		setLayout(new GridLayout(20, 2));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(titleLabel);
		add(new JLabel());

		var courses = CourseContainer.getInstance().getAllCourses();

		for (Course course : courses.values()) {
			var students = course.getStudents();
			JComboBox<String> studentsBox = new JComboBox<>();
			studentsBox.addItem("Select Student");
			for (Student student : students) {
				studentsBox.addItem(student.getUsername());
			}
			studentsBox.setSelectedItem("Select Student");
			courseComboMap.put(course.getCourseId(), studentsBox);
			add(new JLabel(course.getName() + " | " + course.getCourseId()));
			add(studentsBox);
		}

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanel(administrator);
			}
		});

		displayStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String courseId : courseComboMap.keySet()) {
					var comboStudentBox = courseComboMap.get(courseId);
					var course = CourseContainer.getInstance().getAllCourses().get(courseId);

					if (comboStudentBox.getSelectedItem() == "Select Student") {
						continue;
					}

					var student = StudentContainer.getInstance().getAllStudents()
							.get(String.valueOf(comboStudentBox.getSelectedItem()));

					frame.openAdministratorPanelStudent(administrator, course, student);
				}

			}
		});

		add(cancelButton);
		add(displayStudentButton);
		frame.add(this);
		frame.setVisible(true);

	}
}
