package com.liferay.grow.gamification.service.upgrade.v1_0_1;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class BadgeGroupTable {
    public static final String TABLE_NAME = "BadgeGroup";

    public static final Object[][] TABLE_COLUMNS = {
            {"badgeGroupId", Types.BIGINT},
            {"groupName", Types.VARCHAR}
    };

    public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

    static {

        TABLE_COLUMNS_MAP.put("badgeGroupId", Types.BIGINT);

        TABLE_COLUMNS_MAP.put("groupName", Types.VARCHAR);
    }

    public static final String TABLE_SQL_CREATE = "create table gamification_BadgeGroup (badgeGroupId BIGINT not null primary key, groupName VARCHAR(75) not null)";

    public static final String TABLE_SQL_DROP = "drop table gamification_BadgeGroup";

    public static final String TABLE_SQL_ADD_DATA = "insert into gamification_BadgeGroup values(1,'Loyalty Badge')";

    public static final String[] TABLE_SQL_ADD_INDEXES = {
            "create unique index IX_62616467 on gamification_BadgeGroup (groupName)"
    };
}