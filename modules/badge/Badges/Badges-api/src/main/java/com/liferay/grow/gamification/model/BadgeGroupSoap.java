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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.grow.gamification.service.http.BadgeGroupServiceSoap}.
 *
 * @author Vilmos Papp
 * @see com.liferay.grow.gamification.service.http.BadgeGroupServiceSoap
 * @generated
 */
@ProviderType
public class BadgeGroupSoap implements Serializable {
	public static BadgeGroupSoap toSoapModel(BadgeGroup model) {
		BadgeGroupSoap soapModel = new BadgeGroupSoap();

		soapModel.setBadgeGroupId(model.getBadgeGroupId());
		soapModel.setGroupName(model.getGroupName());

		return soapModel;
	}

	public static BadgeGroupSoap[] toSoapModels(BadgeGroup[] models) {
		BadgeGroupSoap[] soapModels = new BadgeGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BadgeGroupSoap[][] toSoapModels(BadgeGroup[][] models) {
		BadgeGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BadgeGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BadgeGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BadgeGroupSoap[] toSoapModels(List<BadgeGroup> models) {
		List<BadgeGroupSoap> soapModels = new ArrayList<BadgeGroupSoap>(models.size());

		for (BadgeGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BadgeGroupSoap[soapModels.size()]);
	}

	public BadgeGroupSoap() {
	}

	public long getPrimaryKey() {
		return _badgeGroupId;
	}

	public void setPrimaryKey(long pk) {
		setBadgeGroupId(pk);
	}

	public long getBadgeGroupId() {
		return _badgeGroupId;
	}

	public void setBadgeGroupId(long badgeGroupId) {
		_badgeGroupId = badgeGroupId;
	}

	public String getGroupName() {
		return _groupName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	private long _badgeGroupId;
	private String _groupName;
}