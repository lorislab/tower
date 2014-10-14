
    create table GU_PERMISSION (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_ACTION varchar(255),
        C_CONTEXT varchar(255),
        C_ENABLED boolean,
        C_SYSTEM boolean,
        primary key (C_GUID)
    );

    create table GU_ROLE (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_ENABLED boolean,
        C_NAME varchar(255),
        C_SYSTEM boolean,
        primary key (C_GUID)
    );

    create table GU_ROLE_PERM (
        C_ROLE_GUID varchar(255) not null,
        C_PERM_GUID varchar(255) not null,
        primary key (C_ROLE_GUID, C_PERM_GUID)
    );

    create table GU_USER (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_CREATIONDATE timestamp,
        C_CREATIONUSER varchar(255),
        C_MODIFICATIONDATE timestamp,
        C_MODIFICATIONUSER varchar(255),
        C_ENABLED boolean,
        C_PRINCIPAL varchar(255),
        C_CONFIG_GUID varchar(255),
        C_PROFILE_GUID varchar(255),
        primary key (C_GUID)
    );

    create table GU_USER_CONFIG (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_KEY varchar(255),
        C_NOTIFY boolean,
        primary key (C_GUID)
    );

    create table GU_USER_PASSWORD (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_PASSWORD varchar(255),
        C_USER_GUID varchar(255),
        primary key (C_GUID)
    );

    create table GU_USER_PROFILE (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_CREATIONDATE timestamp,
        C_CREATIONUSER varchar(255),
        C_MODIFICATIONDATE timestamp,
        C_MODIFICATIONUSER varchar(255),
        C_EMAIL varchar(255),
        C_FIRSTNAME varchar(255),
        C_LASTNAME varchar(255),
        C_LANG varchar(255),
        C_MIDDLENAME varchar(255),
        primary key (C_GUID)
    );

    create table GU_USER_ROLES (
        C_USER_GUID varchar(255) not null,
        C_ROLE varchar(255)
    );

    alter table GU_ROLE_PERM 
        add constraint FKF555B708826E63EA 
        foreign key (C_PERM_GUID) 
        references GU_PERMISSION;

    alter table GU_ROLE_PERM 
        add constraint FKF555B708D030A00B 
        foreign key (C_ROLE_GUID) 
        references GU_ROLE;

    alter table GU_USER 
        add constraint FK4248F87C76906792 
        foreign key (C_CONFIG_GUID) 
        references GU_USER_CONFIG;

    alter table GU_USER 
        add constraint FK4248F87C399C6F5E 
        foreign key (C_PROFILE_GUID) 
        references GU_USER_PROFILE;

    alter table GU_USER_ROLES 
        add constraint FKBBF8A51AB502AAC7 
        foreign key (C_USER_GUID) 
        references GU_USER;
