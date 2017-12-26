CREATE TABLE IF NOT EXISTS `accounts` (
  `account` VARCHAR(45) NOT NULL DEFAULT '',
  `password` varchar(45), # MD5 hash
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_active` bigint(13) unsigned NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL, # 1 banned, 2 requires activation, 3 active
  `last_ip` CHAR(15) NULL DEFAULT NULL,
  `active_code` int(11) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
