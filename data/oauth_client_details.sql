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
