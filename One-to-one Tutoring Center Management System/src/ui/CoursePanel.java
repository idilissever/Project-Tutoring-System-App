package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import course.AdvancedCourse;
import course.BeginnerCourse;
import course.Course;
import course.CourseContainer;
import course.CourseManager;
import course.Equipment;
import course.EquipmentContainer;
import user.Administrator;
import user.Student;
import user.Tutor;
import user.TutorContainer;
import user.TutorManager;

/**
 * One of the panels that is used by the Administrators. Panel of a course from
 * the view of Administrator. Has an administrator object in its fields for easy
 * return to the logged in Administrator's AdministratorPanelCourses.
 * 
 * @author idilissever
 *
 */
public class CoursePanel extends JPanel {

	private Course course;
	private MainFrame frame;
	private Administrator administrator;
	private JLabel courseTypeLabel = new JLabel("Course Type:");
	private JTextField courseTypeField = new JTextField();

	private JLabel nameLabel = new JLabel("Course Name:");
	private JTextField nameField = new JTextField();
	private JLabel iDLabel = new JLabel("Course ID:");
	private JTextField iDField = new JTextField();
	private JLabel prerequisitesLabel = new JLabel("Course Prerequisites:");
	private JTextArea prerequisitesArea = new JTextArea();
	private JLabel equipmentsLabel = new JLabel("Course Equipments:");
	private JTextArea equipmentsArea = new JTextArea();
	private JLabel tutorsLabel = new JLabel("Tutors:");
	private JTextArea tutorsArea = new JTextArea();
	private JLabel studentsLabel = new JLabel("Students: ");
	private JTextArea studentsArea = new JTextArea();

	private JComboBox<String> prerequisitesComboBox = new JComboBox<String>();
	private JButton addPrerequisiteButton = new JButton("Add Prerequisite");
	private JComboBox<String> tutorsComboBox = new JComboBox<String>();
	private JButton addTutorButton = new JButton("Add Tutor");
	private JComboBox<String> equipmentComboBox = new JComboBox<String>();
	private JButton addEquipmentButton = new JButton("Add Equipment");

	private JButton editCourseButton = new JButton("Edit Course Information");
	private JButton saveCourseButton = new JButton("Save Course");
	private JButton cancelButton = new JButton("Cancel");

	private List<Tutor> tutors;
	private List<Course> prerequisites;
	private List<Equipment> equipments;
	private List<Student> students;

	/**
	 * Creates an CoursePanel for the given MainFrame, Administrator and Course
	 * objects passed as arguments.
	 * 
	 * @param frame
	 * @param administrator
	 * @param course
	 */
	public CoursePanel(MainFrame frame, Administrator administrator, Course course) {
		this.frame = frame;
		this.administrator = administrator;
		this.course = course;
		this.prerequisites = course.getPrerequisites();
		this.tutors = course.getTutors();
		this.equipments = course.getEquipments();
		this.students = course.getStudents();

		var courseManager = CourseManager.getInstance();
		var courseContainer = CourseContainer.getInstance();
		var tutorContainer = TutorContainer.getInstance();
		var tutorManager = TutorManager.getInstance();
		var equipmentContainer = EquipmentContainer.getInstance();

		setLayout(new GridLayout(15, 2));
		setVisible(true);
		String type = "";
		if (course instanceof AdvancedCourse) {
			type += "Advanced Course";
			prerequisitesComboBox.addItem("Select Prerequisite");
			var allCourses = courseContainer.getAllCourses();

			for (String courseId : allCourses.keySet()) {
				prerequisitesComboBox.addItem(courseId);
			}

			tutorsComboBox.addItem("Select Tutor");
			var advancedTutors = tutorContainer.getAdvancedTutors();

			for (String tutorUsername : advancedTutors.keySet()) {
				tutorsComboBox.addItem(tutorUsername);
			}

		} else if (course instanceof BeginnerCourse) {
			type += "Beginner Course";
			prerequisitesComboBox.addItem("Select Prerequisite");
			var beginnerCourses = courseContainer.getBeginnerCourses();

			for (String courseId : beginnerCourses.keySet()) {
				prerequisitesComboBox.addItem(courseId);
			}

			tutorsComboBox.addItem("Select Tutor");
			var allTutors = tutorContainer.getAllTutors();

			for (String tutorUsername : allTutors.keySet()) {
				tutorsComboBox.addItem(tutorUsername);
			}
		}

		equipmentComboBox.addItem("Select Equipment");
		for (String equipmentString : equipmentContainer.getAllEquipments().keySet()) {
			equipmentComboBox.addItem(equipmentString);
		}

		courseTypeField.setText(type);
		nameField.setText(course.getName());
		iDField.setText(course.getCourseId());

		var coursesString = " | ";
		int i = 0;
		for (Course prerequisite : prerequisites) {
			if (i != 0 && i % 5 == 0) {
				coursesString += "\n";
			}
			coursesString += prerequisite.getCourseId() + " | ";
			i++;
		}
		prerequisitesArea.setText(coursesString);

		var equipmentsString = " | ";
		for (Equipment equipment : equipments) {
			equipmentsString += equipment.getName() + " | ";
		}
		equipmentsArea.setText(equipmentsString);

		var tutorsString = " | ";
		for (Tutor tutor : tutors) {
			tutorsString += tutor.getName() + " | ";
		}
		tutorsArea.setText(tutorsString);

		var studentsString = " | ";
		int j = 0;
		for (Student student : students) {
			if (j != 0 && j % 5 == 0) {
				studentsString += "\n";
			}
			studentsString += student.getName() + " | ";
			j++;
		}
		studentsArea.setText(studentsString);

		courseTypeField.setEditable(false);
		nameField.setEditable(false);
		iDField.setEditable(false);
		prerequisitesArea.setEditable(false);
		equipmentsArea.setEditable(false);
		tutorsArea.setEditable(false);
		studentsArea.setEditable(false);

		addPrerequisiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var selectedCourseString = String.valueOf(prerequisitesComboBox.getSelectedItem());
				if (selectedCourseString.equals("Select Prerequisite")) {
					return;
				}

				Course selectedCourse = courseContainer.getAllCourses().get(selectedCourseString);
				if (prerequisites.contains(selectedCourse)) {
					return;
				}

				prerequisites.add(selectedCourse);
				var coursesString = " | ";

				for (Course prerequisite : prerequisites) {
					coursesString += prerequisite.getCourseId() + " | ";
				}

				prerequisitesArea.setText(coursesString);

				revalidate();
				repaint();
			}
		});

		addTutorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var selectedTutorString = String.valueOf(tutorsComboBox.getSelectedItem());
				if (selectedTutorString == "Select Tutor") {
					return;
				}

				Tutor selectedTutor = tutorContainer.getAllTutors().get(selectedTutorString);
				if (tutors.contains(selectedTutor)) {
					return;
				}
				tutors.add(selectedTutor);

				var tutorsString = " | ";
				for (Tutor tutor : tutors) {
					tutorsString += tutor.getName() + " | ";
				}

				tutorsArea.setText(tutorsString);

				revalidate();
				repaint();

			}
		});

		addEquipmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var selectedEquipmentString = String.valueOf(equipmentComboBox.getSelectedItem());
				if (selectedEquipmentString.equals("Select Equipment")) {
					return;
				}
				Equipment selectedEquipment = equipmentContainer.getAllEquipments().get(selectedEquipmentString);
				if (equipments.contains(selectedEquipment)) {
					return;
				}

				equipments.add(selectedEquipment);

				var equipmentsString = " | ";

				for (Equipment equipment : equipments) {
					equipmentsString += equipment.getName() + " | ";
				}

				equipmentsArea.setText(equipmentsString);

				revalidate();
				repaint();

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanelCourses(administrator);
			}
		});

		editCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.setEditable(true);
				iDField.setEditable(true);
			}
		});

		saveCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var oldCourseID = course.getCourseId();
				var newCourseID = iDField.getText();
				var allCourses = CourseContainer.getInstance().getAllCourses();

				nameField.setEditable(false);
				iDField.setEditable(false);

				if (!oldCourseID.equals(newCourseID)) {
					allCourses.remove(oldCourseID);
					allCourses.put(newCourseID, course);
				}

				course.setCourseId(newCourseID);
				course.setName(nameField.getText());

			}
		});

		add(courseTypeLabel);
		add(courseTypeField);
		add(nameLabel);
		add(nameField);
		add(iDLabel);
		add(iDField);
		add(prerequisitesLabel);
		add(prerequisitesArea);
		add(equipmentsLabel);
		add(equipmentsArea);
		add(tutorsLabel);
		add(tutorsArea);
		add(studentsLabel);
		add(studentsArea);
		add(prerequisitesComboBox);
		add(addPrerequisiteButton);
		add(tutorsComboBox);
		add(addTutorButton);
		add(equipmentComboBox);
		add(addEquipmentButton);
		add(editCourseButton);
		add(saveCourseButton);
		add(cancelButton);

	}

}
