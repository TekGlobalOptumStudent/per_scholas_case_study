-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.9-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for patchworknovels
DROP DATABASE IF EXISTS `patchworknovels`;
CREATE DATABASE IF NOT EXISTS `patchworknovels` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `patchworknovels`;

-- Dumping structure for table patchworknovels.comment
DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `commentId` int(11) NOT NULL,
  `commentRating` int(11) NOT NULL,
  `commentText` text NOT NULL,
  `commentTimePosted` datetime NOT NULL,
  `commentAuthor` varchar(20) NOT NULL,
  PRIMARY KEY (`commentId`),
  KEY `FK2jemsnx95j5x0w0k9cdj7oaqt` (`commentAuthor`),
  CONSTRAINT `FK2jemsnx95j5x0w0k9cdj7oaqt` FOREIGN KEY (`commentAuthor`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.comment: ~9 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`commentId`, `commentRating`, `commentText`, `commentTimePosted`, `commentAuthor`) VALUES
	(11, 1, 'user1comment1', '2001-01-01 00:00:00', 'user1'),
	(12, 2, 'user1comment2', '2001-01-02 00:00:00', 'user1'),
	(13, 3, 'user1comment3', '2001-01-03 00:00:00', 'user1'),
	(14, 1, 'user2comment1', '2002-02-01 00:00:00', 'user2'),
	(15, 2, 'user2comment2', '2002-02-02 00:00:00', 'user2'),
	(16, 3, 'user2comment3', '2002-02-03 00:00:00', 'user2'),
	(17, 1, 'user3comment1', '2003-03-01 00:00:00', 'user3'),
	(18, 2, 'user3comment2', '2003-03-02 00:00:00', 'user3'),
	(19, 3, 'user3comment3', '2003-03-03 00:00:00', 'user3');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.hibernate_sequence: ~2 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1),
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.snippet
DROP TABLE IF EXISTS `snippet`;
CREATE TABLE IF NOT EXISTS `snippet` (
  `snippetId` int(11) NOT NULL,
  `snippetText` text NOT NULL,
  `snippetTimePosted` datetime NOT NULL,
  `snippetAuthor` varchar(20) NOT NULL,
  PRIMARY KEY (`snippetId`),
  KEY `FKtb8qqk8b6c3ef6wuvymw9d3m0` (`snippetAuthor`),
  CONSTRAINT `FKtb8qqk8b6c3ef6wuvymw9d3m0` FOREIGN KEY (`snippetAuthor`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.snippet: ~10 rows (approximately)
/*!40000 ALTER TABLE `snippet` DISABLE KEYS */;
INSERT INTO `snippet` (`snippetId`, `snippetText`, `snippetTimePosted`, `snippetAuthor`) VALUES
	(11, 'snippetTest1', '2001-01-01 00:00:00', 'user1'),
	(12, 'snippetTest2', '2002-02-02 00:00:00', 'user2'),
	(13, 'snippetTest3', '2003-03-03 00:00:00', 'user3'),
	(14, 'snippetTest4', '2004-04-04 00:00:00', 'user4'),
	(15, 'snippetTest5', '2005-05-05 00:00:00', 'user5'),
	(16, 'snippetTest6', '2006-06-06 00:00:00', 'user6'),
	(17, 'snippetTest7', '2007-07-07 00:00:00', 'user7'),
	(18, 'snippetTest8', '2008-08-08 00:00:00', 'user8'),
	(19, 'snippetTest9', '2009-09-09 00:00:00', 'user9'),
	(20, 'snippetTest10', '2010-10-10 00:00:00', 'user10');
/*!40000 ALTER TABLE `snippet` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.snippet_snippetcomments
DROP TABLE IF EXISTS `snippet_snippetcomments`;
CREATE TABLE IF NOT EXISTS `snippet_snippetcomments` (
  `Snippet_snippetId` int(11) NOT NULL,
  `snippetComments_commentId` int(11) NOT NULL,
  UNIQUE KEY `UK_kkbkijsdgx2xm9nviebvhuyjq` (`snippetComments_commentId`),
  KEY `FKmrn6s5w7je34ff6wfikpuc9lj` (`Snippet_snippetId`),
  CONSTRAINT `FKmrn6s5w7je34ff6wfikpuc9lj` FOREIGN KEY (`Snippet_snippetId`) REFERENCES `snippet` (`snippetId`),
  CONSTRAINT `FKs8oqios4co7u8854nu5vgxd6h` FOREIGN KEY (`snippetComments_commentId`) REFERENCES `comment` (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.snippet_snippetcomments: ~4 rows (approximately)
/*!40000 ALTER TABLE `snippet_snippetcomments` DISABLE KEYS */;
INSERT INTO `snippet_snippetcomments` (`Snippet_snippetId`, `snippetComments_commentId`) VALUES
	(11, 11),
	(12, 12),
	(13, 13),
	(14, 14);
/*!40000 ALTER TABLE `snippet_snippetcomments` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.snippet_snippetstories
DROP TABLE IF EXISTS `snippet_snippetstories`;
CREATE TABLE IF NOT EXISTS `snippet_snippetstories` (
  `Snippet_snippetId` int(11) NOT NULL,
  `snippetStories_storyTitle` varchar(50) NOT NULL,
  KEY `FKgogdfqanlvkyvi9mw3beeq8oh` (`snippetStories_storyTitle`),
  KEY `FKmqmrbvr2n82yiwrv9xpnjkhkt` (`Snippet_snippetId`),
  CONSTRAINT `FKgogdfqanlvkyvi9mw3beeq8oh` FOREIGN KEY (`snippetStories_storyTitle`) REFERENCES `story` (`storyTitle`),
  CONSTRAINT `FKmqmrbvr2n82yiwrv9xpnjkhkt` FOREIGN KEY (`Snippet_snippetId`) REFERENCES `snippet` (`snippetId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.snippet_snippetstories: ~6 rows (approximately)
/*!40000 ALTER TABLE `snippet_snippetstories` DISABLE KEYS */;
INSERT INTO `snippet_snippetstories` (`Snippet_snippetId`, `snippetStories_storyTitle`) VALUES
	(11, 'user1story1'),
	(11, 'user1story2'),
	(12, 'user1story1'),
	(12, 'user1story2'),
	(13, 'user1story1'),
	(13, 'user1story2');
/*!40000 ALTER TABLE `snippet_snippetstories` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.story
DROP TABLE IF EXISTS `story`;
CREATE TABLE IF NOT EXISTS `story` (
  `storyTitle` varchar(50) NOT NULL,
  `storyRating` int(11) NOT NULL,
  `storyTimePosted` datetime NOT NULL,
  `storyAuthor` varchar(20) NOT NULL,
  PRIMARY KEY (`storyTitle`),
  KEY `FKakcla0qbcx2fta17x08pmbll3` (`storyAuthor`),
  CONSTRAINT `FKakcla0qbcx2fta17x08pmbll3` FOREIGN KEY (`storyAuthor`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.story: ~9 rows (approximately)
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` (`storyTitle`, `storyRating`, `storyTimePosted`, `storyAuthor`) VALUES
	('user1story1', 1, '2001-01-01 00:00:00', 'user1'),
	('user1story2', 2, '2001-01-02 00:00:00', 'user1'),
	('user1story3', 3, '2001-01-03 00:00:00', 'user1'),
	('user2story1', 1, '2002-02-01 00:00:00', 'user2'),
	('user2story2', 2, '2002-02-02 00:00:00', 'user2'),
	('user2story3', 3, '2002-02-03 00:00:00', 'user2'),
	('user3story1', 1, '2003-03-01 00:00:00', 'user3'),
	('user3story2', 2, '2003-03-02 00:00:00', 'user3'),
	('user3story3', 3, '2003-03-03 00:00:00', 'user3');
/*!40000 ALTER TABLE `story` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.story_storycomments
DROP TABLE IF EXISTS `story_storycomments`;
CREATE TABLE IF NOT EXISTS `story_storycomments` (
  `Story_storyTitle` varchar(50) NOT NULL,
  `storyComments_commentId` int(11) NOT NULL,
  UNIQUE KEY `UK_xhiclvlnkic6ca2sr3hi2t6` (`storyComments_commentId`),
  KEY `FKyxhld9hwllkltpa3q1q5l3uu` (`Story_storyTitle`),
  CONSTRAINT `FKknqmosxolrve1y7mewtns8kss` FOREIGN KEY (`storyComments_commentId`) REFERENCES `comment` (`commentId`),
  CONSTRAINT `FKyxhld9hwllkltpa3q1q5l3uu` FOREIGN KEY (`Story_storyTitle`) REFERENCES `story` (`storyTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.story_storycomments: ~5 rows (approximately)
/*!40000 ALTER TABLE `story_storycomments` DISABLE KEYS */;
INSERT INTO `story_storycomments` (`Story_storyTitle`, `storyComments_commentId`) VALUES
	('user2story2', 15),
	('user2story3', 16),
	('user3story1', 17),
	('user3story2', 18),
	('user3story3', 19);
/*!40000 ALTER TABLE `story_storycomments` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.story_storytext
DROP TABLE IF EXISTS `story_storytext`;
CREATE TABLE IF NOT EXISTS `story_storytext` (
  `Story_storyTitle` varchar(50) NOT NULL,
  `storyText_snippetId` int(11) NOT NULL,
  KEY `FKaqy1ywo3u4gv9ugs1u3vpxxrl` (`storyText_snippetId`),
  KEY `FKhbcauppip5d1kpmohf7a818r7` (`Story_storyTitle`),
  CONSTRAINT `FKaqy1ywo3u4gv9ugs1u3vpxxrl` FOREIGN KEY (`storyText_snippetId`) REFERENCES `snippet` (`snippetId`),
  CONSTRAINT `FKhbcauppip5d1kpmohf7a818r7` FOREIGN KEY (`Story_storyTitle`) REFERENCES `story` (`storyTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.story_storytext: ~6 rows (approximately)
/*!40000 ALTER TABLE `story_storytext` DISABLE KEYS */;
INSERT INTO `story_storytext` (`Story_storyTitle`, `storyText_snippetId`) VALUES
	('user1story1', 11),
	('user1story1', 12),
	('user1story1', 13),
	('user1story2', 11),
	('user1story2', 12),
	('user1story2', 13);
/*!40000 ALTER TABLE `story_storytext` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `dateJoined` datetime NOT NULL,
  `password` varchar(20) NOT NULL,
  `profileImage` blob DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user: ~11 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `dateJoined`, `password`, `profileImage`) VALUES
	('', '2000-01-01 00:00:00', '', NULL),
	('user1', '2001-01-01 00:00:00', 'pass1', NULL),
	('user10', '2010-10-10 00:00:00', 'pass10', NULL),
	('user2', '2002-02-02 00:00:00', 'pass2', NULL),
	('user3', '2003-03-03 00:00:00', 'pass3', NULL),
	('user4', '2004-04-04 00:00:00', 'pass4', NULL),
	('user5', '2005-05-05 00:00:00', 'pass5', NULL),
	('user6', '2006-06-06 00:00:00', 'pass6', NULL),
	('user7', '2007-07-07 00:00:00', 'pass7', NULL),
	('user8', '2008-08-08 00:00:00', 'pass8', NULL),
	('user9', '2009-09-09 00:00:00', 'pass9', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user_favoritestories
DROP TABLE IF EXISTS `user_favoritestories`;
CREATE TABLE IF NOT EXISTS `user_favoritestories` (
  `User_username` varchar(20) NOT NULL,
  `favoriteStories_storyTitle` varchar(50) NOT NULL,
  UNIQUE KEY `UK_gxr53u17khxmpkxhihud3art1` (`favoriteStories_storyTitle`),
  KEY `FKsxu9ur3ou7obj61bl10cetgw2` (`User_username`),
  CONSTRAINT `FKdaj24ia3bxbvdnmxh86bsvcih` FOREIGN KEY (`favoriteStories_storyTitle`) REFERENCES `story` (`storyTitle`),
  CONSTRAINT `FKsxu9ur3ou7obj61bl10cetgw2` FOREIGN KEY (`User_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user_favoritestories: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_favoritestories` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favoritestories` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user_publishedsnippets
DROP TABLE IF EXISTS `user_publishedsnippets`;
CREATE TABLE IF NOT EXISTS `user_publishedsnippets` (
  `User_username` varchar(20) NOT NULL,
  `publishedSnippets_snippetId` int(11) NOT NULL,
  UNIQUE KEY `UK_s3bl2hpm5i7c3clw24fkr8c5v` (`publishedSnippets_snippetId`),
  KEY `FK7m4fur1jabna112y2sxfhe1g0` (`User_username`),
  CONSTRAINT `FK7m4fur1jabna112y2sxfhe1g0` FOREIGN KEY (`User_username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKadh1a69xihw8ki3ca6vjd9ddi` FOREIGN KEY (`publishedSnippets_snippetId`) REFERENCES `snippet` (`snippetId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user_publishedsnippets: ~10 rows (approximately)
/*!40000 ALTER TABLE `user_publishedsnippets` DISABLE KEYS */;
INSERT INTO `user_publishedsnippets` (`User_username`, `publishedSnippets_snippetId`) VALUES
	('user1', 11),
	('user10', 20),
	('user2', 12),
	('user3', 13),
	('user4', 14),
	('user5', 15),
	('user6', 16),
	('user7', 17),
	('user8', 18),
	('user9', 19);
/*!40000 ALTER TABLE `user_publishedsnippets` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user_publishedstories
DROP TABLE IF EXISTS `user_publishedstories`;
CREATE TABLE IF NOT EXISTS `user_publishedstories` (
  `User_username` varchar(20) NOT NULL,
  `publishedStories_storyTitle` varchar(50) NOT NULL,
  UNIQUE KEY `UK_37933gtyalamndhpscq0lhqsk` (`publishedStories_storyTitle`),
  KEY `FK24vqv3hbghddx8is0c94ap27p` (`User_username`),
  CONSTRAINT `FK24vqv3hbghddx8is0c94ap27p` FOREIGN KEY (`User_username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKdj8a7gu9wj36esavw4h8oltf3` FOREIGN KEY (`publishedStories_storyTitle`) REFERENCES `story` (`storyTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user_publishedstories: ~9 rows (approximately)
/*!40000 ALTER TABLE `user_publishedstories` DISABLE KEYS */;
INSERT INTO `user_publishedstories` (`User_username`, `publishedStories_storyTitle`) VALUES
	('user1', 'user1story1'),
	('user1', 'user1story2'),
	('user1', 'user1story3'),
	('user2', 'user2story1'),
	('user2', 'user2story2'),
	('user2', 'user2story3'),
	('user3', 'user3story1'),
	('user3', 'user3story2'),
	('user3', 'user3story3');
/*!40000 ALTER TABLE `user_publishedstories` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
