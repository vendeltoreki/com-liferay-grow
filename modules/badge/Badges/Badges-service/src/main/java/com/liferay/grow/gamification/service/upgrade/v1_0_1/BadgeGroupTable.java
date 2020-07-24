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

package com.liferay.grow.gamification.service.upgrade.v1_0_1;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vilmos Papp
 */
public class BadgeGroupTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"badgeGroupId", Types.BIGINT}, {"groupName", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>() {
		{
			put("badgeGroupId", Types.BIGINT);
			put("groupName", Types.VARCHAR);
		}
	};

	public static final String TABLE_NAME = "BadgeGroup";

	public static final String TABLE_SQL_ADD_DATA = "insert into gamification_BadgeGroup values(1,'Loyalty Badge')";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_62616467 on gamification_BadgeGroup (groupName)"
	};

	public static final String TABLE_SQL_CREATE = "create table gamification_BadgeGroup (badgeGroupId BIGINT not null primary key, groupName VARCHAR(75) not null)";

	public static final String TABLE_SQL_DROP = "drop table gamification_BadgeGroup";

}