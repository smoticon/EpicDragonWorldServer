CREATE TABLE IF NOT EXISTS `items` (
  `item_id` int(4) DEFAULT '0',
  `slot_id` int(4) DEFAULT '0',
  `type` enum('JUNK','EQUIP','CONSUME') DEFAULT 'JUNK' NOT NULL,
  `stackable` tinyint(1) UNSIGNED NOT NULL DEFAULT '0',
  `tradable` tinyint(1) UNSIGNED NOT NULL DEFAULT '0',
  `stamina` int(4) DEFAULT '0',
  `strength` int(4) DEFAULT '0',
  `dexterity` int(4) DEFAULT '0',
  `intelect` int(4) DEFAULT '0',
  `skill_id` int(4) DEFAULT '0',
  `skill_level` int(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;