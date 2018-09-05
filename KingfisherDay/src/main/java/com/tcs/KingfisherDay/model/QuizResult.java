package com.tcs.KingfisherDay.model;

public class QuizResult {
	private OptionPercentage optionPercentage;
	private Employee winner;

	public QuizResult() {
	}

	public QuizResult(OptionPercentage optionPercentage, Employee winner) {
		super();
		this.optionPercentage = optionPercentage;
		this.winner = winner;
	}

	public OptionPercentage getOptionPercentage() {
		return optionPercentage;
	}

	public void setOptionPercentage(OptionPercentage optionPercentage) {
		this.optionPercentage = optionPercentage;
	}

	public Employee getWinner() {
		return winner;
	}

	public void setWinner(Employee winner) {
		this.winner = winner;
	}

}
