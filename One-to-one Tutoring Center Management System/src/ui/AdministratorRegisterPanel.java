package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import user.AdministratorContainer;

/**
 * Panel displayed if the user presses the button Register as Administrator.
 * Used for taking the new Administrator's information and creating a new
 * Administrator.
 * 
 * @author idilissever
 *
 */
public class AdministratorRegisterPanel extends JPanel {

	private MainFrame frame;
	private JLabel nameLabel = new JLabel("Name and Surname:");
	private JTextField nameField = new JTextField();
	private JLabel usernameLabel = new JLabel("Username: ");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password: ");
	private JTextField passwordField = new JTextField();

	private JButton saveButton = new JButton("Register");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Creates a new AdministratorRegisterPanel with MainFrame object passed as
	 * argument.
	 * 
	 * @param frame
	 */
	public AdministratorRegisterPanel(MainFrame frame) {
		this.frame = frame;
		setLayout(new GridLayout(8, 2));
		setVisible(true);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.openLoginPanel();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				var adminContainer = AdministratorContainer.getInstance();
				String name = nameField.getText();
				String username = usernameField.getText();
				String password = passwordField.getText();

				var admin = adminContainer.createAdministrator(name, username, password);

				frame.openAdministratorPanel(admin);

			}
		});

		add(nameLabel);
		add(nameField);
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(saveButton);
		add(cancelButton);

		frame.add(this);
		frame.setVisible(true);

	}
}
