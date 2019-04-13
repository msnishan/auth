
INSERT INTO `auth`.`auth_user`
(`id`,
`pos_id`,
`created_by`,
`created_datetime`,
`is_enabled`,
`request_id`,
`updated_by`,
`updated_datetime`,
`version`,
`email`,
`employee_id`,
`designation`,
`password`)
VALUES
(3343,"1","ksaleh","2018-12-26 00:00:00","1","ab-req",NULL,NULL,1,"msnishan@gmail.com", "GP001", "MANAGER",
"{bcrypt}$2a$08$D70DyhCnzcfBkolC3uejFeuZBiaW/WBaAa.BKGG3omb4ZBJ48fgya");

INSERT INTO `auth`.`feature_access`
(`id`,
`created_by`,
`created_datetime`,
`is_enabled`,
`request_id`,
`updated_by`,
`updated_datetime`,
`version`,
`feature_id`,
`feature_name`,
`feature_url`,
`feature_method`)
VALUES
(3345,
"ksaleh",
"2018-12-26 00:00:00",
"1",
"ab-req",
NULL,
NULL,
1,
"TRAINER_ADD",
"Add a New Trainer",
"/company/api/trainer",
"POST");
INSERT INTO `auth`.`feature_access`
(`id`,
`created_by`,
`created_datetime`,
`is_enabled`,
`request_id`,
`updated_by`,
`updated_datetime`,
`version`,
`feature_id`,
`feature_name`,
`feature_url`,
`feature_method`)
VALUES
(3346,
"ksaleh",
"2018-12-26 00:00:00",
"1",
"ab-req",
NULL,
NULL,
1,
"RECEPTIONIST_ADD",
"Add a New Receptionist",
"/company/api/receptionist",
"POST");

INSERT INTO `auth`.`feature_access_employee`
(`id`,
`pos_id`,
`created_by`,
`created_datetime`,
`is_enabled`,
`request_id`,
`updated_by`,
`updated_datetime`,
`version`,
`feature_id`,
`employee_id`)
VALUES
(3349,"1","ksaleh","2018-12-26 00:00:00","1","ab-req",NULL,NULL,1,
"TRAINER_ADD",
"GP001");
INSERT INTO `auth`.`feature_access_employee`
(`id`,
`pos_id`,
`created_by`,
`created_datetime`,
`is_enabled`,
`request_id`,
`updated_by`,
`updated_datetime`,
`version`,
`feature_id`,
`employee_id`)
VALUES
(3350,"1","ksaleh","2018-12-26 00:00:00","1","ab-req",NULL,NULL,1,
"RECEPTIONIST_ADD",
"GP001");
INSERT INTO `auth`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
("service-gym","rest-api-user","{bcrypt}$2a$04$xl95D9bpcbIMqNyfHZnGYOYBUQma27Dsr.x/qAaYVompAR9man5Fu","write,read","password,authorization_code,refresh_token,implicit","http://localhost:8080/dashboard","USER",10800,2592000,NULL,"");
