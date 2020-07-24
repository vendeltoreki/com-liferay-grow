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

package com.liferay.grow.gamification.badges.notification;

import com.liferay.grow.gamification.badges.notification.constants.BadgeNotificationPortletKeys;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.SubscriptionSender;

/**
 * @author Vilmos Papp
 */
@SuppressWarnings({"serial", "deprecation"})
public class BadgeReceivedSubscritpionSender extends SubscriptionSender {

	public void setBadgeDesctiption(String badgeComment) {
		_badgeComment = badgeComment;
	}

	public void setBadgeType(String badgeType) {
		_badgeType = badgeType;
	}

	protected void populateNotificationEventJSONObject(
		JSONObject notificationEventJSONObject) {

		super.populateNotificationEventJSONObject(notificationEventJSONObject);

		notificationEventJSONObject.put(
			BadgeNotificationPortletKeys.BADGE_TYPE, _badgeType);
		notificationEventJSONObject.put(
			BadgeNotificationPortletKeys.BADGE_COMMENT, _badgeComment);
	}

	private String _badgeComment;
	private String _badgeType;

}