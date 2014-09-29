
    alter table TW_ST_ACTIVITY 
        drop constraint FK45CFE371A81325B9;

    alter table TW_ST_ACTIVITY_CHANGE 
        drop constraint FK59903EF159C421;

    alter table TW_ST_ACTIVITY_LOG 
        drop constraint FK4B8B1956A81325B9;

    alter table TW_ST_ACTIVITY_LOG 
        drop constraint FK4B8B1956659F6B12;

    alter table TW_ST_APP 
        drop constraint FK27D8C85F33D59626;

    alter table TW_ST_APP 
        drop constraint FK27D8C85FAF796BCF;

    alter table TW_ST_BUILD 
        drop constraint FK94D8792CF536A28E;

    alter table TW_ST_BUILD_PARAM 
        drop constraint FK230E6B9AA81325B9;

    alter table TW_ST_NOTIFY_APP 
        drop constraint FK51ED5BEDC8AAC8D0;

    alter table TW_ST_NOTIFY_SYS 
        drop constraint FK51EDA099C15A9B4A;

    alter table TW_ST_NOTIFY_USERS 
        drop constraint FK8D2128D45531FC2E;

    alter table TW_ST_PROJECT 
        drop constraint FKA06B4257F2D5AF2D;

    alter table TW_ST_SYSTEM 
        drop constraint FK23761711A7EA1EE7;

    alter table TW_ST_SYSTEM 
        drop constraint FK23761711F536A28E;

    alter table TW_ST_SYSTEM_BUILD 
        drop constraint FKB78F7A0A81325B9;

    alter table TW_ST_SYSTEM_BUILD 
        drop constraint FKB78F7A044182AD2;

    drop table TW_ST_ACTIVITY;

    drop table TW_ST_ACTIVITY_CHANGE;

    drop table TW_ST_ACTIVITY_LOG;

    drop table TW_ST_AGENT;

    drop table TW_ST_APP;

    drop table TW_ST_BTS;

    drop table TW_ST_BUILD;

    drop table TW_ST_BUILD_PARAM;

    drop table TW_ST_NOTIFY_APP;

    drop table TW_ST_NOTIFY_GROUP;

    drop table TW_ST_NOTIFY_SYS;

    drop table TW_ST_NOTIFY_USERS;

    drop table TW_ST_PROJECT;

    drop table TW_ST_SCM;

    drop table TW_ST_SYSTEM;

    drop table TW_ST_SYSTEM_BUILD;
