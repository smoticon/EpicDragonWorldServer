CREATE TABLE IF NOT EXISTS `character_items` (
  `owner` varchar(12) DEFAULT NULL,
  `slot` int(4) DEFAULT '0',
  `item` int(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
