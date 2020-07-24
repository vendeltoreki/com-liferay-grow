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

package com.liferay.grow.gamification.badges.notification.action;

import com.liferay.grow.gamification.badges.notification.constants.BadgeNotificationPortletKeys;
import com.liferay.grow.gamification.badges.notification.portlet.BadgeNotificationPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.SubscriptionLocalService;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Vilmos Papp
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BadgeNotificationPortletKeys.BADGE_NOTIFICATION,
		"mvc.command.name=/update_subscription"
	},
	service = MVCActionCommand.class
)
@SuppressWarnings("deprecation")
public class SubscribeMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return;
		}

		long userId = PortalUtil.getUserId(actionRequest);

		if (Constants.SUBSCRIBE.equals(cmd)) {
			_subscriptionLocalService.addSubscription(
				userId, 0, BadgeNotificationPortlet.class.getName(), 0);
		}
		else if (Constants.UNSUBSCRIBE.equals(cmd)) {
			_subscriptionLocalService.deleteSubscription(
				userId, BadgeNotificationPortlet.class.getName(), 0);
		}
	}

	@Reference(unbind = "-")
	protected void setSubscriptionLocalService(
		final SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

	private SubscriptionLocalService _subscriptionLocalService;

}