package system;

import course.Equipment;
import user.Tutor;

/**
 * This class uses a singleton pattern which makes the object of this class
 * unique.
 * 
 * @author idilissever
 *
 */
public class TutoringCenterSystem {

	private double monetaryBalance = 0.0;
	private static TutoringCenterSystem instance;

	/**
	 * Private Constructor to prevent access from other classes. Helps making sure
	 * the instance of the class is unique.
	 */
	private TutoringCenterSystem() {

	}

	/**
	 * This class uses a singleton pattern which makes the object of this class
	 * unique. All the other classes uses this static method to get the only
	 * instance of TutoringCenterSystem class.
	 * 
	 * @return
	 */
	public static TutoringCenterSystem getInstance() {
		if (instance == null) {
			instance = new TutoringCenterSystem();
		}
		return instance;
	}

	/**
	 * This method adds the profitGained given as argument to the field
	 * monateryBalance field.
	 * 
	 * @param profitGained
	 */
	public void makeProfit(double profitGained) {
		monetaryBalance += profitGained;
	}

	/**
	 * This method calculates the monetary cut that the tutoring center should gain
	 * from given tutor's tutoring session and adds the profit gained to its
	 * monetaryBalance field using the makeProfit() method.
	 * 
	 * @param tutor
	 */
	public void gainFromTutorSession(Tutor tutor) {
		makeProfit(tutor.getPercentageCut() * tutor.getTutoringCost() / 100);
	}

	/**
	 * This method calculates the monetary cut that the tutoring center should gain
	 * from selling the given equipment and adds the profit gained to its
	 * monetaryBalance field using the makeProfit() method.
	 * 
	 * @param equipment
	 */
	public void gainFromEquipmentSold(Equipment equipment) {
		makeProfit(equipment.getProfit());
	}

	public void gainFromTutoringSession(Tutor tutor) {
		var recentGain = tutor.getTutoringCost() * tutor.getPercentageCut() * 0.01;
		makeProfit(recentGain);
	}

	public double getMonateryBalance() {
		return monetaryBalance;
	}

}
