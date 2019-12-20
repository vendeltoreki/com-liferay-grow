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

package com.liferay.social.activity.customizer.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.social.activity.customizer.model.CustomSocialActivitySet;
import com.liferay.social.activity.customizer.service.persistence.CustomSocialActivitySetPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CustomSocialActivitySetFinderBaseImpl
	extends BasePersistenceImpl<CustomSocialActivitySet> {

	public CustomSocialActivitySetFinderBaseImpl() {
		setModelClass(CustomSocialActivitySet.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getCustomSocialActivitySetPersistence().getBadColumnNames();
	}

	/**
	 * Returns the custom social activity set persistence.
	 *
	 * @return the custom social activity set persistence
	 */
	public CustomSocialActivitySetPersistence
		getCustomSocialActivitySetPersistence() {

		return customSocialActivitySetPersistence;
	}

	/**
	 * Sets the custom social activity set persistence.
	 *
	 * @param customSocialActivitySetPersistence the custom social activity set persistence
	 */
	public void setCustomSocialActivitySetPersistence(
		CustomSocialActivitySetPersistence customSocialActivitySetPersistence) {

		this.customSocialActivitySetPersistence =
			customSocialActivitySetPersistence;
	}

	@BeanReference(type = CustomSocialActivitySetPersistence.class)
	protected CustomSocialActivitySetPersistence
		customSocialActivitySetPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CustomSocialActivitySetFinderBaseImpl.class);

}