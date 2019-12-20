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

package com.liferay.social.activity.customizer.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CustomSocialActivitySet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomSocialActivitySet
 * @generated
 */
public class CustomSocialActivitySetWrapper
	extends BaseModelWrapper<CustomSocialActivitySet>
	implements CustomSocialActivitySet, ModelWrapper<CustomSocialActivitySet> {

	public CustomSocialActivitySetWrapper(
		CustomSocialActivitySet customSocialActivitySet) {

		super(customSocialActivitySet);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}
	}

	/**
	 * Returns the ID of this custom social activity set.
	 *
	 * @return the ID of this custom social activity set
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the primary key of this custom social activity set.
	 *
	 * @return the primary key of this custom social activity set
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a custom social activity set model instance should use the <code>CustomSocialActivitySet</code> interface instead.
	 */
	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ID of this custom social activity set.
	 *
	 * @param id the ID of this custom social activity set
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the primary key of this custom social activity set.
	 *
	 * @param primaryKey the primary key of this custom social activity set
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CustomSocialActivitySetWrapper wrap(
		CustomSocialActivitySet customSocialActivitySet) {

		return new CustomSocialActivitySetWrapper(customSocialActivitySet);
	}

}