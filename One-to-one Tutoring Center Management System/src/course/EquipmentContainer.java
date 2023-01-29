package course;

import java.util.HashMap;

/**
 * This class uses a singleton pattern which makes the object of this class
 * unique.
 * 
 * @author idilissever
 *
 */
public class EquipmentContainer {

	private HashMap<String, Equipment> equipments = new HashMap<>();
	private static EquipmentContainer instance;

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private EquipmentContainer() {
	}

	/**
	 * 
	 * All the other classes uses this static method to get the only instance of
	 * Equipment Container class.
	 * 
	 * @return the instance of equipment container class
	 * 
	 * 
	 */
	public static EquipmentContainer getInstance() {
		if (instance == null) {
			instance = new EquipmentContainer();
		}
		return instance;
	}

	/**
	 * 
	 * This method creates the equipment objects that will be used in the
	 * application.
	 * 
	 */
	public void setEquipments() {
		var equipment1 = new Equipment("Computer", 100, 5);
		var equipment2 = new Equipment("Calculator", 200, 15);
		var equipment3 = new Equipment("Tablet", 120, 10);
		var equipment4 = new Equipment("Keyboard", 100, 10);
		var equipment5 = new Equipment("Mouse", 80, 10);

		addEquipment(equipment1);
		addEquipment(equipment2);
		addEquipment(equipment3);
		addEquipment(equipment4);
		addEquipment(equipment5);

	}

	/**
	 * This method adds the given Equipment object in the equipments field which is
	 * a HashMap from equipment name (string) to given equipment object.
	 * 
	 * @param equipment
	 */
	public void addEquipment(Equipment equipment) {
		equipments.put(equipment.getName(), equipment);

	}

	/**
	 * 
	 * @return the field all equipments which is a HashMap from equipment name
	 *         (string) to given equipment object.
	 */
	public HashMap<String, Equipment> getAllEquipments() {
		return equipments;
	}
}
