package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import course.Course;
import schedule.ScheduleManager;
import user.Student;
import user.Tutor;

/**
 * One of the panels that is used by the Students. Used for scheduling lessons
 * for the given course with the given tutor. Has a student object in its fields
 * for easy return to the logged in Student's StudentPanelCourses panel.
 * 
 * @author idilissever
 *
 */
public class StudentPanelSchedule extends JPanel {

	private MainFrame frame;
	private Student student;
	private Tutor tutor;
	private Course course;
	private JLabel titleLabel = new JLabel("Please select the session you would like to attend.");
	private JTextField plannedSessionField = new JTextField();
	private JComboBox<String> availableSessionsBox = new JComboBox<String>();
	private JButton scheduleButton = new JButton("Schedule Session");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Creates a StudentPanelSchedule with the given MainFrame, Student, Course and
	 * Tutor objects passed as arguments.
	 * 
	 * @param frame
	 * @param student
	 * @param course
	 * @param tutor
	 */
	public StudentPanelSchedule(MainFrame frame, Student student, Course course, Tutor tutor) {
		this.frame = frame;
		this.student = student;
		this.course = course;
		this.tutor = tutor;

		setLayout(new GridLayout(8, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		var scheduleManager = ScheduleManager.getScheduleManagers().get(tutor.getUsername());
		var availableTimes = scheduleManager.getAvailableTimes();

		availableSessionsBox.addItem("Select session.");

		for (Integer startTime : availableTimes) {
			availableSessionsBox.addItem(startTime + ":00");
		}

		plannedSessionField.setText(
				"Planned Session with " + tutor.getName() + " for course " + course.getName() + "on 15th of January.");
		plannedSessionField.setEditable(false);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openStudentPanelCourses(student);
			}
		});

		availableSessionsBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				plannedSessionField.setText("Planned Session with " + tutor.getName() + " for course "
						+ course.getName() + " on 15th of January at " + availableSessionsBox.getSelectedItem());
				revalidate();
				repaint();
			}
		});

		scheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedSession = (String) availableSessionsBox.getSelectedItem();
				int index = selectedSession.indexOf(":");
				var hour = Integer.valueOf(selectedSession.substring(0, index));
				LocalTime selectedTime = LocalTime.of(hour, 00);
				LocalTime endTime = LocalTime.of(hour + 1, 00);

				scheduleManager.scheduleLesson(selectedTime, endTime, student, course);
				frame.openStudentPanelCourses(student);

			}
		});

		add(titleLabel);
		add(availableSessionsBox);
		add(plannedSessionField);
		add(scheduleButton);
		add(cancelButton);
		frame.add(this);
		frame.setVisible(true);
		repaint();
		revalidate();

	}

}
