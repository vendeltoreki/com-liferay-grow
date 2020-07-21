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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link BadgeGroup}.
 * </p>
 *
 * @author Vilmos Papp
 * @see BadgeGroup
 * @generated
 */
@ProviderType
public class BadgeGroupWrapper implements BadgeGroup, ModelWrapper<BadgeGroup> {
	public BadgeGroupWrapper(BadgeGroup badgeGroup) {
		_badgeGroup = badgeGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return BadgeGroup.class;
	}

	@Override
	public String getModelClassName() {
		return BadgeGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("badgeGroupId", getBadgeGroupId());
		attributes.put("groupName", getGroupName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long badgeGroupId = (Long)attributes.get("badgeGroupId");

		if (badgeGroupId != null) {
			setBadgeGroupId(badgeGroupId);
		}

		String groupName = (String)attributes.get("groupName");

		if (groupName != null) {
			setGroupName(groupName);
		}
	}

	@Override
	public Object clone() {
		return new BadgeGroupWrapper((BadgeGroup)_badgeGroup.clone());
	}

	@Override
	public int compareTo(BadgeGroup badgeGroup) {
		return _badgeGroup.compareTo(badgeGroup);
	}

	/**
	* Returns the badge group ID of this badge group.
	*
	* @return the badge group ID of this badge group
	*/
	@Override
	public long getBadgeGroupId() {
		return _badgeGroup.getBadgeGroupId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _badgeGroup.getExpandoBridge();
	}

	/**
	* Returns the group name of this badge group.
	*
	* @return the group name of this badge group
	*/
	@Override
	public String getGroupName() {
		return _badgeGroup.getGroupName();
	}

	/**
	* Returns the primary key of this badge group.
	*
	* @return the primary key of this badge group
	*/
	@Override
	public long getPrimaryKey() {
		return _badgeGroup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _badgeGroup.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _badgeGroup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _badgeGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _badgeGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _badgeGroup.isNew();
	}

	@Override
	public void persist() {
		_badgeGroup.persist();
	}

	/**
	* Sets the badge group ID of this badge group.
	*
	* @param badgeGroupId the badge group ID of this badge group
	*/
	@Override
	public void setBadgeGroupId(long badgeGroupId) {
		_badgeGroup.setBadgeGroupId(badgeGroupId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_badgeGroup.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_badgeGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_badgeGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_badgeGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group name of this badge group.
	*
	* @param groupName the group name of this badge group
	*/
	@Override
	public void setGroupName(String groupName) {
		_badgeGroup.setGroupName(groupName);
	}

	@Override
	public void setNew(boolean n) {
		_badgeGroup.setNew(n);
	}

	/**
	* Sets the primary key of this badge group.
	*
	* @param primaryKey the primary key of this badge group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_badgeGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_badgeGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BadgeGroup> toCacheModel() {
		return _badgeGroup.toCacheModel();
	}

	@Override
	public BadgeGroup toEscapedModel() {
		return new BadgeGroupWrapper(_badgeGroup.toEscapedModel());
	}

	@Override
	public String toString() {
		return _badgeGroup.toString();
	}

	@Override
	public BadgeGroup toUnescapedModel() {
		return new BadgeGroupWrapper(_badgeGroup.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _badgeGroup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BadgeGroupWrapper)) {
			return false;
		}

		BadgeGroupWrapper badgeGroupWrapper = (BadgeGroupWrapper)obj;

		if (Objects.equals(_badgeGroup, badgeGroupWrapper._badgeGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public BadgeGroup getWrappedModel() {
		return _badgeGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _badgeGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _badgeGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_badgeGroup.resetOriginalValues();
	}

	private final BadgeGroup _badgeGroup;
}