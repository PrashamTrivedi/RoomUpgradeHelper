package com.prashamhtrivedi.roomupgradehelper.testData.first


//From https://github.com/magdamiu/AndroidRoom

const val a_1 = "{\n" +
        "  \"formatVersion\": 1,\n" +
        "  \"database\": {\n" +
        "    \"version\": 1,\n" +
        "    \"identityHash\": \"8e1698d161d676661d9adfcddf8db4fc\",\n" +
        "    \"entities\": [\n" +
        "      {\n" +
        "        \"tableName\": \"Company\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER NOT NULL, `name` TEXT, `date_updated` INTEGER, `latitude` REAL, `longitude` REAL, `hq_latitude` REAL, `hq_longitude` REAL, PRIMARY KEY(`id`))\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"itemUpdatedDate\",\n" +
        "            \"columnName\": \"date_updated\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.latitude\",\n" +
        "            \"columnName\": \"latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.longitude\",\n" +
        "            \"columnName\": \"longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.latitude\",\n" +
        "            \"columnName\": \"hq_latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.longitude\",\n" +
        "            \"columnName\": \"hq_longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": false\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Employee\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `company_id` INTEGER NOT NULL, FOREIGN KEY(`company_id`) REFERENCES `Company`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"employeeId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"company_id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": true\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": [\n" +
        "          {\n" +
        "            \"table\": \"Company\",\n" +
        "            \"onDelete\": \"CASCADE\",\n" +
        "            \"onUpdate\": \"NO ACTION\",\n" +
        "            \"columns\": [\n" +
        "              \"company_id\"\n" +
        "            ],\n" +
        "            \"referencedColumns\": [\n" +
        "              \"id\"\n" +
        "            ]\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Department\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `company_id` INTEGER NOT NULL)\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"id\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"company_id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": true\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Google\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`idGoogle` INTEGER NOT NULL, `addressGoogle` TEXT, `id` INTEGER NOT NULL, `name` TEXT, `date_updated` INTEGER, `latitude` REAL, `longitude` REAL, `hq_latitude` REAL, `hq_longitude` REAL, PRIMARY KEY(`id`))\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"idGoogle\",\n" +
        "            \"columnName\": \"idGoogle\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"addressGoogle\",\n" +
        "            \"columnName\": \"addressGoogle\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"itemUpdatedDate\",\n" +
        "            \"columnName\": \"date_updated\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.latitude\",\n" +
        "            \"columnName\": \"latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.longitude\",\n" +
        "            \"columnName\": \"longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.latitude\",\n" +
        "            \"columnName\": \"hq_latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.longitude\",\n" +
        "            \"columnName\": \"hq_longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": false\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      }\n" +
        "    ],\n" +
        "    \"setupQueries\": [\n" +
        "      \"CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)\",\n" +
        "      \"INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \\\"8e1698d161d676661d9adfcddf8db4fc\\\")\"\n" +
        "    ]\n" +
        "  }\n" +
        "}"

const val a_2="{\n" +
        "  \"formatVersion\": 1,\n" +
        "  \"database\": {\n" +
        "    \"version\": 2,\n" +
        "    \"identityHash\": \"0b2f3096f899a4c0537b93874d2eca72\",\n" +
        "    \"entities\": [\n" +
        "      {\n" +
        "        \"tableName\": \"Company\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER NOT NULL, `name` TEXT, `ref_no` TEXT, `date_updated` INTEGER, `latitude` REAL, `longitude` REAL, `hq_latitude` REAL, `hq_longitude` REAL, PRIMARY KEY(`id`))\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"refNumber\",\n" +
        "            \"columnName\": \"ref_no\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"itemUpdatedDate\",\n" +
        "            \"columnName\": \"date_updated\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.latitude\",\n" +
        "            \"columnName\": \"latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.longitude\",\n" +
        "            \"columnName\": \"longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.latitude\",\n" +
        "            \"columnName\": \"hq_latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.longitude\",\n" +
        "            \"columnName\": \"hq_longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": false\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Employee\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `company_id` INTEGER NOT NULL, FOREIGN KEY(`company_id`) REFERENCES `Company`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"employeeId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"company_id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": true\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": [\n" +
        "          {\n" +
        "            \"table\": \"Company\",\n" +
        "            \"onDelete\": \"CASCADE\",\n" +
        "            \"onUpdate\": \"NO ACTION\",\n" +
        "            \"columns\": [\n" +
        "              \"company_id\"\n" +
        "            ],\n" +
        "            \"referencedColumns\": [\n" +
        "              \"id\"\n" +
        "            ]\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Department\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `company_id` INTEGER NOT NULL)\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"id\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"company_id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": true\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Google\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`idGoogle` INTEGER NOT NULL, `addressGoogle` TEXT, `id` INTEGER NOT NULL, `name` TEXT, `ref_no` TEXT, `date_updated` INTEGER, `latitude` REAL, `longitude` REAL, `hq_latitude` REAL, `hq_longitude` REAL, PRIMARY KEY(`id`))\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"idGoogle\",\n" +
        "            \"columnName\": \"idGoogle\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"addressGoogle\",\n" +
        "            \"columnName\": \"addressGoogle\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"refNumber\",\n" +
        "            \"columnName\": \"ref_no\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"itemUpdatedDate\",\n" +
        "            \"columnName\": \"date_updated\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.latitude\",\n" +
        "            \"columnName\": \"latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.longitude\",\n" +
        "            \"columnName\": \"longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.latitude\",\n" +
        "            \"columnName\": \"hq_latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.longitude\",\n" +
        "            \"columnName\": \"hq_longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": false\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      }\n" +
        "    ],\n" +
        "    \"setupQueries\": [\n" +
        "      \"CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)\",\n" +
        "      \"INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \\\"0b2f3096f899a4c0537b93874d2eca72\\\")\"\n" +
        "    ]\n" +
        "  }\n" +
        "}"

const val a_3="{\n" +
        "  \"formatVersion\": 1,\n" +
        "  \"database\": {\n" +
        "    \"version\": 3,\n" +
        "    \"identityHash\": \"bd1da08dd595969f16211e8d597f0b12\",\n" +
        "    \"entities\": [\n" +
        "      {\n" +
        "        \"tableName\": \"Company\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER NOT NULL, `name` TEXT, `ref_no` TEXT, `tref_no` TEXT, `date_updated` INTEGER, `latitude` REAL, `longitude` REAL, `hq_latitude` REAL, `hq_longitude` REAL, PRIMARY KEY(`id`))\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"refNumber\",\n" +
        "            \"columnName\": \"ref_no\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"refNumberT\",\n" +
        "            \"columnName\": \"tref_no\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"itemUpdatedDate\",\n" +
        "            \"columnName\": \"date_updated\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.latitude\",\n" +
        "            \"columnName\": \"latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.longitude\",\n" +
        "            \"columnName\": \"longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.latitude\",\n" +
        "            \"columnName\": \"hq_latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.longitude\",\n" +
        "            \"columnName\": \"hq_longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": false\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Employee\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `company_id` INTEGER NOT NULL, FOREIGN KEY(`company_id`) REFERENCES `Company`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"employeeId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"company_id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": true\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": [\n" +
        "          {\n" +
        "            \"table\": \"Company\",\n" +
        "            \"onDelete\": \"CASCADE\",\n" +
        "            \"onUpdate\": \"NO ACTION\",\n" +
        "            \"columns\": [\n" +
        "              \"company_id\"\n" +
        "            ],\n" +
        "            \"referencedColumns\": [\n" +
        "              \"id\"\n" +
        "            ]\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Department\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `company_id` INTEGER NOT NULL)\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"id\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"company_id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": true\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      },\n" +
        "      {\n" +
        "        \"tableName\": \"Google\",\n" +
        "        \"createSql\": \"CREATE TABLE IF NOT EXISTS `${'$'}{TABLE_NAME}` (`idGoogle` INTEGER NOT NULL, `addressGoogle` TEXT, `id` INTEGER NOT NULL, `name` TEXT, `ref_no` TEXT, `tref_no` TEXT, `date_updated` INTEGER, `latitude` REAL, `longitude` REAL, `hq_latitude` REAL, `hq_longitude` REAL, PRIMARY KEY(`id`))\",\n" +
        "        \"fields\": [\n" +
        "          {\n" +
        "            \"fieldPath\": \"idGoogle\",\n" +
        "            \"columnName\": \"idGoogle\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"addressGoogle\",\n" +
        "            \"columnName\": \"addressGoogle\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"companyId\",\n" +
        "            \"columnName\": \"id\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": true\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"name\",\n" +
        "            \"columnName\": \"name\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"refNumber\",\n" +
        "            \"columnName\": \"ref_no\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"refNumberT\",\n" +
        "            \"columnName\": \"tref_no\",\n" +
        "            \"affinity\": \"TEXT\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"itemUpdatedDate\",\n" +
        "            \"columnName\": \"date_updated\",\n" +
        "            \"affinity\": \"INTEGER\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.latitude\",\n" +
        "            \"columnName\": \"latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"location.longitude\",\n" +
        "            \"columnName\": \"longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.latitude\",\n" +
        "            \"columnName\": \"hq_latitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          },\n" +
        "          {\n" +
        "            \"fieldPath\": \"headLocation.longitude\",\n" +
        "            \"columnName\": \"hq_longitude\",\n" +
        "            \"affinity\": \"REAL\",\n" +
        "            \"notNull\": false\n" +
        "          }\n" +
        "        ],\n" +
        "        \"primaryKey\": {\n" +
        "          \"columnNames\": [\n" +
        "            \"id\"\n" +
        "          ],\n" +
        "          \"autoGenerate\": false\n" +
        "        },\n" +
        "        \"indices\": [],\n" +
        "        \"foreignKeys\": []\n" +
        "      }\n" +
        "    ],\n" +
        "    \"setupQueries\": [\n" +
        "      \"CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)\",\n" +
        "      \"INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \\\"bd1da08dd595969f16211e8d597f0b12\\\")\"\n" +
        "    ]\n" +
        "  }\n" +
        "}"