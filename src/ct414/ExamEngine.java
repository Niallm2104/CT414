
package ct414;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ExamEngine implements ExamServer {
	
	private static Student[] studs;					//Student Array
	private HashMap<Integer, Session> sessions; //Hashmap containing active sessions
	private static Assessment[] assessm;				//Assessment array
	Random rand;								//For generating a random token integer

    // Constructor is required
    public ExamEngine(Student[] students, Assessment[] ass) {
        super();
        ExamEngine.studs = students;
        ExamEngine.assessm = ass;
        this.sessions = new HashMap<>();
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {
    	int min = 0;
    	int max = 10000000;
    	for(Student s : studs){
    		if(s.getId() == studentid && s.getPassword() == password){
    			 
    			    int randomNum = rand.nextInt((max - min) + 1) + min;		//Generate a random number and assign it to
    			    sessions.put(randomNum, new Session(studentid));			//the session and bind it to the studentId
                    return randomNum;
    		}
    	}

	throw new UnauthorizedAccess("Your Username/ Password was Entered incorrectly");
    }

    // Return a summary list of Assessments currently available for this studentid
    public List<Assessment> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
    	
    	checkSessionId(token, studentid);										//Check if the session Id is correct
    	List<Assessment> assessments = new ArrayList<Assessment>();				//Create an Arraylist of assessments
    	
    	for(Assessment a : this.assessm){
    		if(a.getAssociatedID() == studentid){								//Validate the studentId
    			assessments.add(a);												//if correct add assessment
    		}
    	}
    	
    	if(assessments.size() == 0){
    		throw new NoMatchingAssessment("No assessments found please check ID"); // If there were no assessments throw error
    	}
       
    	return assessments;														//Return assessment list
     }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String courseCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
    	
    	checkSessionId(token, studentid);
    	
    	for(Assessment a : assessm){
    		if(a.getInformation().equals(courseCode) && a.getAssociatedID() == studentid && a.getClosingDate().after(new Date())){
    			return a;
    		}
    	}

    	throw new NoMatchingAssessment("No assessment found");
    }

	// Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
    }
    
    private void checkSessionId(int token, int userID) throws UnauthorizedAccess, RemoteException {
        Session session = sessions.get(token);

        if (session == null || session.getStudentId() != userID){
            throw new UnauthorizedAccess("Error are you sure your logged in?");	//If the ID is incorrect or the session doesnt exist throw an error
        }
        if (session.getLogout().before(new Date())){
            sessions.remove(token);											//If token expires delete
            throw new UnauthorizedAccess("You have been logged in for more than an hour please Log In again to continue");
        }

    }


    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ExamServer";
            ExamServer engine = new ExamEngine(studs, assessm);
            ExamServer stub =
                (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ExamEngine bound");
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }
    }
}
