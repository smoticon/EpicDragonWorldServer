CREATE TABLE IF NOT EXISTS `spawnlist` (
  `npc_id` int(4) DEFAULT '0',
  `x` float DEFAULT '0',
  `y` float DEFAULT '0',
  `z` float DEFAULT '0',
  `heading` float DEFAULT '0',
  `respawn_delay` int(4) DEFAULT '60'
) CHARSET=latin1 COLLATE=latin1_general_ci;

INSERT INTO `spawnlist` VALUES ('1', '4542.18', '68.7411', '3844.77', '246.4', '60');
