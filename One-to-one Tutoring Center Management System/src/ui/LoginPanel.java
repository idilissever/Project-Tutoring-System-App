package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import user.AdministratorContainer;
import user.StudentContainer;
import user.TutorContainer;

/**
 * The first panel displayed when the application executes. Used by all the
 * users to log in or transfer the first registering users to register page.
 * 
 * @author idilissever
 *
 */
public class LoginPanel extends JPanel implements ActionListener {

	private JLabel labelUsername, labelPassword, labelUserType;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JComboBox<String> comboUserType;
	private JButton buttonLogin, buttonRegister;
	private MainFrame frame;

	/**
	 * Creates a log in panel.
	 * 
	 * @param frame
	 */
	public LoginPanel(MainFrame frame) {
		this.frame = frame;
		frame.setTitle("Tutoring Center App");
		setLayout(new GridLayout(4, 2));

		labelUsername = new JLabel("\t\t\t\t\tUsername:");
		fieldUsername = new JTextField();
		labelPassword = new JLabel("\t\t\t\t\tPassword:");
		fieldPassword = new JPasswordField();
		labelUserType = new JLabel("\t\t\t\t\tUser Type:");
		comboUserType = new JComboBox<>(new String[] { "Student", "Tutor", "Administrator" });
		buttonLogin = new JButton("Login");
		buttonRegister = new JButton("Register as Student");

		buttonLogin.addActionListener(this);
		buttonRegister.addActionListener(this);

		add(labelUsername);
		add(fieldUsername);
		add(labelPassword);
		add(fieldPassword);
		add(labelUserType);
		add(comboUserType);
		add(buttonLogin);
		add(buttonRegister);

		setVisible(true);

		comboUserType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (String.valueOf(comboUserType.getSelectedItem()) == "Student") {
					buttonRegister.setText(("Register as Student"));
					revalidate();
				} else if (String.valueOf(comboUserType.getSelectedItem()) == "Tutor") {
					buttonRegister.setText("Register as Tutor");
					revalidate();
				} else if (String.valueOf(comboUserType.getSelectedItem()) == "Administrator") {
					buttonRegister.setText("Register as Administrator");
				}

			}
		});
	}

	public void actionPerformed(ActionEvent e) {

		var studentContainer = StudentContainer.getInstance();

		var username = fieldUsername.getText();
		var password = String.valueOf(fieldPassword.getPassword());
		String userType = (String) comboUserType.getSelectedItem();

		if (e.getSource() == buttonLogin) {
			if (Login.login(username, password, userType)) {
				if (userType.equals("Student")) {
					var student = studentContainer.getAllStudents().get(username);
					frame.openStudentPanel(student);
				}
				if (userType.equals("Tutor")) {
					var tutor = TutorContainer.getInstance().getAllTutors().get(username);
					frame.openTutorPanel(tutor);
				}
				if (userType.equals("Administrator")) {
					var admin = AdministratorContainer.getInstance().getAllAdministrators().get(username);
					frame.openAdministratorPanel(admin);

				}

			}

		} else if (e.getSource() == buttonRegister) {
			if (userType.equals("Student")) {
				frame.openStudentRegisterPanel();
			} else if (userType.equals("Tutor")) {
				frame.openTutorRegisterPanel();
			} else if (userType.equals("Administrator")) {
				frame.openAdministratorRegisterPanel();

			}
		}
	}

}
