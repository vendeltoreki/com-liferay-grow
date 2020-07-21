package com.liferay.grow.gamification.service.upgrade.v1_0_1;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class BadgeTypeTable {
    public static final String TABLE_NAME = "BadgeType";

    public static final Object[][] TABLE_COLUMNS = {
            {"badgeTypeId", Types.BIGINT},
            {"groupdId", Types.BIGINT},
            {"companyId", Types.BIGINT},
            {"userId", Types.BIGINT},
            {"userName", Types.VARCHAR},
            {"createDate", Types.DATE},
            {"modifiedDate", Types.DATE},
            {"type_", Types.VARCHAR},
            {"assignableFrom", Types.DATE},
            {"assignableTo", Types.DATE},
            {"fileEntryId", Types.BIGINT},
            {"system", Types.TINYINT},
            {"templateHTML", Types.LONGVARCHAR},
    };

    public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

    static {

        TABLE_COLUMNS_MAP.put("badgeTypeId", Types.BIGINT);

        TABLE_COLUMNS_MAP.put("groupdId", Types.BIGINT);

        TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

        TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

        TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

        TABLE_COLUMNS_MAP.put("createDate", Types.DATE);

        TABLE_COLUMNS_MAP.put("modifiedDate", Types.DATE);

        TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);

        TABLE_COLUMNS_MAP.put("assignableFrom", Types.DATE);

        TABLE_COLUMNS_MAP.put("assignableTo", Types.DATE);

        TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);

        TABLE_COLUMNS_MAP.put("system", Types.TINYINT);

        TABLE_COLUMNS_MAP.put("templateHTML", Types.LONGVARCHAR);


    }

    public static final String TABLE_SQL_CREATE = "create table gamification_BadgeType(badgeTypeId LONG not null primary key, groupId BIGINT, companyId BIGINT, userId BIGINT, userName VARCHAR, createdDate DATETIME, modifiedDate DATETIME, type_ VARCHAR, assignableFrom DATETIME, assignableTo DATETIME, fileEntryId BIGINT,system TINYINT, templateHTML LONGTEXT)";

    public static final String TABLE_SQL_DROP = "drop table gamification_BadgeType";

    public static final String TABLE_SQL_ALTER = "alter table gamification_BadgeType add badgeGroupId bigint default 0";

    public static final String TABLE_SQL_UPDATE_DATA = "update gamification_BadgeType set badgeGroupId=1 where type_ like '%Year Loyalty'";

    public static final String[] TABLE_SQL_ADD_INDEXES = {

    };
}