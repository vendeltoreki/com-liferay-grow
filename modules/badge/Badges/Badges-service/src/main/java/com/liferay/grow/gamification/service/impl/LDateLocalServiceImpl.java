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

import com.liferay.grow.gamification.exception.NoSuchLDateException;
import com.liferay.grow.gamification.model.LDate;
import com.liferay.grow.gamification.service.base.LDateLocalServiceBaseImpl;

/**
 * The implementation of the l date local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.grow.gamification.service.LDateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Vilmos Papp
 * @see LDateLocalServiceBaseImpl
 * @see com.liferay.grow.gamification.service.LDateLocalServiceUtil
 */
public class LDateLocalServiceImpl extends LDateLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.grow.gamification.service.LDateLocalServiceUtil} to access the l date local service.
	 */
	public long getDateId(int year, int month, int day)
		throws NoSuchLDateException {

		LDate date = lDatePersistence.findByY_M_D(year, month, day);

		return date.getDateId();
	}

}