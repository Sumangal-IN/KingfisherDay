package com.tcs.KingfisherDay.model;

public class QuizResult {
	private OptionPercentage optionPercentage;
	private Response winnerResponse;
	private Employee winner;

	public QuizResult() {
	}

	public QuizResult(OptionPercentage optionPercentage, Response winnerResponse, Employee winner) {
		super();
		this.optionPercentage = optionPercentage;
		this.winnerResponse = winnerResponse;
		this.winner = winner;
	}

	public OptionPercentage getOptionPercentage() {
		return optionPercentage;
	}

	public void setOptionPercentage(OptionPercentage optionPercentage) {
		this.optionPercentage = optionPercentage;
	}

	public Response getWinnerResponse() {
		return winnerResponse;
	}

	public void setWinnerResponse(Response winnerResponse) {
		this.winnerResponse = winnerResponse;
	}

	public Employee getWinner() {
		return winner;
	}

	public void setWinner(Employee winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "QuizResult [optionPercentage=" + optionPercentage + ", winnerResponse=" + winnerResponse + ", winner="
				+ winner + "]";
	}

}
