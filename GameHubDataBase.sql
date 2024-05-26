INSERT IGNORE INTO gamehub.game (`GAMEID`, `GAMENAME`) VALUES
(1, 'Stardew Valley'),
(2, 'The Witcher 3: Wild Hunt'),
(3, 'Red Dead Redemption 2'),
(4, 'Minecraft'),
(5, 'Dark Souls III'),
(6, 'Grand Theft Auto V'),
(7, 'Fortnite'),
(8, 'Overwatch'),
(9, 'Final Fantasy XV'),
(10, 'Persona 5'),
(11, 'God of War'),
(12, 'Bloodborne'),
(13, 'The Elder Scrolls V: Skyrim'),
(14, 'Rocket League'),
(15, 'The Last of Us Part II'),
(16, 'League of Legends'),
(17, 'Counter-Strike: Global Offensive'),
(18, 'Among Us'),
(19, 'FIFA 21'),
(20, 'Apex Legends'),
(21, 'Call of Duty: Warzone'),
(22, 'Animal Crossing: New Horizons'),
(23, 'Assassin\'s Creed Odyssey'),
(24, 'Dota 2'),
(25, 'Rainbow Six Siege'),
(26, 'Halo Infinite'),
(27, 'Genshin Impact'),
(28, 'Cyberpunk 2077'),
(29, 'Borderlands 3'),
(30, 'Monster Hunter: World'),
(31, 'Destiny 2'),
(32, 'Dead by Daylight'),
(33, 'The Elder Scrolls Online'),
(34, 'Terraria'),
(35, 'World of Warcraft'),
(36, 'Smash Bros. Ultimate'),
(37, 'Rust'),
(38, 'Hades'),
(39, 'RuneScape'),
(40, 'Mortal Kombat 11'),
(41, 'Sekiro: Shadows Die Twice'),
(42, 'Death Stranding'),
(43, 'Diablo III'),
(44, 'No Man\'s Sky'),
(45, 'Subnautica'),
(46, 'Star Wars Jedi: Fallen Order'),
(47, 'Baldur\'s Gate III'),
(48, 'Warframe'),
(49, 'Civilization VI'),
(50, 'Sea of Thieves'),
(51, 'Forza Horizon 4'),
(52, 'Gears 5'),
(53, 'Control'),
(54, 'The Outer Worlds'),
(55, 'Divinity: Original Sin 2'),
(56, 'Tom Clancy\'s The Division 2'),
(57, 'Cities: Skylines'),
(58, 'Stellaris'),
(59, 'Factorio'),
(60, 'Crusader Kings III'),
(61, 'Planet Zoo'),
(62, 'Ori and the Will of the Wisps'),
(63, 'Doom Eternal'),
(64, 'Disco Elysium'),
(65, 'Detroit: Become Human'),
(66, 'Resident Evil 2 Remake'),
(67, 'The Sims 4'),
(68, 'Celeste'),
(69, 'Outer Wilds'),
(70, 'Undertale'),
(71, 'Inside'),
(72, 'Limbo'),
(73, 'Braid'),
(74, 'Hyper Light Drifter'),
(75, 'Journey'),
(76, 'Cuphead'),
(77, 'Hollow Knight'),
(78, 'Spelunky 2'),
(79, 'Dead Cells'),
(80, 'Shovel Knight'),
(81, 'Enter the Gungeon'),
(82, 'Katana Zero'),
(83, 'Ori and the Blind Forest'),
(84, 'A Plague Tale: Innocence'),
(85, 'The Witness'),
(86, 'Firewatch'),
(87, 'What Remains of Edith Finch'),
(88, 'Gris'),
(89, 'Night in the Woods'),
(90, 'Life is Strange'),
(91, 'Oxenfree'),
(92, 'To the Moon'),
(93, 'Silent Hill 2'),
(94, 'Amnesia: The Dark Descent'),
(95, 'Resident Evil 7: Biohazard'),
(96, 'Dead Space'),
(97, 'Outlast'),
(98, 'The Last of Us'),
(99, 'Until Dawn'),
(100, 'Phasmophobia');

INSERT INTO `gamehub`.`gamedetails` (`DESCRIPTION`, `DEVELOPER`, `gameId`, `GAMENAME`, `PUBLISHER`, `RELEASEDATE`) 
VALUES 
('Stardew Valley is an open-ended country-life RPG!', 'ConcernedApe', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Stardew Valley'), 'Stardew Valley', 'ConcernedApe', '2016-02-26'),
('The Witcher 3: Wild Hunt is a story-driven open world RPG set in a dark fantasy universe.', 'CD Projekt Red', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Witcher 3: Wild Hunt'), 'The Witcher 3: Wild Hunt', 'CD Projekt', '2015-05-19'),
('Red Dead Redemption 2 is an open-world action-adventure game set in the western era', 'Rockstar Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Red Dead Redemption 2'), 'Red Dead Redemption 2', 'Rockstar Games', '2018-10-26'),
('Minecraft is a sandbox construction game', 'Mojang Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Minecraft'), 'Minecraft', 'Mojang Studios', '2011-11-18'),
('Dark Souls III is an action RPG', 'FromSoftware', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dark Souls III'), 'Dark Souls III', 'Bandai Namco Entertainment', '2016-04-12'),
('Grand Theft Auto V is an open-world action-adventure game', 'Rockstar North', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Grand Theft Auto V'), 'Grand Theft Auto V', 'Rockstar Games', '2013-09-17'),
('Fortnite is a battle royale game', 'Epic Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Fortnite'), 'Fortnite', 'Epic Games', '2017-07-25'),
('Overwatch is a first-person shooter game', 'Blizzard Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Overwatch'), 'Overwatch', 'Blizzard Entertainment', '2016-05-24'),
('Final Fantasy XV is an open-world RPG', 'Square Enix', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Final Fantasy XV'), 'Final Fantasy XV', 'Square Enix', '2016-11-29'),
('Persona 5 is a social simulation RPG', 'Atlus', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Persona 5'), 'Persona 5', 'Atlus', '2016-09-15'),
('God of War is an action-adventure game', 'Santa Monica Studio', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'God of War'), 'God of War', 'Sony Interactive Entertainment', '2018-04-20'),
('Bloodborne is an action RPG', 'FromSoftware', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Bloodborne'), 'Bloodborne', 'Sony Interactive Entertainment', '2015-03-24'),
('The Elder Scrolls V: Skyrim is an action RPG open-world game', 'Bethesda Game Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Elder Scrolls V: Skyrim'), 'The Elder Scrolls V: Skyrim', 'Bethesda Softworks', '2011-11-11'),
('Rocket League is a vehicular arcade sports game', 'Psyonix', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Rocket League'), 'Rocket League', 'Psyonix', '2015-07-07'),
('The Last of Us Part II is an action-adventure game', 'Naughty Dog', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Last of Us Part II'), 'The Last of Us Part II', 'Sony Interactive Entertainment', '2020-06-19'),
('League of Legends is a MOBA game', 'Riot Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'League of Legends'), 'League of Legends', 'Riot Games', '2009-10-27'),
('Counter-Strike: Global Offensive is a first-person shooter game', 'Valve', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Counter-Strike: Global Offensive'), 'Counter-Strike: Global Offensive', 'Valve', '2012-08-21'),
('Among Us is a party deduction game', 'Innersloth', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Among Us'), 'Among Us', 'Innersloth', '2018-11-16'),
('FIFA 21 is a sports simulation game', 'EA Vancouver', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'FIFA 21'), 'FIFA 21', 'EA Sports', '2020-10-09'),
('Apex Legends is a battle royale game', 'Respawn Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Apex Legends'), 'Apex Legends', 'Electronic Arts', '2019-02-04'),
('Call of Duty: Warzone is a battle royale game', 'Infinity Ward', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Call of Duty: Warzone'), 'Call of Duty: Warzone', 'Activision', '2020-03-10'),
('Animal Crossing: New Horizons is a life simulation game developed and published by Nintendo.', 'Nintendo EPD', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Animal Crossing: New Horizons'), 'Animal Crossing: New Horizons', 'Nintendo', '2020-03-20'),
('Assassin\'s Creed Odyssey is an action role-playing video game developed by Ubisoft Quebec and published by Ubisoft.', 'Ubisoft Quebec', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Assassin\'s Creed Odyssey'), 'Assassin\'s Creed Odyssey', 'Ubisoft', '2018-10-05'),
('Dota 2 is a multiplayer online battle arena (MOBA) video game developed and published by Valve Corporation.', 'Valve Corporation', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dota 2'), 'Dota 2', 'Valve Corporation', '2013-07-09'),
('Rainbow Six Siege is an online tactical shooter game developed by Ubisoft Montreal and published by Ubisoft.', 'Ubisoft Montreal', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Rainbow Six Siege'), 'Rainbow Six Siege', 'Ubisoft', '2015-12-01'),
('Halo Infinite is a first-person shooter game developed by 343 Industries and published by Xbox Game Studios.', '343 Industries', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Halo Infinite'), 'Halo Infinite', 'Xbox Game Studios', '2021-12-08'),
('Genshin Impact is an action role-playing game developed and published by miHoYo.', 'miHoYo', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Genshin Impact'), 'Genshin Impact', 'miHoYo', '2020-09-28'),
('Cyberpunk 2077 is an action role-playing game developed and published by CD Projekt.', 'CD Projekt Red', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Cyberpunk 2077'), 'Cyberpunk 2077', 'CD Projekt', '2020-12-10'),
('Borderlands 3 is a first-person shooter game developed by Gearbox Software and published by 2K Games.', 'Gearbox Software', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Borderlands 3'), 'Borderlands 3', '2K Games', '2019-09-13'),
('Monster Hunter: World is an action role-playing game developed and published by Capcom.', 'Capcom', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Monster Hunter: World'), 'Monster Hunter: World', 'Capcom', '2018-08-09'),
('Destiny 2 is a free-to-play online-only multiplayer first-person shooter game developed by Bungie.', 'Bungie', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Destiny 2'), 'Destiny 2', 'Bungie', '2017-09-06'),
('Dead by Daylight is an asymmetric survival horror game developed and published by Behaviour Interactive.', 'Behaviour Interactive', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dead by Daylight'), 'Dead by Daylight', 'Behaviour Interactive', '2016-06-14'),
('The Elder Scrolls Online is a massively multiplayer online role-playing game developed by ZeniMax Online Studios and published by Bethesda Softworks.', 'ZeniMax Online Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Elder Scrolls Online'), 'The Elder Scrolls Online', 'Bethesda Softworks', '2014-04-04'),
('Terraria is an action-adventure sandbox game developed by Re-Logic.', 'Re-Logic', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Terraria'), 'Terraria', 'Re-Logic', '2011-05-16'),
('World of Warcraft is a massively multiplayer online role-playing game developed and published by Blizzard Entertainment.', 'Blizzard Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'World of Warcraft'), 'World of Warcraft', 'Blizzard Entertainment', '2004-11-23'),
('Smash Bros. Ultimate is a crossover fighting game developed by Bandai Namco Studios and Sora Ltd. and published by Nintendo.', 'Bandai Namco Studios, Sora Ltd.', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Smash Bros. Ultimate'), 'Smash Bros. Ultimate', 'Nintendo', '2018-12-07'),
('Rust is a multiplayer-only survival video game developed and published by Facepunch Studios.', 'Facepunch Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Rust'), 'Rust', 'Facepunch Studios', '2018-02-08'),
('Hades is a roguelike dungeon crawler video game developed and published by Supergiant Games.', 'Supergiant Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Hades'), 'Hades', 'Supergiant Games', '2020-09-17'),
('RuneScape is a fantasy massively multiplayer online role-playing game developed and published by Jagex.', 'Jagex', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'RuneScape'), 'RuneScape', 'Jagex', '2001-01-04'),
('Mortal Kombat 11 is a fighting game developed by NetherRealm Studios and published by Warner Bros. Interactive Entertainment.', 'NetherRealm Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Mortal Kombat 11'), 'Mortal Kombat 11', 'Warner Bros. Interactive Entertainment', '2019-04-23'),
('Sekiro: Shadows Die Twice is an action-adventure game developed by FromSoftware and published by Activision.', 'FromSoftware', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Sekiro: Shadows Die Twice'), 'Sekiro: Shadows Die Twice', 'Activision', '2019-03-22'),
('Death Stranding is an action game developed by Kojima Productions and published by Sony Interactive Entertainment.', 'Kojima Productions', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Death Stranding'), 'Death Stranding', 'Sony Interactive Entertainment', '2019-11-08'),
('Diablo III is a hack-and-slash action role-playing game developed and published by Blizzard Entertainment.', 'Blizzard Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Diablo III'), 'Diablo III', 'Blizzard Entertainment', '2012-05-15'),
('No Man\'s Sky is a survival game developed and published by the indie studio Hello Games.', 'Hello Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'No Man\'s Sky'), 'No Man\'s Sky', 'Hello Games', '2016-08-09'),
('Subnautica is an open-world survival action-adventure game developed and published by Unknown Worlds Entertainment.', 'Unknown Worlds Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Subnautica'), 'Subnautica', 'Unknown Worlds Entertainment', '2018-01-23'),
('Star Wars Jedi: Fallen Order is an action-adventure game developed by Respawn Entertainment and published by Electronic Arts.', 'Respawn Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Star Wars Jedi: Fallen Order'), 'Star Wars Jedi: Fallen Order', 'Electronic Arts', '2019-11-15'),
('Baldur\'s Gate III is a role-playing video game developed and published by Larian Studios.', 'Larian Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Baldur\'s Gate III'), 'Baldur\'s Gate III', 'Larian Studios', '2020-10-06'),
('Warframe is a free-to-play action role-playing third-person shooter multiplayer online game developed and published by Digital Extremes.', 'Digital Extremes', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Warframe'), 'Warframe', 'Digital Extremes', '2013-03-25'),
('Civilization VI is a turn-based strategy 4X video game developed by Firaxis Games, published by 2K Games.', 'Firaxis Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Civilization VI'), 'Civilization VI', '2K Games', '2016-10-21'),
('Sea of Thieves is an action-adventure game developed by Rare and published by Xbox Game Studios.', 'Rare', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Sea of Thieves'), 'Sea of Thieves', 'Xbox Game Studios', '2018-03-20'),
('Forza Horizon 4 is a racing video game developed by Playground Games and published by Xbox Game Studios.', 'Playground Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Forza Horizon 4'), 'Forza Horizon 4', 'Xbox Game Studios', '2018-10-02'),
('Gears 5 is a third-person shooter video game developed by The Coalition and published by Xbox Game Studios.', 'The Coalition', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Gears 5'), 'Gears 5', 'Xbox Game Studios', '2019-09-10'),
('Control is an action-adventure video game developed by Remedy Entertainment and published by 505 Games.', 'Remedy Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Control'), 'Control', '505 Games', '2019-08-27'),
('The Outer Worlds is an action role-playing game developed by Obsidian Entertainment and published by Private Division.', 'Obsidian Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Outer Worlds'), 'The Outer Worlds', 'Private Division', '2019-10-25'),
('Divinity: Original Sin 2 is a role-playing video game developed and published by Larian Studios.', 'Larian Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Divinity: Original Sin 2'), 'Divinity: Original Sin 2', 'Larian Studios', '2017-09-14'),
('Tom Clancy\'s The Division 2 is an online action role-playing video game developed by Massive Entertainment and published by Ubisoft.', 'Massive Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Tom Clancy\'s The Division 2'), 'Tom Clancy\'s The Division 2', 'Ubisoft', '2019-03-15'),
('Cities: Skylines is a city-building game developed by Colossal Order and published by Paradox Interactive.', 'Colossal Order', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Cities: Skylines'), 'Cities: Skylines', 'Paradox Interactive', '2015-03-10'),
('Stellaris is a 4X grand strategy video game developed and published by Paradox Interactive.', 'Paradox Development Studio', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Stellaris'), 'Stellaris', 'Paradox Interactive', '2016-05-09'),
('Factorio is a construction and management simulation game developed by Wube Software.', 'Wube Software', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Factorio'), 'Factorio', 'Wube Software', '2021-08-14'),
('Crusader Kings III is a grand strategy game developed by Paradox Development Studio and published by Paradox Interactive.', 'Paradox Development Studio', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Crusader Kings III'), 'Crusader Kings III', 'Paradox Interactive', '2020-09-01'),
('Planet Zoo is a construction and management simulation video game developed and published by Frontier Developments.', 'Frontier Developments', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Planet Zoo'), 'Planet Zoo', 'Frontier Developments', '2019-11-05'),
('Ori and the Will of the Wisps is a platform-adventure Metroidvania video game developed by Moon Studios and published by Xbox Game Studios.', 'Moon Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Ori and the Will of the Wisps'), 'Ori and the Will of the Wisps', 'Xbox Game Studios', '2020-03-11'),
('Doom Eternal is a first-person shooter game developed by id Software and published by Bethesda Softworks.', 'id Software', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Doom Eternal'), 'Doom Eternal', 'Bethesda Softworks', '2020-03-20'),
('Disco Elysium is a role-playing video game developed and published by ZA/UM.', 'ZA/UM', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Disco Elysium'), 'Disco Elysium', 'ZA/UM', '2019-10-15'),
('Detroit: Become Human is an adventure game developed by Quantic Dream and published by Sony Interactive Entertainment.', 'Quantic Dream', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Detroit: Become Human'), 'Detroit: Become Human', 'Sony Interactive Entertainment', '2019-12-12'),
('Resident Evil 2 Remake is a survival horror game developed and published by Capcom.', 'Capcom', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Resident Evil 2 Remake'), 'Resident Evil 2 Remake', 'Capcom', '2019-01-25'),
('The Sims 4 is a life simulation game developed by Maxis and published by Electronic Arts.', 'Maxis', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Sims 4'), 'The Sims 4', 'Electronic Arts', '2014-09-02'),
('Celeste is a platforming video game developed by Maddy Makes Games.', 'Maddy Makes Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Celeste'), 'Celeste', 'Maddy Makes Games', '2018-01-25'),
('Outer Wilds is an open world exploration video game developed by Mobius Digital and published by Annapurna Interactive.', 'Mobius Digital', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Outer Wilds'), 'Outer Wilds', 'Annapurna Interactive', '2019-05-30'),
('Undertale is a role-playing video game developed and published by indie developer Toby Fox.', 'Toby Fox', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Undertale'), 'Undertale', 'Toby Fox', '2015-09-15'),
('Inside is a puzzle-platformer adventure game developed and published by Playdead.', 'Playdead', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Inside'), 'Inside', 'Playdead', '2016-06-29'),
('Limbo is a puzzle-platformer video game developed by independent studio Playdead.', 'Playdead', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Limbo'), 'Limbo', 'Playdead', '2010-07-21'),
('Braid is a puzzle-platformer video game developed by Number None, Inc.', 'Number None, Inc.', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Braid'), 'Braid', 'Number None, Inc.', '2008-08-06'),
('Hyper Light Drifter is an action-adventure game developed by Heart Machine.', 'Heart Machine', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Hyper Light Drifter'), 'Hyper Light Drifter', 'Heart Machine', '2016-03-31'),
('Journey is an indie adventure game developed by Thatgamecompany and published by Sony Computer Entertainment.', 'Thatgamecompany', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Journey'), 'Journey', 'Sony Computer Entertainment', '2012-03-13'),
('Cuphead is a run and gun indie video game developed and published by StudioMDHR.', 'StudioMDHR', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Cuphead'), 'Cuphead', 'StudioMDHR', '2017-09-29'),
('Hollow Knight is a Metroidvania video game developed and published by Team Cherry.', 'Team Cherry', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Hollow Knight'), 'Hollow Knight', 'Team Cherry', '2017-02-24'),
('Spelunky 2 is a roguelike platform video game developed by Mossmouth.', 'Mossmouth', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Spelunky 2'), 'Spelunky 2', 'Mossmouth', '2020-09-15'),
('Dead Cells is a roguelike-metroidvania hybrid video game developed and published by Motion Twin.', 'Motion Twin', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dead Cells'), 'Dead Cells', 'Motion Twin', '2018-08-07'),
('Shovel Knight is a 2D side-scrolling platform game developed and published by Yacht Club Games.', 'Yacht Club Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Shovel Knight'), 'Shovel Knight', 'Yacht Club Games', '2014-06-26'),
('Enter the Gungeon is a bullet hell roguelike video game developed by Dodge Roll and published by Devolver Digital.', 'Dodge Roll', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Enter the Gungeon'), 'Enter the Gungeon', 'Devolver Digital', '2016-04-05'),
('Katana Zero is an action-platformer video game developed by Askiisoft.', 'Askiisoft', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Katana Zero'), 'Katana Zero', 'Askiisoft', '2019-04-18'),
('Ori and the Blind Forest is a platform-adventure Metroidvania video game developed by Moon Studios.', 'Moon Studios', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Ori and the Blind Forest'), 'Ori and the Blind Forest', 'Microsoft Studios', '2015-03-11'),
('A Plague Tale: Innocence is an action-adventure horror stealth game developed by Asobo Studio and published by Focus Home Interactive.', 'Asobo Studio', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'A Plague Tale: Innocence'), 'A Plague Tale: Innocence', 'Focus Home Interactive', '2019-05-14'),
('The Witness is a puzzle video game developed and published by Thekla, Inc.', 'Thekla, Inc.', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Witness'), 'The Witness', 'Thekla, Inc.', '2016-01-26'),
('Firewatch is an adventure game developed by Campo Santo and published by Panic.', 'Campo Santo', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Firewatch'), 'Firewatch', 'Panic', '2016-02-09'),
('What Remains of Edith Finch is an interactive storytelling experience developed by Giant Sparrow and published by Annapurna Interactive.', 'Giant Sparrow', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'What Remains of Edith Finch'), 'What Remains of Edith Finch', 'Annapurna Interactive', '2017-04-25'),
('Gris is a platform-adventure game developed by Nomada Studio and published by Devolver Digital.', 'Nomada Studio', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Gris'), 'Gris', 'Devolver Digital', '2018-12-13'),
('Night in the Woods is an adventure game developed by Infinite Fall and published by Finji.', 'Infinite Fall', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Night in the Woods'), 'Night in the Woods', 'Finji', '2017-02-21'),
('Life is Strange is an episodic graphic adventure video game developed by Dontnod Entertainment and published by Square Enix.', 'Dontnod Entertainment', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Life is Strange'), 'Life is Strange', 'Square Enix', '2015-01-30'),
('Oxenfree is a supernatural thriller graphic adventure game developed and published by Night School Studio.', 'Night School Studio', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Oxenfree'), 'Oxenfree', 'Night School Studio', '2016-01-15'),
('To the Moon is an independent adventure RPG developed and published by Freebird Games.', 'Freebird Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'To the Moon'), 'To the Moon', 'Freebird Games', '2011-11-01'),
('Silent Hill 2 is a survival horror video game published by Konami for the PlayStation 2 and developed by Team Silent.', 'Team Silent', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Silent Hill 2'), 'Silent Hill 2', 'Konami', '2001-09-24'),
('Amnesia: The Dark Descent is a survival horror adventure video game developed by Frictional Games.', 'Frictional Games', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Amnesia: The Dark Descent'), 'Amnesia: The Dark Descent', 'Frictional Games', '2010-09-08'),
('Resident Evil 7: Biohazard is a survival horror video game developed and published by Capcom.', 'Capcom', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Resident Evil 7: Biohazard'), 'Resident Evil 7: Biohazard', 'Capcom', '2017-01-24'),
('Dead Space is a science fiction survival horror video game developed by EA Redwood Shores.', 'EA Redwood Shores', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dead Space'), 'Dead Space', 'Electronic Arts', '2008-10-14'),
('Outlast is a first-person survival horror video game developed and published by Red Barrels.', 'Red Barrels', (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Outlast'), 'Outlast', 'Red Barrels', '2013-09-04'),
('The Last of Us is an action-adventure survival horror game developed by Naughty Dog.','Naughty Dog',(SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Last of Us'),'The Last of Us','Sony Computer Entertainment','2013-06-14'),
('Until Dawn is an interactive drama survival horror adventure game developed by Supermassive Games.','Supermassive Games',(SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Until Dawn'),'Until Dawn','Sony Computer Entertainment','2015-08-25'),
('Phasmophobia is a 4 player online co-op psychological horror game.','Kinetic Games',(SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Phasmophobia'),'Phasmophobia','Kinetic Games','2020-09-18');

SET SQL_SAFE_UPDATES=0;
UPDATE `gamehub`.`gamedetails`
SET `STORAGE` = 
    CASE 
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Stardew Valley') THEN '750 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Witcher 3: Wild Hunt') THEN '60 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Red Dead Redemption 2') THEN '125 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Minecraft') THEN '600 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dark Souls III') THEN '27.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Grand Theft Auto V') THEN '75 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Fortnite') THEN '40 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Overwatch') THEN '35 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Final Fantasy XV') THEN '125 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Persona 5') THEN '25 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'God of War') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Bloodborne') THEN '27.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Elder Scrolls V: Skyrim') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Rocket League') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Last of Us Part II') THEN '65 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'League of Legends') THEN '11.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Counter-Strike: Global Offensive') THEN '17.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Among Us') THEN '500 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'FIFA 21') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Apex Legends') THEN '27.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Call of Duty: Warzone') THEN '110 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Animal Crossing: New Horizons') THEN '6.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Assassin\'s Creed Odyssey') THEN '50 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dota 2') THEN '17.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Rainbow Six Siege') THEN '65 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Halo Infinite') THEN 'valoarea estimată nu este disponibilă încă'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Genshin Impact') THEN '35 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Cyberpunk 2077') THEN '85 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Borderlands 3') THEN '80 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Monster Hunter: World') THEN '25 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Destiny 2') THEN '100 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dead by Daylight') THEN '22.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Elder Scrolls Online') THEN '90 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Terraria') THEN '350 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'World of Warcraft') THEN '85 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Smash Bros. Ultimate') THEN '17.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Rust') THEN '15 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Hades') THEN '9 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'RuneScape') THEN '4 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Mortal Kombat 11') THEN '55 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Sekiro: Shadows Die Twice') THEN '17.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Death Stranding') THEN '65 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Diablo III') THEN '27.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'No Man\'s Sky') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Subnautica') THEN '8 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Star Wars Jedi: Fallen Order') THEN '55 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Baldur\'s Gate III') THEN '75 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Warframe') THEN '35 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Civilization VI') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Sea of Thieves') THEN '55 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Forza Horizon 4') THEN '67.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Gears 5') THEN '67.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Control') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Outer Worlds') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Divinity: Original Sin 2') THEN '35 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Tom Clancy''s The Division 2') THEN '55 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Cities: Skylines') THEN '7.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Stellaris') THEN '7.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Factorio') THEN '1.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Crusader Kings III') THEN '4 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Planet Zoo') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Ori and the Will of the Wisps') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Doom Eternal') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Disco Elysium') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Detroit: Become Human') THEN '55 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Resident Evil 2 Remake') THEN '25 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Sims 4') THEN '35 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Celeste') THEN '1.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Outer Wilds') THEN '7.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Undertale') THEN '350 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Inside') THEN '4 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Limbo') THEN '325 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Braid') THEN '325 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Hyper Light Drifter') THEN '750 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Journey') THEN '4 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Cuphead') THEN '7.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Hollow Knight') THEN '4.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Spelunky 2') THEN '750 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dead Cells') THEN '750 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Shovel Knight') THEN '350 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Enter the Gungeon') THEN '750 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Katana Zero') THEN '350 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Ori and the Blind Forest') THEN '9 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'A Plague Tale: Innocence') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Witness') THEN '4.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Firewatch') THEN '4.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'What Remains of Edith Finch') THEN '4.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Gris') THEN '2.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Night in the Woods') THEN '1.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Life is Strange') THEN '12.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Oxenfree') THEN '2.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'To the Moon') THEN '350 MB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Silent Hill 2') THEN '2.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Amnesia: The Dark Descent') THEN '4 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Resident Evil 7: Biohazard') THEN '25 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Dead Space') THEN '7.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Outlast') THEN '4.5 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'The Last of Us') THEN '45 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Until Dawn') THEN '55 GB'
        WHEN `GAMEID` = (SELECT `GAMEID` FROM `gamehub`.`game` WHERE `GAMENAME` = 'Phasmophobia') THEN '4.5 GB'
    END;
    
INSERT INTO gamehub.pricedetails (gameId, price, discount, discount_price)
VALUES
    (1, 13.99, 0, 0),
    (2, 30.99, 0, 0),
    (3, 59.99, 0, 0),
    (4, 20, 0, 0),
    (5, 29.99, 0, 0),
    (6, 4.98, 0, 0),
    (7, 0, 0, 0),
    (8, 0, 0, 0),
    (9, 34.99, 0, 0),
    (10, 59.99, 0, 0),
    (11, 50, 0, 0),
    (12, 29.99, 0, 0),
    (13, 39.99, 0, 0),
    (14, 0, 0, 0),
    (15, 70, 0, 0),
    (16, 0, 0, 0),
    (17, 0, 0, 0),
    (18, 4.49, 0, 0),
    (19, 69.99, 0, 0),
    (20, 0, 0, 0),
    (21, 0, 0, 0),
    (22, 0, 0, 0),
    (23, 59.99, 0, 0),
    (24, 0, 0, 0),
    (25, 19.99, 0, 0),
    (26, 0, 0, 0),
    (27, 0, 0, 0),
    (28, 59.99, 0, 0),
    (29, 59.99, 0, 0),
    (30, 29.99, 0, 0),
    (31, 0, 0, 0),
    (32, 19.99, 0, 0),
    (33, 19.99, 0, 0),
    (34, 9.75, 0, 0),
    (35, 0, 0, 0),
    (36, 0, 0, 0),
    (37, 39.99, 0, 0),
    (38, 24.50, 0, 0),
    (39, 0, 0, 0),
    (40, 59.99, 0, 0),
    (41, 59.00, 0, 0),
    (42, 0, 0, 0),
    (43, 34.99, 0, 0),
    (44, 58.99, 0, 0),
    (45, 29.99, 0, 0),
    (46, 39.99, 0, 0),
    (47, 59.99, 0, 0),
    (48, 0, 0, 0),
    (49, 59.99, 0, 0),
    (50, 39.99, 0, 0),
    (51, 54.99, 0, 0),
    (52, 29.99, 0, 0),
    (53, 39.99, 0, 0),
    (54, 29.99, 0, 0),
    (55, 44.99, 0, 0),
    (56, 0, 0, 0),
    (57, 8.39, 0, 0),
    (58, 11.99, 0, 0),
    (59, 32.49, 0, 0),
    (60, 19.99, 0, 0),
    (61, 44.99, 0, 0),
    (62, 29.99, 0, 0),
    (63, 39.99, 0, 0),
    (64, 9.99, 0, 0),
    (65, 39.99, 0, 0),
    (66, 64.99, 0, 0),
    (67, 0, 0, 0),
    (68, 19.50, 0, 0),
    (69, 22.99, 0, 0),
    (70, 9.99, 0, 0),
    (71, 19.99, 0, 0),
    (72, 9.99, 0, 0),
    (73, 15.60, 0, 0),
    (74, 14.99, 0, 0),
    (75, 13.99, 0, 0),
    (76, 19.99, 0, 0),
    (77, 14.79, 0, 0),
    (78, 8.39, 0, 0),
    (79, 12.49, 0, 0),
    (80, 39.99, 0, 0),
    (81, 4.43, 0, 0),
    (82, 14.79, 0, 0),
    (83, 0, 0, 0),
    (84, 7.99, 0, 0),
    (85, 36.99, 0, 0),
    (86, 19.50, 0, 0),
    (87, 18.49, 0, 0),
    (88, 2.95, 0, 0),
    (89, 9.75, 0, 0),
    (90, 19.99, 0, 0),
    (91, 9.99, 0, 0),
    (92, 9.75, 0, 0),
    (93, 0, 0, 0),
    (94, 19.50, 0, 0),
    (95, 19.99, 0, 0),
    (96, 59.99, 0, 0),
    (97, 19.50, 0, 0),
    (98, 59.99, 0, 0),
    (99, 0, 0, 0),
    (100, 11.59, 0, 0);
INSERT IGNORE INTO gamehub.gamepg (`ID`, `TYPE`, `GAMEID`) VALUES
(1, 'PEGI7', 1),
(2, 'PEGI18', 2),
(3, 'PEGI18', 3),
(4, 'PEGI7', 4),
(5, 'PEGI16', 5),
(6, 'PEGI18', 6),
(7, 'PEGI12', 7),
(8, 'PEGI12', 8),
(9, 'PEGI16', 9),
(10, 'PEGI16', 10),
(11, 'PEGI18', 11),
(12, 'PEGI16', 12),
(13, 'PEGI18', 13),
(14, 'PEGI3', 14),
(15, 'PEGI18', 15),
(16, 'PEGI12', 16),
(17, 'PEGI18', 17),
(18, 'PEGI7', 18),
(19, 'PEGI3', 19),
(20, 'PEGI16', 20),
(21, 'PEGI18', 21),
(22, 'PEGI3', 22),
(23, 'PEGI18', 23),
(24, 'PEGI12', 24),
(25, 'PEGI18', 25),
(26, 'PEGI16', 26),
(27, 'PEGI12', 27),
(28, 'PEGI18', 28),
(29, 'PEGI18', 29),
(30, 'PEGI16', 30),
(31, 'PEGI16', 31),
(32, 'PEGI18', 32),
(33, 'PEGI18', 33),
(34, 'PEGI12', 34),
(35, 'PEGI12', 35),
(36, 'PEGI12', 36),
(37, 'PEGI18', 37),
(38, 'PEGI12', 38),
(39, 'PEGI12', 39),
(40, 'PEGI18', 40),
(41, 'PEGI18', 41),
(42, 'PEGI18', 42),
(43, 'PEGI16', 43),
(44, 'PEGI7', 44),
(45, 'PEGI7', 45),
(46, 'PEGI16', 46),
(47, 'PEGI16', 47),
(48, 'PEGI18', 48),
(49, 'PEGI12', 49),
(50, 'PEGI12', 50),
(51, 'PEGI3', 51),
(52, 'PEGI18', 52),
(53, 'PEGI16', 53),
(54, 'PEGI18', 54),
(55, 'PEGI16', 55),
(56, 'PEGI18', 56),
(57, 'PEGI3', 57),
(58, 'PEGI7', 58),
(59, 'PEGI7', 59),
(60, 'PEGI12', 60),
(61, 'PEGI3', 61),
(62, 'PEGI7', 62),
(63, 'PEGI18', 63),
(64, 'PEGI18', 64),
(65, 'PEGI18', 65),
(66, 'PEGI18', 66),
(67, 'PEGI12', 67),
(68, 'PEGI3', 68),
(69, 'PEGI12', 69),
(70, 'PEGI12', 70),
(71, 'PEGI16', 71),
(72, 'PEGI12', 72),
(73, 'PEGI7', 73),
(74, 'PEGI7', 74),
(75, 'PEGI7', 75),
(76, 'PEGI3', 76),
(77, 'PEGI7', 77),
(78, 'PEGI7', 78),
(79, 'PEGI12', 79),
(80, 'PEGI7', 80),
(81, 'PEGI12', 81),
(82, 'PEGI16', 82),
(83, 'PEGI7', 83),
(84, 'PEGI18', 84),
(85, 'PEGI3', 85),
(86, 'PEGI16', 86),
(87, 'PEGI16', 87),
(88, 'PEGI12', 88),
(89, 'PEGI12', 89),
(90, 'PEGI16', 90),
(91, 'PEGI12', 91),
(92, 'PEGI12', 92),
(93, 'PEGI18', 93),
(94, 'PEGI18', 94),
(95, 'PEGI18', 95),
(96, 'PEGI18', 96),
(97, 'PEGI18', 97),
(98, 'PEGI18', 98),
(99, 'PEGI18', 99),
(100, 'PEGI16', 100);

INSERT INTO gamehub.category (`categoryId`, `categoryName`) VALUES
(1, 'Simulation'),
(2, 'RPG'),
(3, 'Indie'),
(4, 'Action'),
(5, 'Open-world'),
(6, 'Western'),
(7, 'Sandbox'),
(8, 'Survival'),
(9, 'Adventure'),
(10, 'Dark Fantasy'),
(11, 'Crime'),
(12, 'Battle Royale'),
(13, 'Shooter'),
(14, 'Multiplayer'),
(15, 'FPS'),
(16, 'Fantasy'),
(17, 'Anime'),
(18, 'Mythology'),
(19, 'Sports'),
(20, 'Sci-fi'),
(21, 'Strategy'),
(22, 'Co-op'),
(23, 'Piracy'),
(24, 'Racing'),
(25, 'Supernatural'),
(26, 'Detective'),
(27, 'Narrative'),
(28, 'Puzzle'),
(29, 'Horror'),
(30, 'Retro'),
(31, 'Stealth'),
(32, 'Story-rich'),
(33, 'Episodic'),
(34, 'Mystery'),
(35, 'Single-Player');

INSERT IGNORE INTO gamehub.game_category (`GAMEID`, `CATEGORYID`) VALUES
-- Stardew Valley
(1, 1), -- Simulation
(1, 9), -- Adventure
(1, 22), -- Retro

-- The Witcher 3: Wild Hunt
(2, 2), -- RPG
(2, 16), -- Fantasy
(2, 33), -- Episodic

-- Red Dead Redemption 2
(3, 4), -- Action
(3, 6), -- Western
(3, 9), -- Adventure

-- Minecraft
(4, 3), -- Indie
(4, 7), -- Sandbox
(4, 14), -- Multiplayer

-- Dark Souls III
(5, 2), -- RPG
(5, 10), -- Dark Fantasy
(5, 29), -- Horror

-- Grand Theft Auto V
(6, 4), -- Action
(6, 5), -- Open-World
(6, 14), -- Multiplayer

-- Fortnite
(7, 4), -- Action
(7, 14), -- Multiplayer
(7, 34), -- Mystery

-- Overwatch
(8, 4), -- Action
(8, 14), -- Multiplayer
(8, 15), -- FPS

-- Final Fantasy XV
(9, 2), -- RPG
(9, 16), -- Fantasy
(9, 22), -- Co-op

-- Persona 5
(10, 2), -- RPG
(10, 17), -- Anime
(10, 32), -- Story-rich

-- God of War
(11, 2), -- RPG
(11, 16), -- Fantasy
(11, 22), -- Co-op

-- Bloodborne
(12, 2), -- RPG
(12, 10), -- Dark Fantasy
(12, 29), -- Horror

-- The Elder Scrolls V: Skyrim
(13, 2), -- RPG
(13, 5), -- Open-world
(13, 33), -- Episodic

-- Rocket League
(14, 19), -- Sports
(14, 14), -- Multiplayer
(14, 28), -- Puzzle

-- The Last of Us Part II
(15, 4), -- Action
(15, 27), -- Narrative
(15, 33), -- Episodic

-- League of Legends
(16, 21), -- Strategy
(16, 14), -- Multiplayer
(16, 17), -- Anime

-- Counter-Strike: Global Offensive
(17, 15), -- FPS
(17, 14), -- Multiplayer
(17, 21), -- Strategy

-- Among Us
(18, 14), -- Multiplayer
(18, 32), -- Story-rich
(18, 33), -- Episodic

-- FIFA 21
(19, 19), -- Sports
(19, 14), -- Multiplayer
(19, 35), -- Single-Player

-- Apex Legends
(20, 13), -- Shooter
(20, 14), -- Multiplayer
(20, 15), -- FPS

-- Call of Duty: Warzone
(21, 13), -- Shooter
(21, 14), -- Multiplayer
(21, 15), -- FPS

-- Animal Crossing: New Horizons
(22, 3), -- Indie
(22, 7), -- Sandbox
(22, 18), -- Mythology

-- Assassin's Creed Odyssey
(23, 4), -- Action
(23, 9), -- Adventure
(23, 16), -- Fantasy

-- Dota 2
(24, 13), -- Shooter
(24, 14), -- Multiplayer
(24, 21), -- Strategy

-- Rainbow Six Siege
(25, 13), -- Shooter
(25, 14), -- Multiplayer
(25, 31), -- Stealth

-- Halo Infinite
(26, 13), -- Shooter
(26, 14), -- Multiplayer
(26, 20), -- Sci-fi

-- Genshin Impact
(27, 2), -- RPG
(27, 14), -- Multiplayer
(27, 16), -- Fantasy

-- Cyberpunk 2077
(28, 2), -- RPG
(28, 20), -- Sci-fi
(28, 33), -- Episodic

-- Borderlands 3
(29, 2), -- RPG
(29, 20), -- Sci-fi
(29, 14), -- Multiplayer

-- Monster Hunter: World
(30, 2), -- RPG
(30, 8), -- Survival
(30, 20), -- Sci-fi

-- Destiny 2
(31, 2), -- RPG
(31, 20), -- Sci-fi
(31, 14), -- Multiplayer

-- Dead by Daylight
(32, 9), -- Adventure
(32, 13), -- Shooter
(32, 29), -- Horror

-- The Elder Scrolls Online
(33, 2), -- RPG
(33, 20), -- Sci-fi
(33, 14), -- Multiplayer

-- Terraria
(34, 3), -- Indie
(34, 7), -- Sandbox
(34, 14), -- Multiplayer

-- World of Warcraft
(35, 2), -- RPG
(35, 20), -- Sci-fi
(35, 14), -- Multiplayer

-- Smash Bros. Ultimate
(36, 14), -- Multiplayer
(36, 17), -- Anime
(36, 30), -- Retro

-- Rust
(37, 4), -- Action
(37, 7), -- Sandbox
(37, 26), -- Detective

-- Hades
(38, 2), -- RPG
(38, 14), -- Multiplayer
(38, 15), -- FPS

-- RuneScape
(39, 2), -- RPG
(39, 20), -- Sci-fi
(39, 35), -- Single-Player

-- Mortal Kombat 11
(40, 4), -- Action
(40, 14), -- Multiplayer
(40, 28), -- Puzzle

-- Sekiro: Shadows Die Twice
(41, 4), -- Action
(41, 14), -- Multiplayer
(41, 31), -- Stealth

-- Death Stranding
(42, 20), -- Sci-fi
(42, 26), -- Detective
(42, 27), -- Narrative

-- Diablo III
(43, 2), -- RPG
(43, 20), -- Sci-fi
(43, 26), -- Detective

-- No Man's Sky
(44, 20), -- Sci-fi
(44, 7), -- Sandbox
(44, 33), -- Episodic

-- Subnautica
(45, 8), -- Survival
(45, 20), -- Sci-fi
(45, 14), -- Multiplayer

-- Star Wars Jedi: Fallen Order
(46, 4), -- Action
(46, 20), -- Sci-fi
(46, 16), -- Fantasy

-- Baldur's Gate III
(47, 2), -- RPG
(47, 7), -- Sandbox
(47, 18), -- Mythology

-- Warframe
(48, 20), -- Sci-fi
(48, 14), -- Multiplayer
(48, 1), -- Simulation

-- Civilization VI
(49, 21), -- Strategy
(49, 14), -- Multiplayer
(49, 18), -- Mythology

-- Sea of Thieves
(50, 4), -- Action
(50, 14), -- Multiplayer
(50, 23), -- Piracy

-- Forza Horizon 4
(51, 4), -- Action
(51, 19), -- Sports
(51, 24), -- Racing

-- Gears 5
(52, 4), -- Action
(52, 14), -- Multiplayer
(52, 20), -- Sci-fi

-- Control
(53, 4), -- Action
(53, 13), -- Shooter
(53, 27), -- Narrative

-- The Outer Worlds
(54, 2), -- RPG
(54, 20), -- Sci-fi
(54, 32), -- Story-rich

-- Divinity: Original Sin 2
(55, 2), -- RPG
(55, 20), -- Sci-fi
(55, 21), -- Strategy

-- Tom Clancy's The Division 2
(56, 4), -- Action
(56, 13), -- Shooter
(56, 14), -- Multiplayer

-- Cities: Skylines
(57, 3), -- Indie
(57, 7), -- Sandbox
(57, 21), -- Strategy

-- Stellaris
(58, 21), -- Strategy
(58, 20), -- Sci-fi
(58, 18), -- Mythology

-- Factorio
(59, 21), -- Strategy
(59, 7), -- Sandbox
(59, 14), -- Multiplayer

-- Crusader Kings III
(60, 21), -- Strategy
(60, 16), -- Fantasy
(60, 32), -- Story-rich

-- Planet Zoo
(61, 3), -- Indie
(61, 7), -- Sandbox
(61, 19), -- Sports

-- Ori and the Will of the Wisps
(62, 3), -- Indie
(62, 9), -- Adventure
(62, 16), -- Fantasy

-- Doom Eternal
(63, 4), -- Action
(63, 13), -- Shooter
(63, 14), -- Multiplayer

-- Disco Elysium
(64, 2), -- RPG
(64, 26), -- Detective
(64, 27), -- Narrative

-- Detroit: Become Human
(65, 2), -- RPG
(65, 26), -- Detective
(65, 27), -- Narrative

-- Resident Evil 2 Remake
(66, 4), -- Action
(66, 13), -- Shooter
(66, 29), -- Horror

-- The Sims 4
(67, 3), -- Indie
(67, 27), -- Narrative
(67, 35), -- Single-Player

-- Celeste
(68, 14), -- Multiplayer
(68, 28), -- Puzzle
(68, 29), -- Horror

-- Outer Wilds
(69, 9), -- Adventure
(69, 20), -- Sci-fi
(69, 32), -- Story-rich

-- Undertale
(70, 14), -- Multiplayer
(70, 28), -- Puzzle
(70, 33), -- Episodic

-- Inside
(71, 14), -- Multiplayer
(71, 32), -- Story-rich
(71, 29), -- Horror

-- Limbo
(72, 14), -- Multiplayer
(72, 32), -- Story-rich
(72, 29), -- Horror

-- Braid
(73, 14), -- Multiplayer
(73, 28), -- Puzzle
(73, 32), -- Story-rich

-- Hyper Light Drifter
(74, 14), -- Multiplayer
(74, 28), -- Puzzle
(74, 20), -- Sci-fi

-- Journey
(75, 14), -- Multiplayer
(75, 27), -- Narrative
(75, 32), -- Story-rich

-- Cuphead
(76, 14), -- Multiplayer
(76, 28), -- Puzzle
(76, 30), -- Retro

-- Hollow Knight
(77, 14), -- Multiplayer
(77, 16), -- Fantasy
(77, 29), -- Horror

-- Spelunky 2
(78, 14), -- Multiplayer
(78, 28), -- Puzzle
(78, 8), -- Survival

-- Dead Cells
(79, 14), -- Multiplayer
(79, 28), -- Puzzle
(79, 8), -- Survival

-- Shovel Knight
(80, 14), -- Multiplayer
(80, 28), -- Puzzle
(80, 30), -- Retro

-- Enter the Gungeon
(81, 14), -- Multiplayer
(81, 28), -- Puzzle
(81, 15), -- FPS

-- Katana Zero
(82, 14), -- Multiplayer
(82, 28), -- Puzzle
(82, 31), -- Stealth

-- Ori and the Blind Forest
(83, 3), -- Indie
(83, 9), -- Adventure
(83, 27), -- Narrative

-- A Plague Tale: Innocence
(84, 4), -- Action
(84, 29), -- Horror
(84, 27), -- Narrative

-- The Witness
(85, 14), -- Multiplayer
(85, 28), -- Puzzle
(85, 32), -- Story-rich

-- Firewatch
(86, 3), -- Indie
(86, 26), -- Detective
(86, 27), -- Narrative

-- What Remains of Edith Finch
(87, 3), -- Indie
(87, 26), -- Detective
(87, 27), -- Narrative

-- Gris
(88, 14), -- Multiplayer
(88, 16), -- Fantasy
(88, 27), -- Narrative

-- Night in the Woods
(89, 14), -- Multiplayer
(89, 16), -- Fantasy
(89, 27), -- Narrative

-- Life is Strange
(90, 14), -- Multiplayer
(90, 16), -- Fantasy
(90, 27), -- Narrative

-- Oxenfree
(91, 14), -- Multiplayer
(91, 16), -- Fantasy
(91, 27), -- Narrative

-- To the Moon
(92, 14), -- Multiplayer
(92, 16), -- Fantasy
(92, 27), -- Narrative

-- Silent Hill 2
(93, 4), -- Action
(93, 29), -- Horror
(93, 27), -- Narrative

-- Amnesia: The Dark Descent
(94, 29), -- Horror
(94, 26), -- Detective
(94, 27), -- Narrative

-- Resident Evil 7: Biohazard
(95, 4), -- Action
(95, 29), -- Horror
(95, 27), -- Narrative

-- Dead Space
(96, 4), -- Action
(96, 29), -- Horror
(96, 27), -- Narrative

-- Outlast
(97, 4), -- Action
(97, 29), -- Horror
(97, 27), -- Narrative

-- The Last of Us
(98, 4), -- Action
(98, 29), -- Horror
(98, 27), -- Narrative

-- Until Dawn
(99, 4), -- Action
(99, 29), -- Horror
(99, 32), -- Story-rich

-- Phasmophobia
(100, 29), -- Horror
(100, 14), -- Multiplayer
(100, 27); -- Narrative

INSERT IGNORE INTO gamehub.memory (`MEMORYID`, `MEMORY`) VALUES
(1, '1'),
(2, '2'),
(3, '4'),
(4, '6'),
(5, '8'),
(6, '12'),
(7, '16'),
(8, '24'),
(9, '32'),
(10, '64');
INSERT IGNORE INTO gamehub.processor (`PROCESSORID`, `PROCESSORNAME`, `PERFORMANCE`) VALUES
(1, 'AMD Ryzen 7 7800X3D', 157.2),
(2, 'AMD Ryzen 5 7600X', 140),
(3, 'AMD Ryzen 5 5600X', 89.3),
(4, 'Intel Core i9-14900K', 168.2),
(5, 'Intel Pentium E2160', 68.8),
(6, 'Intel Core 2 Quad Q8200', 101.33),
(7, 'Intel Core i5-7600', 76.6),
(8, 'Intel Core i3-6100', 66.7),
(9, 'Intel Core i3-10105', 86.1),
(10, 'Intel Xeon E5-2680 V4', 159.7),
(11, 'Intel Core i5-2400', 102.1),
(12, 'AMD Ryzen 5 3500', 78.7),
(13, 'Intel Core i7-2600K', 112.4),
(14, 'Intel Core i5-12600', 107.1),
(15, 'Intel Core i5-10500', 103.6),
(16, 'AMD Ryzen 7 1800X', 120.6),
(17, 'Intel Core i5-3570K', 84.4),
(18, 'Intel Core i7-4790K', 130.4),
(19, 'Intel Core i3-7100', 76.9),
(20, 'Intel Xeon E5-2660 V4', 154.2);
INSERT IGNORE INTO gamehub.videocard (`VIDEOCARDID`, `VIDEOCARDNAME`, `PERFORMANCE`, `MEMORY`) VALUES
(1, 'NVIDIA GeForce RTX 3060', 3109, 12),
(2, 'NVIDIA GeForce RTX 4070', 4422, 12),
(3, 'NVIDIA GeForce RTX 4060', 4373, 8),
(4, 'NVIDIA GeForce RTX 4090', 4905, 24),
(5, 'RTX 6000 Ada Generation', 4468, 48),
(6, 'AMD Radeon RX 6700 XT', 6914, 12),
(7, 'NVIDIA GeForce RTX 4070 Ti SUPER', 4981, 16),
(8, 'AMD Radeon RX 6600', 1634, 8),
(9, 'AMD Radeon RX 7800 XT', 3876, 16),
(10, 'NVIDIA GeForce GTX 1630', 4559, 4),
(11, 'NVIDIA GeForce RTX 4070 Ti', 7095, 12),
(12, 'NVIDIA GeForce RTX 4090', 7015, 24),
(13, 'AMD Radeon RX 7900 XT', 3914, 20),
(14, 'NVIDIA GeForce RTX 4070', 5527, 12),
(15, 'AMD Radeon RX 5600 XT', 2873, 6),
(16, 'NVIDIA GeForce RTX 4080', 4710, 16),
(17, 'NVIDIA GeForce RTX 4080', 4731, 16),
(18, 'NVIDIA GeForce RTX 4060', 4328, 8),
(19, 'NVIDIA GeForce RTX 4060 Ti', 4883, 8);
INSERT IGNORE INTO gamehub.systemrequirements (`GAMEID`, `PROCESSOR_PROCESSORID`, `MEMORY_MEMORYID`, `VIDEOCARD_VIDEOCARDID`) VALUES
(1, 8, 5, 1),  -- Stardew Valley
(2, 16, 7, 3), -- The Witcher 3: Wild Hunt
(3, 10, 8, 4), -- Red Dead Redemption 2
(4, 9, 4, 1),  -- Minecraft
(5, 18, 7, 6), -- Dark Souls III
(6, 4, 7, 2),  -- Grand Theft Auto V
(7, 17, 5, 8), -- Fortnite
(8, 11, 6, 3), -- Overwatch
(9, 12, 7, 9), -- Final Fantasy XV
(10, 15, 7, 5),-- Persona 5
(11, 18, 7, 7),-- God of War
(12, 16, 7, 7),-- Bloodborne
(13, 13, 7, 1),-- The Elder Scrolls V: Skyrim
(14, 11, 6, 1),-- Rocket League
(15, 10, 8, 5),-- The Last of Us Part II
(16, 4, 8, 5), -- League of Legends
(17, 3, 7, 8), -- Counter-Strike: Global Offensive
(18, 5, 5, 10),-- Among Us
(19, 9, 5, 10),-- FIFA 21
(20, 15, 7, 11),-- Apex Legends
(21, 14, 7, 14),-- Call of Duty: Warzone
(22, 8, 6, 12),-- Animal Crossing: New Horizons
(23, 18, 7, 16),-- Assassin's Creed Odyssey
(24, 17, 6, 13),-- Dota 2
(25, 6, 7, 17), -- Rainbow Six Siege
(26, 16, 7, 18),-- Halo Infinite
(27, 3, 6, 6),  -- Genshin Impact
(28, 10, 8, 13),-- Cyberpunk 2077
(29, 15, 7, 17),-- Borderlands 3
(30, 16, 7, 16),-- Monster Hunter: World
(31, 12, 7, 15),-- Destiny 2
(32, 17, 7, 15),-- Dead by Daylight
(33, 7, 7, 11), -- The Elder Scrolls Online
(34, 5, 6, 1),  -- Terraria
(35, 10, 7, 13),-- World of Warcraft
(36, 15, 7, 11),-- Smash Bros. Ultimate
(37, 9, 5, 6),  -- Rust
(38, 6, 7, 14), -- Hades
(39, 5, 5, 8),  -- RuneScape
(40, 16, 7, 11),-- Mortal Kombat 11
(41, 18, 7, 14),-- Sekiro: Shadows Die Twice
(42, 14, 8, 16),-- Death Stranding
(43, 13, 7, 9), -- Diablo III
(44, 9, 6, 10), -- No Man's Sky
(45, 10, 7, 12),-- Subnautica
(46, 18, 7, 14),-- Star Wars Jedi: Fallen Order
(47, 14, 8, 13),-- Baldur's Gate III
(48, 10, 7, 6), -- Warframe
(49, 4, 8, 17), -- Civilization VI
(50, 15, 7, 16),-- Sea of Thieves
(51, 12, 7, 18),-- Forza Horizon 4
(52, 10, 7, 15),-- Gears 5
(53, 17, 7, 14),-- Control
(54, 9, 7, 13), -- The Outer Worlds
(55, 16, 7, 11),-- Divinity: Original Sin 2
(56, 18, 7, 17),-- Tom Clancy's The Division 2
(57, 5, 6, 1),  -- Cities: Skylines
(58, 14, 7, 17),-- Stellaris
(59, 15, 7, 13),-- Factorio
(60, 17, 7, 14),-- Crusader Kings III
(61, 13, 7, 14),-- Planet Zoo
(62, 12, 7, 16),-- Ori and the Will of the Wisps
(63, 4, 8, 16), -- Doom Eternal
(64, 10, 7, 9), -- Disco Elysium
(65, 14, 7, 15),-- Detroit: Become Human
(66, 12, 7, 13),-- Resident Evil 2 Remake
(67, 5, 6, 10), -- The Sims 4
(68, 6, 7, 11), -- Celeste
(69, 14, 7, 12),-- Outer Wilds
(70, 9, 5, 6),  -- Undertale
(71, 3, 6, 6),  -- Inside
(72, 2, 6, 7),  -- Limbo
(73, 5, 6, 8),  -- Braid
(74, 4, 8, 11), -- Hyper Light Drifter
(75, 13, 7, 10),-- Journey
(76, 11, 6, 8), -- Cuphead
(77, 12, 7, 14),-- Hollow Knight
(78, 8, 6, 9),  -- Spelunky 2
(79, 10, 7, 13),-- Dead Cells
(80, 7, 7, 14), -- Shovel Knight
(81, 17, 7, 16),-- Enter the Gungeon
(82, 14, 7, 15),-- Katana Zero
(83, 6, 7, 12), -- Ori and the Blind Forest
(84, 12, 7, 13),-- A Plague Tale: Innocence
(85, 13, 7, 12),-- The Witness
(86, 7, 7, 10), -- Firewatch
(87, 9, 7, 14), -- What Remains of Edith Finch
(88, 8, 6, 9),  -- Gris
(89, 5, 6, 7),  -- Night in the Woods
(90, 11, 7, 10),-- Life is Strange
(91, 6, 7, 8),  -- Oxenfree
(92, 5, 6, 7),  -- To the Moon
(93, 16, 7, 12),-- Silent Hill 2
(94, 13, 7, 13),-- Amnesia: The Dark Descent
(95, 18, 7, 14),-- Resident Evil 7: Biohazard
(96, 10, 7, 11),-- Dead Space
(97, 8, 6, 9),  -- Outlast
(98, 18, 7, 15),-- The Last of Us
(99, 14, 7, 16),-- Until Dawn
(100, 7, 7, 17);-- Phasmophobia

