package ct414;

import java.util.Date;

public class Session {
	
	private Date logout;		//Create a date and student variable to hold 
	private int studentId;		//the tokens for the session
	
	public Session(int id){
		this.logout = new Date(new Date().getTime() + 3600000);
		this.studentId = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public Date getLogout() {
		return logout;
	}

	


}
