package user;

import java.util.HashMap;

/**
 * This class generates all the administrators (which in our case one of them)
 * in the application. Uses a Singleton pattern for making sure that there will
 * be only one instance of this class.
 * 
 * @author idilissever
 *
 */
public class AdministratorContainer {

	private static AdministratorContainer instance;
	private HashMap<String, Administrator> allAdministrators = new HashMap<>();

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private AdministratorContainer() {

	}

	/**
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of Administrator Container class.
	 * 
	 * @return the only instance of this class
	 */
	public static AdministratorContainer getInstance() {
		if (instance == null) {
			instance = new AdministratorContainer();
		}
		return instance;

	}

	/**
	 * This method creates the Administrators in the system every time the
	 * application is executed.
	 */
	public void setAdministrators() {

		var admin1 = new Administrator("Bengisu İşsever", "bengisuissever", "123");
		addAdministrator(admin1);

	}

	/**
	 * This method adds the given Administrator to the field allAdministrators.
	 * 
	 * @param administrator
	 */
	public void addAdministrator(Administrator administrator) {
		allAdministrators.put(administrator.getUsername(), administrator);

	}

	/**
	 * Creates an Administrator with the given information.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @return the created administrator object.
	 */
	public Administrator createAdministrator(String name, String username, String password) {
		var admin = new Administrator(name, username, password);
		addAdministrator(admin);
		return admin;
	}

	/**
	 * 
	 * @return the field allAdministrators
	 */
	public HashMap<String, Administrator> getAllAdministrators() {
		return allAdministrators;
	}

}
