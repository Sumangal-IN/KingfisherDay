package com.tcs.KingfisherDay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Question {

	@Id
	@Column(name = "QUESTION_ID", nullable = false)
	private String questionID;
	@Column(name = "QUESTION_TEXT", nullable = false)
	private String questionText;
	@Column(name = "OPTION_A", nullable = false)
	private String optionA;
	@Column(name = "OPTION_B", nullable = false)
	private String optionB;
	@Column(name = "OPTION_C", nullable = false)
	private String optionC;
	@Column(name = "OPTION_D", nullable = false)
	private String optionD;
	@Column(name = "CURRENT", nullable = false)
	private boolean current;

	public Question() {

	}

	public Question(String questionID, String questionText, String optionA, String optionB, String optionC,
			String optionD, boolean current) {
		super();
		this.questionID = questionID;
		this.questionText = questionText;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.current = current;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", questionText=" + questionText + ", optionA=" + optionA
				+ ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", current=" + current
				+ "]";
	}

}
