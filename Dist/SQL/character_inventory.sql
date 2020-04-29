CREATE TABLE IF NOT EXISTS `character_inventory` (
  `owner` varchar(25) DEFAULT NULL,
  `item_id` int(4) DEFAULT 0,
  `equiped` int(11) DEFAULT 0,
  `amount` int(4) DEFAULT 0,
  `enchant` int(4) DEFAULT 0
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

