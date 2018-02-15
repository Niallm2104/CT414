package ct414;

import java.util.Date;
import java.util.List;

public class AssessmentImpl implements Assessment {

	private String information;
	private Date ClosingDate;
	private List<Question> questions;
	private Question question;
	private int answer;
	private int AssociatedId;
	
	public AssessmentImpl(String information, Date Closing, List quest, Question question, int answer, int ID){
		this.information = information;
		this.ClosingDate = Closing;
		this.questions = quest;
		this.question = question;
		this.answer = answer;
		this.AssociatedId = ID;
	}
	
	
	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return this.information;
	}

	@Override
	public Date getClosingDate() {
		// TODO Auto-generated method stub
		return this.ClosingDate;
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return this.questions;
	}

	@Override
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		// TODO Auto-generated method stub
		try{
			return questions.get(questionNumber);
		} catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidQuestionNumber();
        }
	}

	@Override
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		if(questionNumber < 0 || questionNumber >= questions.size()){
            throw new InvalidQuestionNumber();
        }

        if(optionNumber < 0 || optionNumber >= getQuestion(questionNumber).getAnswerOptions().length){
            throw new InvalidOptionNumber();
        }
        this.answer = optionNumber;
		
	}

	@Override
	public int getSelectedAnswer(int questionNumber) {
		
		return answer;
	}

	@Override
	public int getAssociatedID() {
		// TODO Auto-generated method stub
		return this.AssociatedId;
	}
	
	
	
}
