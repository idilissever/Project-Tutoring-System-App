package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.TutoringCenterSystem;
import user.Administrator;
import user.AdministratorContainer;

/**
 * The panel displayed after the Administrator logs in. Panel has variety of
 * features and buttons for Administrators to engage.
 * 
 * @author idilissever
 *
 */
public class AdministratorPanel extends JPanel {

	private MainFrame frame;
	private Administrator administrator;
	private JLabel nameJLabel = new JLabel("Name and Surname");
	private JTextField nameField = new JTextField();
	private JLabel usernameLabel = new JLabel("Username: ");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password: ");
	private JTextField passwordField = new JTextField();
	private JLabel systemBalanceLabel = new JLabel("Monatery Balance of System:");
	private JTextField systemBalanceField = new JTextField();

	private JButton logoutButton = new JButton("Logout");
	private JButton editButton = new JButton("Edit Information");
	private JButton saveButton = new JButton("Save");
	private JButton displayTutorsButton = new JButton("Display Tutors");
	private JButton displayStudentButton = new JButton("Display Students");
	private JButton displayCoursesButton = new JButton("Display Courses");
	private JButton addCourseButton = new JButton("Add New Course");

	/**
	 * Creates an Administrator panel with the given Administrator and MainFrame
	 * objects as arguments.
	 * 
	 * @param frame
	 * @param administrator
	 */
	public AdministratorPanel(MainFrame frame, Administrator administrator) {
		this.frame = frame;
		this.administrator = administrator;

		setSize(400, 400);
		setVisible(true);
		setLayout(new GridLayout(15, 2));

		var system = TutoringCenterSystem.getInstance();
		HashMap<String, Administrator> allAdmins = AdministratorContainer.getInstance().getAllAdministrators();

		nameField.setText(administrator.getName());
		usernameField.setText(administrator.getUsername());
		passwordField.setText(administrator.getPassword());
		systemBalanceField.setText(String.valueOf(system.getMonateryBalance()));

		nameField.setEditable(false);
		usernameField.setEditable(false);
		passwordField.setEditable(false);
		systemBalanceField.setEditable(false);

		displayStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanelStudents(administrator);
			}
		});

		displayCoursesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanelCourses(administrator);
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.setEditable(true);
				usernameField.setEditable(true);
				passwordField.setEditable(true);
			}
		});
		displayTutorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAdministratorPanelTutors(administrator);

			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newUsername = usernameField.getText();
				var oldUsername = administrator.getUsername();

				administrator.setName(nameField.getText());
				administrator.setUsername(newUsername);
				administrator.setPassword(passwordField.getText());

				if (!allAdmins.containsKey(administrator.getUsername())) {
					allAdmins.remove(oldUsername);
					AdministratorContainer.getInstance().addAdministrator(administrator);
				}
				nameField.setEditable(false);
				usernameField.setEditable(false);
				passwordField.setEditable(false);

			}
		});

		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openAddCoursePanel(administrator);

			}
		});

		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openLoginPanel();

			}
		});

		add(nameJLabel);
		add(nameField);
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(systemBalanceLabel);
		add(systemBalanceField);

		add(editButton);
		add(saveButton);
		add(addCourseButton);
		add(displayCoursesButton);
		add(displayStudentButton);
		add(displayTutorsButton);
		add(logoutButton);
		frame.add(this);
		frame.setVisible(true);
		repaint();
		revalidate();

	}

}
