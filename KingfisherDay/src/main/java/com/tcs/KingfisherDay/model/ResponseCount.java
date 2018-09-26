package com.tcs.KingfisherDay.model;

public class ResponseCount {
	private int love;
	private int like;

	public ResponseCount() {

	}

	public ResponseCount(int love, int like) {
		super();
		this.love = love;
		this.like = like;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

}
