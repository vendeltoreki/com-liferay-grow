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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Vilmos Papp
 */
public class BadgeUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeTables();
	}

	protected void upgradeTables() throws Exception {
		runSQL(BadgeGroupTable.TABLE_SQL_CREATE);
		runSQL(BadgeGroupTable.TABLE_SQL_ADD_INDEXES);
		runSQL(BadgeGroupTable.TABLE_SQL_ADD_DATA);
		runSQL(BadgeTypeTable.TABLE_SQL_ALTER);
		runSQL(BadgeTypeTable.TABLE_SQL_UPDATE_DATA);
	}

}