package ct414;

public class QuestionImpl implements Question {
	private int questionNo;
	private String questionDe;
	private String[] options;
	
	public QuestionImpl(int qNo, String qDe, String[] options){
		this.options = options;
		this.questionDe = qDe;
		this.questionNo = qNo;
	}
	@Override
	public int getQuestionNumber() {
		
		return this.questionNo;
	}

	@Override
	public String getQuestionDetail() {
		// TODO Auto-generated method stub
		return this.questionDe;
	}

	@Override
	public String[] getAnswerOptions() {
		// TODO Auto-generated method stub
		return this.options;
	}

}
