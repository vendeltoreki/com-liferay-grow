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

package com.liferay.grow.gamification.service.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.grow.gamification.badges.notification.BadgeReceivedSubscritpionSender;
import com.liferay.grow.gamification.badges.notification.BadgeWebSocketEndpoint;
import com.liferay.grow.gamification.badges.notification.constants.BadgeNotificationPortletKeys;
import com.liferay.grow.gamification.badges.notification.portlet.BadgeNotificationPortlet;
import com.liferay.grow.gamification.model.Badge;
import com.liferay.grow.gamification.model.BadgeType;
import com.liferay.grow.gamification.model.Message;
import com.liferay.grow.gamification.service.base.BadgeLocalServiceBaseImpl;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Locale;

import javax.mail.internet.InternetAddress;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

/**
 * The implementation of the badge local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.grow.gamification.service.BadgeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Vilmos Papp
 * @see BadgeLocalServiceBaseImpl
 * @see com.liferay.grow.gamification.service.BadgeLocalServiceUtil
 */
@ClientEndpoint
public class BadgeLocalServiceImpl extends BadgeLocalServiceBaseImpl {

	@Override
	public Badge addBadge(Badge badge) {
		return addBadge(badge, true);
	}

	@Override
	public Badge addBadge(Badge badge, boolean notify) {
		badge = super.addBadge(badge);

		if (notify) {
			_notifySubscribers(badge);
		}

		return badge;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.grow.gamification.service.BadgeLocalServiceUtil} to access the badge local service.
	 */
	public List<Badge> getBadgesOfUser(long userId) {
		return badgePersistence.findBytoUserId(userId);
	}

	public List<Badge> getBadgesOfUser(long userId, int start, int end) {
		return badgePersistence.findBytoUserId(userId, start, end);
	}

	private String _getImageLink(Badge badge) throws PortalException {
		BadgeType badgeType = badgeTypeLocalService.getBadgeType(
			badge.getBadgeTypeId());

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			badgeType.getFileEntryId());

		String downloadUrl = DLUtil.getPreviewURL(
			fileEntry, fileEntry.getFileVersion(), null, "", false, true);

		StringBundler imageLink = new StringBundler(2);

		imageLink.append("https://grow.liferay.com");
		imageLink.append(downloadUrl);

		return imageLink.toString();
	}

	private MailMessage _getMailMessage(Badge badge) throws PortalException {
		MailMessage mailMessage = new MailMessage();

		String content = _BADGE_EMAIL_BODY;

		BadgeType badgeType = badgeTypeLocalService.getBadgeType(
			badge.getBadgeTypeId());

		String downloadUrl = _getImageLink(badge);

		if (downloadUrl.indexOf("t=") > 0) {
			downloadUrl = downloadUrl.substring(0, downloadUrl.indexOf("t=")-1);
		}

		content = StringUtil.replace(
			content, "${badgeType}", badgeType.getType());
		content = StringUtil.replace(content, "${badgeImageLink}", downloadUrl);
		content = StringUtil.replace(
			content, "${colleague}", badge.getUserName());
		content = StringUtil.replace(
			content, "${reason}", badge.getDescription());

		mailMessage.setBody(content);

		return mailMessage;
	}

	private void _notifySubscribers(Badge badge) {
		URI endpointURI = null;

		try {
			String protocol =
				(Validator.isNull(PropsUtil.get(PropsKeys.WEB_SERVER_PROTOCOL)) ? "ws" : PropsUtil.get(PropsKeys.WEB_SERVER_PROTOCOL));

			if (!protocol.startsWith("ws")) {
				protocol = "wss";
			}

			String httpPort =
				((Integer.parseInt(PropsUtil.get(PropsKeys.WEB_SERVER_HTTP_PORT)) == -1) ? "8080" : PropsUtil.get(PropsKeys.WEB_SERVER_HTTP_PORT));
			String httpsPort =
				((Integer.parseInt(PropsUtil.get(PropsKeys.WEB_SERVER_HTTPS_PORT)) == -1) ? "8443" : PropsUtil.get(PropsKeys.WEB_SERVER_HTTPS_PORT));
			String host =
				(Validator.isNull(PropsUtil.get(PropsKeys.WEB_SERVER_HOST)) ? "localhost" : PropsUtil.get(PropsKeys.WEB_SERVER_HOST));

			StringBundler endpoint = new StringBundler(6);

			endpoint.append(protocol);
			endpoint.append("://");
			endpoint.append(host);
			endpoint.append(":");
			endpoint.append(protocol.equals("ws") ? httpPort : httpsPort);
			endpoint.append("/o/gamification");
			_log.info("endpoint:" + endpoint.toString());
			endpointURI = new URI(endpoint.toString());
		}
		catch (URISyntaxException urise) {
			_log.error(urise);
		}

		WebSocketContainer container =
			ContainerProvider.getWebSocketContainer();
		BadgeWebSocketEndpoint endpoint = new BadgeWebSocketEndpoint();

		try {
			container.connectToServer(endpoint, endpointURI);
		}
		catch (DeploymentException de) {
			_log.error(de);
		}
		catch (IOException ioe) {
			_log.error(ioe);
		}

		// so all of this stuff should normally come from some kind of configuration.
		// As this is just an example, we're using a lot of hard coded values and portal-ext.properties values.

		User fromUser = userLocalService.fetchUser(badge.getUserId());

		String badgeType = badgeTypeLocalService.fetchBadgeType(
			badge.getBadgeTypeId()).getType();

		String entryTitle = badgeType + " Badge Received";

		LocalizedValuesMap subjectLocalizedValuesMap = new LocalizedValuesMap();
		LocalizedValuesMap bodyLocalizedValuesMap = new LocalizedValuesMap();

		subjectLocalizedValuesMap.put(
			Locale.ENGLISH, "A badge has been received");
		bodyLocalizedValuesMap.put(
			Locale.ENGLISH, "A " + badgeType + " badge has been received from " + fromUser.getFullName() +
			".");

		BadgeReceivedSubscritpionSender subscriptionSender =
			new BadgeReceivedSubscritpionSender();

		subscriptionSender.setBadgeType(badgeType);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(
			BadgeNotificationPortlet.class.getName());
		subscriptionSender.setCompanyId(badge.getCompanyId());

		subscriptionSender.setCurrentUserId(badge.getToUserId());
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(
			fromUser.getEmailAddress(), fromUser.getFullName());
		subscriptionSender.setHtmlFormat(true);

		subscriptionSender.setMailId("badge_received", 0);

		int notificationType = 100;

		subscriptionSender.setNotificationType(notificationType);
		subscriptionSender.setCreatorUserId(badge.getUserId());
		subscriptionSender.setNotificationType(
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY);
		String portletId = BadgeNotificationPortletKeys.BadgeNotification;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromUser.getEmailAddress());
		subscriptionSender.setServiceContext(new ServiceContext());

		subscriptionSender.addPersistedSubscribers(
			BadgeNotificationPortlet.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();

		payloadJSON.put(BadgeNotificationPortletKeys.BADGE_TYPE, badgeType);
		payloadJSON.put(
			BadgeNotificationPortletKeys.BADGE_COMMENT, badge.getDescription());

		UserNotificationEvent userNotificationEvent =
			UserNotificationEventLocalServiceUtil.createUserNotificationEvent(
				counterLocalService.increment());

		userNotificationEvent.setCompanyId(badge.getCompanyId());
		userNotificationEvent.setDeliverBy(0);
		userNotificationEvent.setDelivered(true);
		userNotificationEvent.setDeliveryType(
			UserNotificationDeliveryConstants.TYPE_WEBSITE);
		userNotificationEvent.setTimestamp(System.currentTimeMillis());
		userNotificationEvent.setPayload(payloadJSON.toString());
		userNotificationEvent.setType(
			BadgeNotificationPortletKeys.BadgeNotification);
		userNotificationEvent.setUserId(badge.getToUserId());

		UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
			userNotificationEvent);

		InternetAddress recipient = null;
		InternetAddress sender = null;
		User user = null;

		try {
			MailMessage mailMessage = _getMailMessage(badge);

			user = userLocalService.getUserById(badge.getToUserId());

			sender = new InternetAddress(
				_BADGE_EMAIL_SENDER_ADDRESS, _BADGE_EMAIL_SENDER_PERSONAL);
			recipient = new InternetAddress(
				user.getEmailAddress(), user.getFullName());

			mailMessage.setFrom(sender);
			mailMessage.setHTMLFormat(true);
			mailMessage.setSubject(_BADGE_EMAIL_SUBJECT);

			mailMessage.setTo(recipient);

			MailServiceUtil.sendEmail(mailMessage);
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee);
		}
		catch (PortalException pe) {
			_log.error(pe);
		}

		try {
			Message message = new Message();

			if (user == null) {
				user = userLocalService.getUserById(badge.getToUserId());
			}

			message.setBadgeType(badgeType);
			message.setMessageType(Message.BADGE_MESSAGE);
			message.setDescription(badge.getDescription());
			message.setUserName(fromUser.getFullName());
			message.setImageURL(_getImageLink(badge));
			message.setReceiverName(user.getFullName());
			endpoint.sendMessage(message.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe);
		}
		catch (PortalException pe) {
			_log.error(pe);
		}
	}

	private static final String _BADGE_EMAIL_BODY = "<center><table style=\"border-radius: 10px;\" bgcolor=\"#ffffff\" \n" +
			"    width=\"90%\" cellspacing=\"2\" cellpadding=\"2\" border=\"0\"><tbody><tr align=\"center\">\n" +
			"    <td valign=\"top\">Good news! You've just received a ${badgeType} from ${colleague}!</td>\n" +
			"    </tr><tr align=\"center\"><td valign=\"top\"><img src=\"${badgeImageLink}\"\n" +
			"    alt=\"Badge image\" height=\"300\" width=\"300\">\n" +
			"    </td></tr><tr align=\"center\"><td valign=\"top\">Congratulations!</td></tr><tr align=\"center\">\n" +
			"    <td valign=\"top\">Reason you received this badge for : ${reason}</td></tr></tbody></table>\n" +
			"    </center>";

	private static final String _BADGE_EMAIL_SENDER_ADDRESS =
		"admin@liferay.com";

	private static final String _BADGE_EMAIL_SENDER_PERSONAL =
		"GROW Badge Notification";

	private static final String _BADGE_EMAIL_SUBJECT = "You received a Badge!";

	private static final Log _log = LogFactoryUtil.getLog(
		BadgeLocalServiceImpl.class);

}