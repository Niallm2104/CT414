package ct414;

public class Student {
	private String password;
	private int id;
	
	public Student(String pw, int idNo){
		this.password = pw;
		this.id = idNo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getId(){
		return id;
	}

}
