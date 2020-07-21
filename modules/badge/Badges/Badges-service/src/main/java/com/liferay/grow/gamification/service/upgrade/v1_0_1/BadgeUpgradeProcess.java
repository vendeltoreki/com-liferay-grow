package com.liferay.grow.gamification.service.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

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