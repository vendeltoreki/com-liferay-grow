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

package com.liferay.grow.gamification.badges.editor.portlet;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.grow.gamification.badges.editor.constants.BadgeTypeEditorPortletKeys;
import com.liferay.grow.gamification.model.BadgeGroup;
import com.liferay.grow.gamification.model.BadgeType;
import com.liferay.grow.gamification.service.BadgeGroupLocalService;
import com.liferay.grow.gamification.service.BadgeTypeLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;

import java.util.Date;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.hibernate.exception.ConstraintViolationException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Vilmos Papp
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.form-validator.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.header-portlet-css=/css/style.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Badge Type Editor",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BadgeTypeEditorPortletKeys.BADGE_TYPE_EDITOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BadgeTypeEditorPortlet extends MVCPortlet {

	public void addBadgeGroup(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String badgeGroupName = actionRequest.getParameter(
			BadgeTypeEditorPortletKeys.BADGE_GROUP_NAME);

		try {
			long badgeGroupId = _counterLocalService.increment(
				BadgeGroup.class.getName());

			BadgeGroup badgeGroup = _badgeGroupLocalService.createBadgeGroup(
				badgeGroupId);

			badgeGroup.setGroupName(badgeGroupName);

			_badgeGroupLocalService.addBadgeGroup(badgeGroup);
		}
		catch (ConstraintViolationException cve) {
			SessionErrors.add(
				actionRequest, ConstraintViolationException.class);
		}
		catch (Exception e) {
			_log.error("+=+");
			_log.error(e);
			SessionErrors.add(actionRequest, e.getMessage());
		}
	}

	public void addBadgeType(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String type = actionRequest.getParameter(
			BadgeTypeEditorPortletKeys.TYPE);
		String system = actionRequest.getParameter(
			BadgeTypeEditorPortletKeys.SYSTEM);
		String availableFrom = actionRequest.getParameter(
			BadgeTypeEditorPortletKeys.AVAILABLE_FROM);
		String availableTo = actionRequest.getParameter(
			BadgeTypeEditorPortletKeys.AVAILABLE_TO);
		String templateHTML = actionRequest.getParameter(
			BadgeTypeEditorPortletKeys.TEMPLATE_HTML);
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String fileName = "";

		try {
			long badgeTypeId = _counterLocalService.increment(
				BadgeType.class.getName());

			User user = (User)actionRequest.getAttribute(WebKeys.USER);

			BadgeType badgeType = _badgeTypeLocalService.createBadgeType(
				badgeTypeId);

			if (!availableFrom.equals("")) {
				badgeType.setAssignableFrom(_parseDate(availableFrom));
			}

			if (!availableTo.equals("")) {
				badgeType.setAssignableTo(_parseDate(availableTo));
			}

			badgeType.setSystem(Boolean.parseBoolean(system));
			badgeType.setCompanyId(user.getCompanyId());
			badgeType.setCreateDate(new Date());

			UploadPortletRequest uploadRequest =
				PortalUtil.getUploadPortletRequest(actionRequest);

			if (uploadRequest.getSize(BadgeTypeEditorPortletKeys.FILE_ENTRY) ==
					0) {

				throw new Exception("Received file is 0 bytes!");
			}

			// Get the uploaded file as a file.

			File uploadedFile = uploadRequest.getFile(
				BadgeTypeEditorPortletKeys.FILE_ENTRY);

			fileName = uploadRequest.getFileName(
				BadgeTypeEditorPortletKeys.FILE_ENTRY);
			Folder badgesFolder = null;

			try {
				badgesFolder = _dlAppLocalService.getFolder(
					themeDisplay.getSiteGroupId(), 0,
					BadgeTypeEditorPortletKeys.BADGES);
			}
			catch (NoSuchFolderException nsfe) {
				badgesFolder = _dlAppLocalService.addFolder(
					user.getUserId(), themeDisplay.getSiteGroupId(), 0,
					BadgeTypeEditorPortletKeys.BADGES,
					BadgeTypeEditorPortletKeys.FOLDER_DESCRIPTION,
					new ServiceContext());
			}

			FileEntry fileEntry = _dlAppLocalService.addFileEntry(
				user.getUserId(), badgesFolder.getRepositoryId(),
				badgesFolder.getFolderId(), fileName,
				MimeTypesUtil.getContentType(fileName),
				FileUtil.getBytes(uploadedFile), new ServiceContext());

			badgeType.setFileEntryId(fileEntry.getFileEntryId());

			badgeType.setGroupId(user.getGroupId());
			badgeType.setModifiedDate(new Date());
			badgeType.setType(type);
			badgeType.setUserId(user.getUserId());
			badgeType.setUserName(user.getFullName());
			badgeType.setTemplateHTML(templateHTML);

			_badgeTypeLocalService.addBadgeType(badgeType);
		}
		catch (DuplicateFileEntryException dfee) {
			_log.error(dfee);
			SessionErrors.add(actionRequest, DuplicateFileEntryException.class);
		}
		catch (ConstraintViolationException cve) {
			SessionErrors.add(
				actionRequest, ConstraintViolationException.class);
		}
		catch (Exception e) {
			_log.error(e);
			SessionErrors.add(actionRequest, e.getMessage());
		}
	}

	@Reference(unbind = "-")
	protected void setBadgeGroupLocalService(
		BadgeGroupLocalService badgeGroupLocalService) {

		_badgeGroupLocalService = badgeGroupLocalService;
	}

	@Reference(unbind = "-")
	protected void setBadgeTypeLocalService(
		BadgeTypeLocalService badgeTypeLocalService) {

		_badgeTypeLocalService = badgeTypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setCounterLocalService(
		CounterLocalService counterLocalService) {

		_counterLocalService = counterLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	private Date _parseDate(String date) throws Exception {
		return DateUtil.parseDate("yyyy-MM-dd", date, Locale.US);
	}

	private BadgeGroupLocalService _badgeGroupLocalService;
	private BadgeTypeLocalService _badgeTypeLocalService;
	private CounterLocalService _counterLocalService;
	private DLAppLocalService _dlAppLocalService;
	private Log _log = LogFactoryUtil.getLog(
		BadgeTypeEditorPortlet.class.getName());

}