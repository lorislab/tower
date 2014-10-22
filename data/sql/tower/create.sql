
    create table TW_ST_ACTIVITY (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_DATE timestamp,
        C_BUILD varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_ACTIVITY_CHANGE (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_DESCRIPTION varchar(255),
        C_ERROR varchar(255),
        C_KEY varchar(255),
        C_PARENT varchar(255),
        C_STATUS varchar(255),
        C_TYPE varchar(255),
        C_USER varchar(255),
        C_ACTIVITY varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_ACTIVITY_LOG (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_DATE timestamp,
        C_MESSAGE varchar(255),
        C_REVISION varchar(255),
        C_USER varchar(255),
        C_BUILD varchar(255),
        C_CHANGE varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_AGENT (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_AUTH smallint,
        C_NAME varchar(255),
        C_PASSWORD varchar(255),
        C_TYPE varchar(255),
        C_URL varchar(255),
        C_USER varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_APP (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_ENABLED smallint,
        C_INDEX integer,
        C_KEY varchar(255),
        C_NAME varchar(255),
        C_NOTIFY smallint,
        C_REPO_LINK varchar(255),
        C_SCM_BRANCHES varchar(255),
        C_SCM_REPO varchar(255),
        C_SCM_TAG varchar(255),
        C_SCM_TRUNK varchar(255),
        C_SCM_TYPE varchar(255),
        C_TYPE varchar(255),
        C_PROJECT varchar(255),
        C_SCM varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_BTS (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_AUTH smallint,
        C_LINK varchar(255),
        C_NAME varchar(255),
        C_PASWORD varchar(255),
        C_SERVER varchar(255),
        C_TYPE varchar(255),
        C_USER varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_BUILD (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_AGENT varchar(255),
        C_MVN_ARTIFACT_ID varchar(255),
        C_BUILD varchar(255),
        C_DATE timestamp,
        C_MVN_GROUP_ID varchar(255),
        C_INSTALL timestamp,
        C_KEY varchar(255),
        C_MVN_VERSION varchar(255),
        C_SCM varchar(255),
        C_SERVICE varchar(255),
        C_UID varchar(255),
        C_VERSION integer,
        C_APP varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_BUILD_PARAM (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_NAME varchar(255),
        C_TYPE varchar(255),
        C_VALUE varchar(255),
        C_BUILD varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_NOTIFY_APP (
        C_APP_GUID varchar(255) not null,
        applications varchar(255)
    );

    create table TW_ST_NOTIFY_GROUP (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_ENABLED smallint,
        C_NAME varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_NOTIFY_SYS (
        C_SYSTEM_GUID varchar(255) not null,
        C_SYSTEMS varchar(255)
    );

    create table TW_ST_NOTIFY_USERS (
        C_USER_GUID varchar(255) not null,
        users varchar(255)
    );

    create table TW_ST_PROJECT (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_BTS_ID varchar(255),
        C_ENABLED smallint,
        C_INDEX integer,
        C_NAME varchar(255),
        C_BTS varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_SCM (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_AUTH smallint,
        C_CONN_TIMEOUT integer,
        C_LINK varchar(255),
        C_NAME varchar(255),
        C_PASWORD varchar(255),
        C_READ_TIMEOUT integer,
        C_SERVER varchar(255),
        C_TYPE varchar(255),
        C_USER varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_SYSTEM (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_CLASS varchar(255),
        C_DOMAIN varchar(255),
        C_ENABLED smallint,
        C_INDEX integer,
        C_KEY varchar(255),
        C_LINK varchar(255),
        C_NAME varchar(255),
        C_NOTIFY smallint,
        C_SERVICE varchar(255),
        C_TIMER smallint,
        C_AGENT varchar(255),
        C_APP varchar(255),
        primary key (C_GUID)
    );

    create table TW_ST_SYSTEM_BUILD (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_DATE timestamp,
        C_TYPE varchar(255),
        C_BUILD varchar(255),
        C_SYSTEM varchar(255),
        primary key (C_GUID)
    );

    alter table TW_ST_ACTIVITY 
        add constraint FK45CFE371A81325B9 
        foreign key (C_BUILD) 
        references TW_ST_BUILD;

    alter table TW_ST_ACTIVITY_CHANGE 
        add constraint FK59903EF159C421 
        foreign key (C_ACTIVITY) 
        references TW_ST_ACTIVITY;

    alter table TW_ST_ACTIVITY_LOG 
        add constraint FK4B8B1956A81325B9 
        foreign key (C_BUILD) 
        references TW_ST_BUILD;

    alter table TW_ST_ACTIVITY_LOG 
        add constraint FK4B8B1956659F6B12 
        foreign key (C_CHANGE) 
        references TW_ST_ACTIVITY_CHANGE;

    alter table TW_ST_APP 
        add constraint FK27D8C85F33D59626 
        foreign key (C_SCM) 
        references TW_ST_SCM;

    alter table TW_ST_APP 
        add constraint FK27D8C85FAF796BCF 
        foreign key (C_PROJECT) 
        references TW_ST_PROJECT;

    alter table TW_ST_BUILD 
        add constraint FK94D8792CF536A28E 
        foreign key (C_APP) 
        references TW_ST_APP;

    alter table TW_ST_BUILD_PARAM 
        add constraint FK230E6B9AA81325B9 
        foreign key (C_BUILD) 
        references TW_ST_BUILD;

    alter table TW_ST_NOTIFY_APP 
        add constraint FK51ED5BEDC8AAC8D0 
        foreign key (C_APP_GUID) 
        references TW_ST_NOTIFY_GROUP;

    alter table TW_ST_NOTIFY_SYS 
        add constraint FK51EDA099C15A9B4A 
        foreign key (C_SYSTEM_GUID) 
        references TW_ST_NOTIFY_GROUP;

    alter table TW_ST_NOTIFY_USERS 
        add constraint FK8D2128D45531FC2E 
        foreign key (C_USER_GUID) 
        references TW_ST_NOTIFY_GROUP;

    alter table TW_ST_PROJECT 
        add constraint FKA06B4257F2D5AF2D 
        foreign key (C_BTS) 
        references TW_ST_BTS;

    alter table TW_ST_SYSTEM 
        add constraint FK23761711A7EA1EE7 
        foreign key (C_AGENT) 
        references TW_ST_AGENT;

    alter table TW_ST_SYSTEM 
        add constraint FK23761711F536A28E 
        foreign key (C_APP) 
        references TW_ST_APP;

    alter table TW_ST_SYSTEM_BUILD 
        add constraint FKB78F7A0A81325B9 
        foreign key (C_BUILD) 
        references TW_ST_BUILD;

    alter table TW_ST_SYSTEM_BUILD 
        add constraint FKB78F7A044182AD2 
        foreign key (C_SYSTEM) 
        references TW_ST_SYSTEM;
