
    create table BARN_ATTRIBUTE (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_NAME varchar(255),
        C_VALUE varchar(255),
        C_CONFIG varchar(255),
        primary key (C_GUID)
    );

    create table BARN_CONFIG (
        C_GUID varchar(255) not null,
        C_OPLOCK integer,
        C_TYPE varchar(255),
        primary key (C_GUID)
    );

    alter table BARN_ATTRIBUTE 
        add constraint FKED28C5F8F5E08610 
        foreign key (C_CONFIG) 
        references BARN_CONFIG;
