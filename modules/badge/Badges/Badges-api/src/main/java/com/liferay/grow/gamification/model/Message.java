/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.grow.gamification.model;

import jodd.json.JsonSerializer;

/**
 * @author Vilmos Papp
 */
public class Message {

	public static final int BADGE_MESSAGE = 1;

	public static final int SYSTEM_MESSAGE = 0;

	public String getBadgeType() {
		return _badgeType;
	}

	public String getDescription() {
		return _description;
	}

	public String getImageURL() {
		return _imageURL;
	}

	public int getMessageType() {
		return _messageType;
	}

	public String getReceiverName() {
		return _receiverName;
	}

	public String getUserName() {
		return _userName;
	}

	public void setBadgeType(String badgeType) {
		_badgeType = badgeType;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setImageURL(String imageURL) {
		_imageURL = imageURL;
	}

	public void setMessageType(int messageType) {
		_messageType = messageType;
	}

	public void setReceiverName(String receiverName) {
		_receiverName = receiverName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public String toString() {
		JsonSerializer jsonSerializer = new JsonSerializer();

		return jsonSerializer.serialize(this);
	}

	private String _badgeType;
	private String _description;
	private String _imageURL;
	private int _messageType;
	private String _receiverName;
	private String _userName;

}