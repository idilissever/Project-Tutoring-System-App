package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

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
import user.Tutor;
import user.TutorContainer;

/**
 * One of the panels that is used by the Students. Used for selecting a course
 * and tutor to make a scheduled lesson by the logged in Student. Has a Student
 * object in its fields for easy return to the logged in Student's panel.
 * 
 * @author idilissever
 *
 */
public class StudentPanelCourses extends JPanel {

	MainFrame frame;
	Student student;
	private HashMap<String, JComboBox<String>> courseComboBoxHashMap = new HashMap<>();
	private JLabel titleLabel = new JLabel(
			"Please select the tutor you would like to schedule lesson with for the required course.");
	private JButton scheduleLessonButton = new JButton("Schedule Lesson");
	private JButton cancelButton = new JButton("Cancel");
	private JTextArea scheduledSessionsTextArea = new JTextArea();

	/**
	 * Creates a StudentPanelCourses with the given MainFrame and Student objects
	 * passed as arguments.
	 * 
	 * @param frame
	 * @param student
	 */
	public StudentPanelCourses(MainFrame frame, Student student) {
		this.frame = frame;
		this.student = student;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(15, 2));

		add(titleLabel);
		add(new JLabel());

		var scheduledLessonString = "";

		var scheduledLessons = ScheduleManager.getscheduledLessonsOfStudent(student);
		int numberOfSessions = 0;
		for (ScheduledLesson scheduledLesson : scheduledLessons) {
			scheduledLessonString += "Scheduled lesson at " + scheduledLesson.getStartTime().toString() + "-"
					+ scheduledLesson.getEndTime().toString() + " with " + scheduledLesson.getTutor().getName()
					+ " for course " + scheduledLesson.getCourse().getCourseId() + "\n";
			numberOfSessions++;
		}

		scheduledSessionsTextArea.setRows(numberOfSessions);
		scheduledSessionsTextArea.setMinimumSize(new Dimension(500, 100));

		scheduledSessionsTextArea.setText(scheduledLessonString);
		scheduledSessionsTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(scheduledSessionsTextArea);

		revalidate();
		repaint();

		var courses = student.getRegisteredCourses();

		for (Course course : courses) {
			var tutors = course.getTutors();
			JComboBox<String> tutorsBox = new JComboBox<>();
			tutorsBox.addItem("Do not schedule session for this course.");
			for (Tutor tutor : tutors) {
				tutorsBox.addItem(tutor.getUsername());

			}
			tutorsBox.setSelectedItem("Do not schedule session for this course.");
			courseComboBoxHashMap.put(course.getCourseId(), tutorsBox);
			add(new JLabel(course.getName() + "|" + course.getCourseId()));
			add(tutorsBox);

		}

		scheduleLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String courseId : courseComboBoxHashMap.keySet()) {
					var comboTutorBox = courseComboBoxHashMap.get(courseId);
					var course = CourseContainer.getInstance().getAllCourses().get(courseId);

					if (comboTutorBox.getSelectedItem() == "Do not schedule session for this course.") {
						continue;
					}

					var tutor = TutorContainer.getInstance().getAllTutors().get(comboTutorBox.getSelectedItem());

					frame.openStudentPanelSchedule(student, course, tutor);

				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openStudentPanel(student);

			}
		});

		add(scrollPane);

		add(scheduleLessonButton);
		add(cancelButton);
		frame.add(this);
		frame.setVisible(true);
		repaint();
		revalidate();

	}

}
