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
public class BadgeTypeTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"badgeTypeId", Types.BIGINT}, {"groupdId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.DATE},
		{"modifiedDate", Types.DATE}, {"type_", Types.VARCHAR},
		{"assignableFrom", Types.DATE}, {"assignableTo", Types.DATE},
		{"fileEntryId", Types.BIGINT}, {"system", Types.TINYINT},
		{"templateHTML", Types.LONGVARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>() {
		{
			put("badgeTypeId", Types.BIGINT);
			put("groupdId", Types.BIGINT);
			put("companyId", Types.BIGINT);
			put("userId", Types.BIGINT);
			put("userName", Types.VARCHAR);
			put("createDate", Types.DATE);
			put("modifiedDate", Types.DATE);
			put("type_", Types.VARCHAR);
			put("assignableFrom", Types.DATE);
			put("assignableTo", Types.DATE);
			put("fileEntryId", Types.BIGINT);
			put("system", Types.TINYINT);
			put("templateHTML", Types.LONGVARCHAR);
		}
	};

	public static final String TABLE_NAME = "BadgeType";

	public static final String[] TABLE_SQL_ADD_INDEXES = {};

	public static final String TABLE_SQL_ALTER = "alter table gamification_BadgeType add badgeGroupId bigint default 0";

	public static final String TABLE_SQL_CREATE = "create table gamification_BadgeType(badgeTypeId LONG not null primary key, groupId BIGINT, companyId BIGINT, userId BIGINT, userName VARCHAR, createdDate DATETIME, modifiedDate DATETIME, type_ VARCHAR, assignableFrom DATETIME, assignableTo DATETIME, fileEntryId BIGINT,system TINYINT, templateHTML LONGTEXT)";

	public static final String TABLE_SQL_DROP = "drop table gamification_BadgeType";

	public static final String TABLE_SQL_UPDATE_DATA = "update gamification_BadgeType set badgeGroupId=1 where type_ like '%Year Loyalty'";

}