package com.liferay.grow.gamification.model;

import jodd.json.JsonSerializer;


public class Message {

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getBadgeType() {
		return badgeType;
	}

	public void setBadgeType(String badgeType) {
		this.badgeType = badgeType;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String toString() {
		JsonSerializer jsonSerializer = new JsonSerializer();
		return jsonSerializer.serialize(this);
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public static final int SYSTEM_MESSAGE = 0;
	public static final int BADGE_MESSAGE = 1;

	private int messageType;
	private String badgeType;
	private String userName;
	private String description;
	private String imageURL;
	private String receiverName;

}
