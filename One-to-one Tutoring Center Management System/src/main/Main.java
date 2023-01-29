/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <İdil İşsever, 0080447>
*************************************************************************/
package main;

import course.CourseContainer;
import course.EquipmentContainer;
import ui.MainFrame;
import user.AdministratorContainer;
import user.StudentContainer;
import user.TutorContainer;

public class Main {
	public static void main(String[] args) {

		var studentContainer = StudentContainer.getInstance();
		var tutorContainer = TutorContainer.getInstance();
		var equipmentContainer = EquipmentContainer.getInstance();
		var courseContainer = CourseContainer.getInstance();
		var administratorContainer = AdministratorContainer.getInstance();

		equipmentContainer.setEquipments();
		courseContainer.setCourses();
		tutorContainer.setTutors();
		studentContainer.setStudents();
		administratorContainer.setAdministrators();

		new MainFrame().openLoginPanel();

	}
}
