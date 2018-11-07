CREATE TABLE IF NOT EXISTS `characters` (
  `account` varchar(20) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `slot` int(2) DEFAULT '0',
  `selected` tinyint(1) DEFAULT '1',
  `class_id` int(2) DEFAULT '0',
  `location_name` varchar(20) DEFAULT 'Starting Area',
  `x` float DEFAULT '9945.9',
  `y` float DEFAULT '9.2',
  `z` float DEFAULT '10534.9',
  `heading` float DEFAULT '0',
  `experience` int(8) DEFAULT '0',
  `hp` int(8) DEFAULT '1',
  `mp` int(8) DEFAULT '1',
  `access_level` int(2) DEFAULT '0',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
