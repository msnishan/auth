drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);

create table if not exists oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);
drop table if exists auth_user;
CREATE TABLE `auth_user` (
  `id` bigint(20) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_klvc3dss72qnlrjp2bai055mw` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists auth_address;
CREATE TABLE `auth_address` (
  `id` bigint(20) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) NOT NULL,
  `county` varchar(255) DEFAULT NULL,
  `iso_country` varchar(255) NOT NULL,
  `pin_code` bigint(20) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6tq8i9j835mjfl8xuarb72316` (`user_email`),
  CONSTRAINT `FK6tq8i9j835mjfl8xuarb72316` FOREIGN KEY (`user_email`) REFERENCES `auth_user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists auth_group;
CREATE TABLE `auth_group` (
  `id` bigint(20) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `group_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h2600eq48svty67oybnmrg4v8` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists auth_grant;
CREATE TABLE `auth_grant` (
  `id` bigint(20) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `grant_id` varchar(255) NOT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ol9m33apae0ygo9srfkcn9yqy` (`grant_id`),
  KEY `FKscnnf3s7awbrhbbw9mgq6s8uj` (`group_id`),
  CONSTRAINT `FKscnnf3s7awbrhbbw9mgq6s8uj` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists auth_user_grant;
CREATE TABLE `auth_user_grant` (
  `id` bigint(20) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `grant_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKh2osps5bpt6u2jif7wt7h0v5h` (`email`,`grant_id`),
  KEY `FKhxpwy3j19mjcn392t4tkpxcy1` (`grant_id`),
  CONSTRAINT `FKf8n47xg27r0kjqnawdwq42ppm` FOREIGN KEY (`email`) REFERENCES `auth_user` (`email`),
  CONSTRAINT `FKhxpwy3j19mjcn392t4tkpxcy1` FOREIGN KEY (`grant_id`) REFERENCES `auth_grant` (`grant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



