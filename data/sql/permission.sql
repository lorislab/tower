-- ROLES	
INSERT INTO TOWER.GU_ROLE (C_GUID, C_OPLOCK, C_NAME, C_SYSTEM, C_ENABLED)  VALUES ('ADMIN', 1, 'tower_admin', true, true);
INSERT INTO TOWER.GU_ROLE (C_GUID, C_OPLOCK, C_NAME, C_SYSTEM, C_ENABLED)  VALUES ('ADMIN_USER', 1, 'tower_admin_user', true, true);
INSERT INTO TOWER.GU_ROLE (C_GUID, C_OPLOCK, C_NAME, C_SYSTEM, C_ENABLED)  VALUES ('DEPLOYER', 1, 'tower_deployer', true, true);
INSERT INTO TOWER.GU_ROLE (C_GUID, C_OPLOCK, C_NAME, C_SYSTEM, C_ENABLED)  VALUES ('USER', 1, 'tower_user', true, true);

-- PERMISSION
-- DASHBOARD
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('P1', 1, 'DASHBOARD', 'MENU', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'P1');
-- OTHER
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('P4', 1, 'LOGOUT', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('P5', 1, 'PROFILE', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('P6', 1, 'PASSWORD', 'MENU', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'P4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'P5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'P6');
-- DEPLOYMENT
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PD1', 1, 'DEPLOYMENT', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PD2', 1, 'DEPLOY', 'MENU', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('DEPLOYER', 'PD1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('DEPLOYER', 'PD2');
-- SETTINGS
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('P3', 1, 'SETTINGS', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA1', 1, 'PROJECT', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA2', 1, 'SYSTEM', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA3', 1, 'APPLICATION', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA4', 1, 'SCM', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA5', 1, 'BTS', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA6', 1, 'AGENT', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA7', 1, 'MAIL', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA8', 1, 'TIMER', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA20', 1, 'AD', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA21', 1, 'NOTIFY_GROUP', 'MENU', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'P3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA7');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA8');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA20');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA21');
-- USER MANAGEMENT
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA9', 1, 'USERMAN', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA10', 1, 'USER', 'MENU', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PA11', 1, 'AD_SEARCH', 'MENU', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA9');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA10');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PA11');
-- PROJECT
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PP1', 1, 'SEARCH', 'PROJECT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PP2', 1, 'EDIT', 'PROJECT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PP3', 1, 'CLOSE', 'PROJECT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PP4', 1, 'SAVE', 'PROJECT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PP5', 1, 'DELETE', 'PROJECT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PP6', 1, 'CREATE', 'PROJECT', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PP1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PP2');	
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PP3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PP4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PP5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PP6');
-- BTS
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB1', 1, 'SEARCH', 'BTS', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB2', 1, 'EDIT', 'BTS', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB3', 1, 'CLOSE', 'BTS', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB4', 1, 'SAVE', 'BTS', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB5', 1, 'DELETE', 'BTS', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB6', 1, 'CREATE', 'BTS', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PB7', 1, 'PASSWORD', 'BTS', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB2');	
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PB7');
 -- MAIL
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PM1', 1, 'EDIT', 'MAIL', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PM2', 1, 'SAVE', 'MAIL', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PM3', 1, 'PASSWORD', 'MAIL', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PM4', 1, 'TEST', 'MAIL', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PM1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PM2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PM3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PM4');
 -- TIMER
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PT1', 1, 'EDIT', 'TIMER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PT2', 1, 'SAVE', 'TIMER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PT3', 1, 'START', 'TIMER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PT4', 1, 'STOP', 'TIMER', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PT1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PT2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PT3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PT4');
 -- SCM
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS1', 1, 'SEARCH', 'SCM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS2', 1, 'EDIT', 'SCM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS3', 1, 'CLOSE', 'SCM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS4', 1, 'SAVE', 'SCM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS5', 1, 'DELETE', 'SCM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS6', 1, 'CREATE', 'SCM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PS7', 1, 'PASSWORD', 'SCM', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS2');	
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PS7');
 -- APPLICATION
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP1', 1, 'SEARCH', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP2', 1, 'EDIT', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP3', 1, 'CLOSE', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP4', 1, 'SAVE', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP5', 1, 'DELETE', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP6', 1, 'CREATE', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PAP7', 1, 'KEY', 'APPLICATION', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP2');	
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PAP7');
 -- SYSTEM
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY1', 1, 'SEARCH', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY2', 1, 'EDIT', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY3', 1, 'CLOSE', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY4', 1, 'SAVE', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY5', 1, 'DELETE', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY6', 1, 'CREATE', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PY7', 1, 'KEY', 'SYSTEM', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY2');	
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PY7');
 -- NOTIFICATION GROUP
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('NG1', 1, 'SEARCH', 'NOTIFY_GROUP', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('NG2', 1, 'EDIT', 'NOTIFY_GROUP', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('NG3', 1, 'CLOSE', 'NOTIFY_GROUP', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('NG4', 1, 'SAVE', 'NOTIFY_GROUP', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('NG5', 1, 'DELETE', 'NOTIFY_GROUP', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('NG6', 1, 'CREATE', 'NOTIFY_GROUP', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'NG1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'NG2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'NG3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'NG4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'NG5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'NG6');
 -- AGENT
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG1', 1, 'SEARCH', 'AGENT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG2', 1, 'EDIT', 'AGENT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG3', 1, 'CLOSE', 'AGENT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG4', 1, 'SAVE', 'AGENT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG5', 1, 'DELETE', 'AGENT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG6', 1, 'CREATE', 'AGENT', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PG7', 1, 'PASSWORD', 'AGENT', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG2');	
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PG7');
 -- AD
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PMA1', 1, 'EDIT', 'AD', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PMA2', 1, 'SAVE', 'AD', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PMA3', 1, 'PASSWORD', 'AD', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PMA1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PMA2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PMA3');
 -- AD SEARCH
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PMAS1', 1, 'SEARCH', 'AD_SEARCH', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PMAS2', 1, 'RESET', 'AD_SEARCH', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PMAS1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PMAS2');
 -- USER
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU1', 1, 'EDIT', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU2', 1, 'SAVE', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU3', 1, 'IMPORT', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU4', 1, 'CLOSE', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU5', 1, 'SAVE', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU6', 1, 'DELETE', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU7', 1, 'CREATE', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU8', 1, 'PASSWORD', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU9', 1, 'SEARCH', 'USER', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('PU10', 1, 'KEY', 'USER', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU4');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU5');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU6');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU7');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU8');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU9');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('ADMIN', 'PU10');
 -- PROFILE
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('UP1', 1, 'SAVE', 'PROFILE', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('UP2', 1, 'EDIT', 'PROFILE', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('UP3', 1, 'KEY', 'PROFILE', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('UP4', 1, 'PASSWORD', 'PROFILE', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'UP1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'UP2');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'UP3');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'UP4');
 -- PROFILE PASSWORD
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('UPP1', 1, 'SAVE', 'PASSWORD', true, true);
INSERT INTO TOWER.GU_PERMISSION (C_GUID, C_OPLOCK, C_ACTION, C_CONTEXT, C_ENABLED, C_SYSTEM)  VALUES ('UPP2', 1, 'EDIT', 'PASSWORD', true, true);
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'UPP1');
INSERT INTO TOWER.GU_ROLE_PERM (C_ROLE_GUID, C_PERM_GUID)  VALUES ('USER', 'UPP2');
