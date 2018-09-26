package com.tcs.KingfisherDay.model;

public class PhotographyContestImageResponse {

	private PhotographyContestImage photographyContestImage;
	private ResponseCount responseCount;

	public PhotographyContestImageResponse() {

	}

	public PhotographyContestImageResponse(PhotographyContestImage photographyContestImage,
			ResponseCount responseCount) {
		super();
		this.photographyContestImage = photographyContestImage;
		this.responseCount = responseCount;
	}

	public PhotographyContestImage getPhotographyContestImage() {
		return photographyContestImage;
	}

	public void setPhotographyContestImage(PhotographyContestImage photographyContestImage) {
		this.photographyContestImage = photographyContestImage;
	}

	public ResponseCount getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(ResponseCount responseCount) {
		this.responseCount = responseCount;
	}

}