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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import course.Course;
import course.CourseContainer;
import schedule.ScheduleManager;
import schedule.ScheduledLesson;
import user.Student;
import user.StudentContainer;
import user.Tutor;

/**
 * One of the panels that is used by the Tutors. Used for selecting a student to
 * display for any course. Has an tutor object in its fields for easy return to
 * the logged in Tutor's panel.
 * 
 * @author idilissever
 *
 */
public class TutorPanelCourses extends JPanel {

	private MainFrame frame;
	private Tutor tutor;
	private JLabel titleLabel = new JLabel("Please select the student you would like to display.");
	private JButton cancelButton = new JButton("Cancel");
	private JButton displayStudentButton = new JButton("Display Student");
	private JTextArea scheduledSessionsTextArea = new JTextArea();
	private HashMap<String, JComboBox<String>> courseComboMap = new HashMap<>();

	/**
	 * Creates a TutorPanelCourses for the given Mainframe and Tutor object.
	 * 
	 * @param frame
	 * @param tutor
	 */
	public TutorPanelCourses(MainFrame frame, Tutor tutor) {
		this.frame = frame;
		this.tutor = tutor;
		setLayout(new GridLayout(20, 2));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(titleLabel);
		add(new JLabel());

		ScheduleManager scheduleManager = ScheduleManager.getScheduleManagers().get(tutor.getUsername());

		var courses = tutor.getCourses();

		for (Course course : courses) {
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

		var scheduledLessons = scheduleManager.getScheduledLessonsOfTutor();
		var scheduledLessonString = "";

		int numberOfSessions = 0;
		for (ScheduledLesson scheduledLesson : scheduledLessons) {
			scheduledLessonString += "Scheduled lesson at " + scheduledLesson.getStartTime().toString() + "-"
					+ scheduledLesson.getEndTime().toString() + " with " + scheduledLesson.getStudent().getName()
					+ " for course " + scheduledLesson.getCourse().getCourseId() + "\n";
			numberOfSessions++;
		}

		scheduledSessionsTextArea.setText(scheduledLessonString);
		scheduledSessionsTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(scheduledSessionsTextArea);
		revalidate();
		repaint();

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openTutorPanel(tutor);
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
					frame.openTutorPanelStudent(tutor, student, course);
				}

			}
		});

		add(scrollPane);
		add(cancelButton);
		add(displayStudentButton);
		frame.add(this);
		frame.setVisible(true);

	}

}
