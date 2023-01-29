package user;

import java.util.ArrayList;
import java.util.List;

import course.Course;
import schedule.ScheduleManager;

/**
 * A concrete subclass of the abstract class user. Contains getter and setter
 * methods for its fields.
 * 
 * @author idilissever
 *
 */
public class Tutor extends User {

	private String imageURL;
	private double monateryBalance;
	private List<Course> courses;
	private double tutoringCost;
	private double percentageCut;
	private TutorType tutorType;
	private ScheduleManager scheduleManager;

	/**
	 * Creates student objects using its super class's constructor and the arguments
	 * passed.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param imageURL
	 * @param monateryBalance
	 * @param tutoringCost
	 * @param percentageCut
	 * @param tutorType
	 */
	public Tutor(String name, String username, String password, String imageURL, double monateryBalance,
			double tutoringCost, double percentageCut, TutorType tutorType) {
		super(name, username, password);
		this.imageURL = imageURL;
		this.monateryBalance = monateryBalance;
		this.courses = new ArrayList<>();
		this.tutoringCost = tutoringCost;
		this.percentageCut = percentageCut;
		this.tutorType = tutorType;
		this.scheduleManager = new ScheduleManager(this);
	}

	/**
	 * Creates student objects using the overloaded constructor the class contains
	 * and the arguments passed.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param imageURL
	 * @param monateryBalance
	 * @param tutoringCost
	 * @param percentageCut
	 * @param tutorType
	 * @param courses
	 */
	public Tutor(String name, String username, String password, String imageURL, double monateryBalance,
			double tutoringCost, double percentageCut, TutorType tutorType, List<Course> courses) {
		this(name, username, password, imageURL, monateryBalance, tutoringCost, percentageCut, tutorType);
		this.courses = courses;
	}

	public ScheduleManager getScheduleManager() {
		return scheduleManager;
	}

	public enum TutorType {
		A_LEVEL, B_LEVEL
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public double getMonateryBalance() {
		return monateryBalance;
	}

	public void setMonateryBalance(double monateryBalance) {
		this.monateryBalance = monateryBalance;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public double getTutoringCost() {
		return tutoringCost;
	}

	public void setTutoringCost(double tutoringCost) {
		this.tutoringCost = tutoringCost;
	}

	public double getPercentageCut() {
		return percentageCut;
	}

	public void setPercentageCut(double percentageCut) {
		this.percentageCut = percentageCut;
	}

	public TutorType getTutorType() {
		return tutorType;
	}

	public void setTutorType(TutorType tutorType) {
		this.tutorType = tutorType;
	}

}
