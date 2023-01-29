package user;

import java.util.ArrayList;
import java.util.List;

import course.*;
import schedule.ScheduledLesson;

/**
 * A concrete subclass of the abstract class user. Contains getter and setter
 * methods for its fields.
 * 
 * @author idilissever
 *
 */
public class Student extends User {

	private String tCKN;
	private int age;
	private int idNumber;
	private double monateryBalance;
	private Gender gender;
	private List<Course> passedCourses;
	private List<Course> registeredCourses;
	private List<ScheduledLesson> tutoringSessions = new ArrayList<>();
	private List<Equipment> equipments = new ArrayList<>();
	private List<ScheduledLesson> paidLessons = new ArrayList<>();
	private double totalDebt = 0.0;

	/**
	 * Creates student objects using the overloaded constructor it contains and the
	 * arguments passed.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param tCKN
	 * @param age
	 * @param idNumber
	 * @param monateryBalance
	 * @param gender
	 */
	public Student(String name, String username, String password, String tCKN, int age, int idNumber,
			double monateryBalance, Gender gender) {
		this(name, username, password, tCKN, age, idNumber, monateryBalance, gender, new ArrayList<>(),
				new ArrayList<>());
	}

	/**
	 * Creates student objects using its super class's constructor and the arguments
	 * passed.
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param tCKN
	 * @param age
	 * @param idNumber
	 * @param monateryBalance
	 * @param gender
	 * @param passedCourses
	 * @param registeredCourses
	 */
	public Student(String name, String username, String password, String tCKN, int age, int idNumber,
			double monateryBalance, Gender gender, List<Course> passedCourses, List<Course> registeredCourses) {
		super(name, username, password);
		this.tCKN = tCKN;
		this.age = age;
		this.idNumber = idNumber;
		this.monateryBalance = monateryBalance;
		this.gender = gender;
		this.passedCourses = passedCourses;
		this.registeredCourses = registeredCourses;
	}

	public double getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(double totalDebt) {
		this.totalDebt = totalDebt;
	}

	/**
	 * An Enum for the gender of the student.
	 * 
	 * @author idilissever
	 *
	 */
	public enum Gender {
		FEMALE, MALE
	}

	public void settCKN(String tCKN) {
		if (tCKN.length() != 11) {
			this.tCKN = "00000000000";
			return;
		}
		this.tCKN = tCKN;
	}

	public String gettCKN() {
		return tCKN;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public double getMonateryBalance() {
		return monateryBalance;
	}

	public void setMonateryBalance(double monateryBalance) {
		this.monateryBalance = monateryBalance;
	}

	public List<Course> getPassedCourses() {
		return passedCourses;
	}

	public void setPassedCourses(List<Course> passedCourses) {
		this.passedCourses = passedCourses;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	public void setGender(String gender) {
		if (gender == "FEMALE") {
			this.gender = Gender.FEMALE;
		} else if (gender == "MALE") {
			this.gender = Gender.MALE;
		}
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;

	}

	public List<ScheduledLesson> getTutoringSessions() {
		return tutoringSessions;
	}

	/**
	 * A helper method for adding the given scheduled lesson object to its field
	 * tutoring sessions.
	 * 
	 * @param scheduledLesson
	 */
	public void addSession(ScheduledLesson scheduledLesson) {
		tutoringSessions.add(scheduledLesson);

	}

	/**
	 * A helper method for adding the given equipment object to its field
	 * equipments.
	 * 
	 * @param equipment
	 */
	public void addEquipment(Equipment equipment) {
		equipments.add(equipment);
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	/**
	 * A helper method for adding the given scheduled lesson object to its field
	 * paid lessons. For separating the paid lessons from unpaid ones.
	 * 
	 * @param paidLesson
	 */
	public void addPaidSession(ScheduledLesson paidLesson) {
		paidLessons.add(paidLesson);
	}

	public List<ScheduledLesson> getPaidLessons() {
		return paidLessons;
	}

	/**
	 * A helper method for getting the unpaid ScheduledLesson objects.
	 * 
	 * @return list of scheduled lesson objects that are not payed by the student
	 */
	public List<ScheduledLesson> getUnpaidLessons() {
		var unpaidLessons = new ArrayList<ScheduledLesson>();
		for (ScheduledLesson lesson : getTutoringSessions()) {
			if (getPaidLessons().contains(lesson)) {
				continue;
			}
			unpaidLessons.add(lesson);
		}
		return unpaidLessons;

	}

}
