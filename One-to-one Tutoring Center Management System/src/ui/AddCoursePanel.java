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
 * 
 * One of the panels that is used by the Administrators. Used for adding new
 * Courses to the system. Has an administrator object in its fields for easy
 * return to the logged in Administrator's panel.
 * 
 * @author idilissever
 *
 */
public class AddCoursePanel extends JPanel {

	private MainFrame frame;
	private Administrator administrator;
	private JLabel courseTypeLabel = new JLabel("Course Type:");
	private JComboBox<String> courseTypeBox = new JComboBox<String>();

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

	private JComboBox<String> prerequisitesComboBox = new JComboBox<String>();
	private JButton addPrerequisiteButton = new JButton("Add Prerequisite");
	private JComboBox<String> tutorsComboBox = new JComboBox<String>();
	private JButton addTutorButton = new JButton("Add Tutor");
	private JComboBox<String> equipmentComboBox = new JComboBox<String>();
	private JButton addEquipmentButton = new JButton("Add Equipment");

	private JButton saveCourseButton = new JButton("Save Course");
	private JButton cancelButton = new JButton("Cancel");

	private List<Tutor> tutors = new ArrayList<>();
	private List<Course> prerequisites = new ArrayList<>();
	private List<Equipment> equipments = new ArrayList<>();

	/**
	 * Creates an AddCoursePanel for the logged in Administrator with the given
	 * MainFrame and Administrator objects as arguments.
	 * 
	 * @param frame
	 * @param administrator
	 * 
	 */

	public AddCoursePanel(MainFrame frame, Administrator administrator) {
		var courseManager = CourseManager.getInstance();
		var courseContainer = CourseContainer.getInstance();
		var tutorContainer = TutorContainer.getInstance();
		var tutorManager = TutorManager.getInstance();
		var equipmentContainer = EquipmentContainer.getInstance();

		this.frame = frame;
		this.administrator = administrator;

		setLayout(new GridLayout(15, 2));
		setVisible(true);

		equipmentsArea.setEditable(false);
		prerequisitesArea.setEditable(false);
		tutorsArea.setEditable(false);

		courseTypeBox.addItem("Select Course Type");
		courseTypeBox.addItem("Beginner Course");
		courseTypeBox.addItem("Advanced Course");
		courseTypeBox.setSelectedItem("Select Course Type");

		equipmentComboBox.addItem("Select Equipment");
		for (String equipmentString : equipmentContainer.getAllEquipments().keySet()) {
			equipmentComboBox.addItem(equipmentString);
		}

		courseTypeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (courseTypeBox.getSelectedItem().equals("Beginner Course")) {

					prerequisitesComboBox.removeAllItems();
					prerequisitesComboBox.addItem("Select Prerequisite");
					var beginnerCourses = courseContainer.getBeginnerCourses();

					for (String courseId : beginnerCourses.keySet()) {
						prerequisitesComboBox.addItem(courseId);
					}

					tutorsComboBox.removeAllItems();
					tutorsComboBox.addItem("Select Tutor");
					var allTutors = tutorContainer.getAllTutors();

					for (String tutorUsername : allTutors.keySet()) {
						tutorsComboBox.addItem(tutorUsername);
					}

				} else if (courseTypeBox.getSelectedItem().equals("Advanced Course")) {

					prerequisitesComboBox.removeAllItems();
					prerequisitesComboBox.addItem("Select Prerequisite");
					var allCourses = courseContainer.getAllCourses();

					for (String courseId : allCourses.keySet()) {
						prerequisitesComboBox.addItem(courseId);
					}

					tutorsComboBox.removeAllItems();
					tutorsComboBox.addItem("Select Tutor");
					var advancedTutors = tutorContainer.getAdvancedTutors();

					for (String tutorUsername : advancedTutors.keySet()) {
						tutorsComboBox.addItem(tutorUsername);
					}

				}
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

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanel(administrator);
			}
		});

		saveCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String courseId = iDField.getText();
				List<Student> students = new ArrayList<>();
				List<Course> prerequisitesList = prerequisites;
				List<Equipment> equipmentsList = equipments;
				List<Tutor> tutorsList = tutors;

				Course course = null;
				if (String.valueOf(courseTypeBox.getSelectedItem()) == "Advanced Course") {
					course = new AdvancedCourse(name, courseId, students, prerequisitesList, equipmentsList,
							tutorsList);
				}
				if (String.valueOf(courseTypeBox.getSelectedItem()).equals("Beginner Course")) {
					course = new BeginnerCourse(name, courseId, students, prerequisitesList, equipmentsList,
							tutorsList);
				}

				courseContainer.addCourse(courseId, course);

				for (int i = 0; i < tutors.size(); i++) {
					var tutor = tutorsList.get(i);
					tutor.getCourses().add(course);
				}

				frame.openAdministratorPanel(administrator);

			}
		});

		add(courseTypeLabel);
		add(courseTypeBox);
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
		add(prerequisitesComboBox);
		add(addPrerequisiteButton);
		add(tutorsComboBox);
		add(addTutorButton);
		add(equipmentComboBox);
		add(addEquipmentButton);
		add(saveCourseButton);
		add(cancelButton);

		frame.add(this);
		frame.setVisible(true);
		repaint();
		revalidate();

	}

}
