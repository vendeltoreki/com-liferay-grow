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

package badgeimport.portlet;

import badgeimport.constants.BadgeImportPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.grow.gamification.model.Badge;
import com.liferay.grow.gamification.model.BadgeType;
import com.liferay.grow.gamification.service.BadgeLocalServiceUtil;
import com.liferay.grow.gamification.service.BadgeTypeLocalServiceUtil;
import com.liferay.grow.gamification.service.LDateLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author István András Dézsi
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=BadgeImport Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BadgeImportPortletKeys.BADGE_IMPORT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BadgeImportPortlet extends MVCPortlet {

	public void importBadges(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_importFromCSV(
			"Loyalty_Badge_input.csv", _getLoyaltyBadgeTypeMap(), themeDisplay);
	}

	private Map<Integer, Integer> _getLoyaltyBadgeTypeMap() {
		HashMap<Integer, Integer> loyaltyBadgeTypeMap = new HashMap<>();

		List<BadgeType> badgeTypes =
			BadgeTypeLocalServiceUtil.getAllBadgeTypes();

		for (BadgeType badgeType : badgeTypes) {
			try {
				String title = badgeType.getType();

				title = title.toLowerCase();

				if (!title.contains("loyalty")) {
					continue;
				}

				String[] titleParts = StringUtil.split(title, StringPool.SPACE);

				Integer year = 0;

				for (String titlePart : titleParts) {
					if (GetterUtil.getInteger(titlePart) > 0) {
						year = GetterUtil.getInteger(titlePart);
					}

					break;
				}

				if (year <= 0) {
					_log.error(
						"Cannot determine the year for loyalty badge type: " +
							badgeType.getType());

					continue;
				}

				loyaltyBadgeTypeMap.put(
					year, GetterUtil.getInteger(badgeType.getBadgeTypeId()));
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return loyaltyBadgeTypeMap;
	}

	private InputStream _getStream(String fileName) throws Exception {
		Class<?> clazz = getClass();

		return clazz.getResourceAsStream("dependencies/" + fileName);
	}

	private void _importFromCSV(
			String fileName, Map<Integer, Integer> badgeTypeMap,
			ThemeDisplay themeDisplay)
		throws Exception {

		long companyId = CompanyThreadLocal.getCompanyId();

		User fromUser = themeDisplay.getUser();

		long fromUserId = fromUser.getUserId();
		String fromUserName = fromUser.getFullName();

		Date now = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(now);

		long dateId = LDateLocalServiceUtil.getDateId(
			cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
			cal.get(Calendar.DAY_OF_MONTH));

		String userEmailAddress = StringPool.BLANK;
		String loyalty = StringPool.BLANK;

		InputStream inputStream = null;
		BufferedReader bufferedReader = null;

		User user = null;

		try {
			inputStream = _getStream(fileName);

			bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

			String line = StringPool.BLANK;

			// Reading the header row

			line = bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				try {
					String[] fields = StringUtil.split(
						line, StringPool.SEMICOLON);

					if (fields.length != 2) {
						continue;
					}

					userEmailAddress = fields[0];
					loyalty = fields[1];

					int year = GetterUtil.getInteger(
						StringUtil.split(loyalty, StringPool.SPACE)[0]);

					if (year <= 0) {
						_log.error(
							"Coudn't determine the year number for line: " +
								line);

						continue;
					}

					String description =
						"You have been a member of the Liferay Family for " +
							"more than " + year + " years!";

					user = UserLocalServiceUtil.fetchUserByEmailAddress(
						companyId, userEmailAddress);

					if (user == null) {
						_log.warn(
							"Coudn't find user with email address " +
								userEmailAddress);

						continue;
					}

					long badgeId = CounterLocalServiceUtil.increment(
						Badge.class.getName());

					long badgeTypeId = badgeTypeMap.get(year);

					Badge badge = BadgeLocalServiceUtil.createBadge(badgeId);

					badge.setUserId(fromUserId);
					badge.setAssignedDateId(dateId);
					badge.setBadgeTypeId(badgeTypeId);
					badge.setCompanyId(companyId);
					badge.setCreateDate(now);
					badge.setDescription(description);
					badge.setGroupId(user.getGroupId());
					badge.setToUserId(user.getUserId());
					badge.setUserName(fromUserName);
					badge.setUuid(String.valueOf(UUID.randomUUID()));

					BadgeLocalServiceUtil.addBadge(badge, false);
				}
				catch (Exception e) {
					_log.error(e.getMessage(), e);
				}
			}
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BadgeImportPortlet.class);

}