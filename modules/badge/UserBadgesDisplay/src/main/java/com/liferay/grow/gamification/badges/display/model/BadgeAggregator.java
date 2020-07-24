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

package com.liferay.grow.gamification.badges.display.model;

/**
 * @author Vilmos Papp
 */
public class BadgeAggregator {

	public long getBadgeTypeId() {
		return _badgeTypeId;
	}

	public int getCount() {
		return _count;
	}

	public String getImage() {
		return _image;
	}

	public String getName() {
		return _name;
	}

	public void increaseCount() {
		_count++;
	}

	public void setBadgeTypeId(long badgeTypeId) {
		_badgeTypeId = badgeTypeId;
	}

	public void setCount(int count) {
		_count = count;
	}

	public void setImage(String image) {
		_image = image;
	}

	public void setName(String name) {
		_name = name;
	}

	@Override
	public String toString() {
		return "BadgeAggregator [_badgeTypeId=" + _badgeTypeId + ", _count=" +
			_count + ", _image=" + _image + ", _name=" + _name + "]";
	}

	private long _badgeTypeId;
	private int _count;
	private String _image;
	private String _name;

}