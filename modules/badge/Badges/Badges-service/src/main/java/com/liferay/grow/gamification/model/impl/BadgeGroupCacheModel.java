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

package com.liferay.grow.gamification.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.grow.gamification.model.BadgeGroup;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing BadgeGroup in entity cache.
 *
 * @author Vilmos Papp
 * @see BadgeGroup
 * @generated
 */
@ProviderType
public class BadgeGroupCacheModel implements CacheModel<BadgeGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BadgeGroupCacheModel)) {
			return false;
		}

		BadgeGroupCacheModel badgeGroupCacheModel = (BadgeGroupCacheModel)obj;

		if (badgeGroupId == badgeGroupCacheModel.badgeGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, badgeGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{badgeGroupId=");
		sb.append(badgeGroupId);
		sb.append(", groupName=");
		sb.append(groupName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BadgeGroup toEntityModel() {
		BadgeGroupImpl badgeGroupImpl = new BadgeGroupImpl();

		badgeGroupImpl.setBadgeGroupId(badgeGroupId);

		if (groupName == null) {
			badgeGroupImpl.setGroupName("");
		}
		else {
			badgeGroupImpl.setGroupName(groupName);
		}

		badgeGroupImpl.resetOriginalValues();

		return badgeGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		badgeGroupId = objectInput.readLong();
		groupName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(badgeGroupId);

		if (groupName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupName);
		}
	}

	public long badgeGroupId;
	public String groupName;
}