
    alter table GU_ROLE_PERM 
        drop constraint FKF555B708826E63EA;

    alter table GU_ROLE_PERM 
        drop constraint FKF555B708D030A00B;

    alter table GU_USER 
        drop constraint FK4248F87CC1DE04B2;

    alter table GU_USER 
        drop constraint FK4248F87C76906792;

    alter table GU_USER 
        drop constraint FK4248F87C399C6F5E;

    alter table GU_USER_ROLES 
        drop constraint FKBBF8A51AB502AAC7;

    drop table GU_PERMISSION;

    drop table GU_ROLE;

    drop table GU_ROLE_PERM;

    drop table GU_USER;

    drop table GU_USER_CONFIG;

    drop table GU_USER_PASSWORD;

    drop table GU_USER_PROFILE;

    drop table GU_USER_ROLES;
