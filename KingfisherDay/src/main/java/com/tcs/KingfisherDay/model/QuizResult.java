package com.tcs.KingfisherDay.model;

public class QuizResult {
	private OptionPercentage optionPercentage;
	private Associate winner;

	public QuizResult() {
	}

	public QuizResult(OptionPercentage optionPercentage, Associate winner) {
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

	public Associate getWinner() {
		return winner;
	}

	public void setWinner(Associate winner) {
		this.winner = winner;
	}

}
