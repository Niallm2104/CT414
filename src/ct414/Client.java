package ct414;

import java.awt.event.ActionEvent;
import java.rmi.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener, ExamServer {
    JLabel lblId;
    JLabel lblPass;
    JTextField txtId;
    JTextField txtPass;
    JButton btnProcess;
    JTextArea txtS;

    public Client() {
    	
    	
        
        this.setTitle("GUI");
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        //Labels and text fields for ID & password
        lblId = new JLabel("ID: ");
        lblId.setBounds(10, 10, 90, 21);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(105, 10, 90, 21);
        add(txtId);

        lblPass = new JLabel("Password: ");
        lblPass.setBounds(10, 35, 90, 21);
        add(lblPass);

        txtPass = new JTextField();
        txtPass.setBounds(105, 35, 90, 21);
        add(txtPass);

        btnProcess = new JButton("Process");
        btnProcess.setBounds(200, 40, 90, 21);
        btnProcess.addActionListener(this);
        add(btnProcess);

        txtS = new JTextArea();
        txtS.setBounds(10, 85, 290, 120);
        add(txtS);

        this.setVisible(true);
    }
    

    public static void main(String[] args) throws RemoteException, NotBoundException {
        new Client();
        String name = "ExamServer";
        Registry registry = LocateRegistry.getRegistry();
        ExamServer serv = (ExamServer) registry.lookup(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnProcess)) {
            try {
                processInformation();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void processInformation() throws UnknownHostException, IOException {
       // Socket s = new Socket("localhost", 5000);
      //  ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
        
    	
        
     /*   String ID = txtId.getText();
        int Pass = Integer.parseInt(txtPass.getText());

        p.writeObject(new Student(ID, Pass));
        p.flush();

        // Here we read the details from server
        BufferedReader response = new BufferedReader(new InputStreamReader(
                s.getInputStream()));
        txtS.setText("The server respond: " + response.readLine());
        p.close();
        response.close();
        s.close();*/
    }

	@Override
	public int login(int studentid, String password) throws UnauthorizedAccess, RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Assessment> getAvailableSummary(int token, int studentid)
			throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assessment getAssessment(int token, int studentid, String courseCode)
			throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitAssessment(int token, int studentid, Assessment completed)
			throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		// TODO Auto-generated method stub
		
	}

}