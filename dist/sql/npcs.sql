CREATE TABLE IF NOT EXISTS `npcs` (
  `npc_id` int(4) DEFAULT '0',
  `type` varchar(10) DEFAULT 'Npc',
  `level` int(4) DEFAULT '1',
  `stamina` int(4) DEFAULT '10',
  `strength` int(4) DEFAULT '10',
  `dexterity` int(4) DEFAULT '10',
  `intelect` int(4) DEFAULT '10'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;