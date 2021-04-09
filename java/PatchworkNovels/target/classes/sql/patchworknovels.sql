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
  `comment_id` int(11) NOT NULL,
  `comment_rating` int(11) NOT NULL,
  `comment_text` text NOT NULL,
  `comment_time_posted` datetime(6) NOT NULL,
  `comment_author` varchar(20) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKoxemrebhbs3otlxfog7fyowu9` (`comment_author`),
  CONSTRAINT `FKoxemrebhbs3otlxfog7fyowu9` FOREIGN KEY (`comment_author`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.comment: ~9 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`comment_id`, `comment_rating`, `comment_text`, `comment_time_posted`, `comment_author`) VALUES
	(11, 1, 'user1comment1', '2001-01-01 00:00:00.000000', 'user1'),
	(12, 2, 'user1comment2', '2001-01-02 00:00:00.000000', 'user1'),
	(13, 3, 'user1comment3', '2001-01-03 00:00:00.000000', 'user1'),
	(14, 1, 'user2comment1', '2002-02-01 00:00:00.000000', 'user2'),
	(15, 2, 'user2comment2', '2002-02-02 00:00:00.000000', 'user2'),
	(16, 3, 'user2comment3', '2002-02-03 00:00:00.000000', 'user2'),
	(17, 1, 'user3comment1', '2003-03-01 00:00:00.000000', 'user3'),
	(18, 2, 'user3comment2', '2003-03-02 00:00:00.000000', 'user3'),
	(19, 3, 'user3comment3', '2003-03-03 00:00:00.000000', 'user3');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Dumping data for table patchworknovels.hibernate_sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(1, 1, 9223372036854775806, 1, 1, 1000, 0, 0);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.snippet
DROP TABLE IF EXISTS `snippet`;
CREATE TABLE IF NOT EXISTS `snippet` (
  `snippet_id` int(11) NOT NULL,
  `snippet_text` text NOT NULL,
  `snippet_time_posted` datetime(6) NOT NULL,
  `snippet_author` varchar(20) NOT NULL,
  PRIMARY KEY (`snippet_id`),
  KEY `FKbhifepcwlrdhmww65hhfrky6r` (`snippet_author`),
  CONSTRAINT `FKbhifepcwlrdhmww65hhfrky6r` FOREIGN KEY (`snippet_author`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.snippet: ~10 rows (approximately)
/*!40000 ALTER TABLE `snippet` DISABLE KEYS */;
INSERT INTO `snippet` (`snippet_id`, `snippet_text`, `snippet_time_posted`, `snippet_author`) VALUES
	(11, 'snippetTest1', '2001-01-01 00:00:00.000000', 'user1'),
	(12, 'snippetTest2', '2002-02-02 00:00:00.000000', 'user2'),
	(13, 'snippetTest3', '2003-03-03 00:00:00.000000', 'user3'),
	(14, 'snippetTest4', '2004-04-04 00:00:00.000000', 'user4'),
	(15, 'snippetTest5', '2005-05-05 00:00:00.000000', 'user5'),
	(16, 'snippetTest6', '2006-06-06 00:00:00.000000', 'user6'),
	(17, 'snippetTest7', '2007-07-07 00:00:00.000000', 'user7'),
	(18, 'snippetTest8', '2008-08-08 00:00:00.000000', 'user8'),
	(19, 'snippetTest9', '2009-09-09 00:00:00.000000', 'user9'),
	(20, 'snippetTest10', '2010-10-10 00:00:00.000000', 'user10');
/*!40000 ALTER TABLE `snippet` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.snippet_snippet_comments
DROP TABLE IF EXISTS `snippet_snippet_comments`;
CREATE TABLE IF NOT EXISTS `snippet_snippet_comments` (
  `snippet_snippet_id` int(11) NOT NULL,
  `snippet_comments_comment_id` int(11) NOT NULL,
  UNIQUE KEY `UK_iltr0kv761v10ivsj359ekd9b` (`snippet_comments_comment_id`),
  KEY `FK329e7lsuujq7ltiksr7mbg463` (`snippet_snippet_id`),
  CONSTRAINT `FK329e7lsuujq7ltiksr7mbg463` FOREIGN KEY (`snippet_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FK3c962ypleoaynklqnk0k1etnb` FOREIGN KEY (`snippet_comments_comment_id`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.snippet_snippet_comments: ~4 rows (approximately)
/*!40000 ALTER TABLE `snippet_snippet_comments` DISABLE KEYS */;
INSERT INTO `snippet_snippet_comments` (`snippet_snippet_id`, `snippet_comments_comment_id`) VALUES
	(11, 11),
	(12, 12),
	(13, 13),
	(14, 14);
/*!40000 ALTER TABLE `snippet_snippet_comments` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.snippet_snippet_stories
DROP TABLE IF EXISTS `snippet_snippet_stories`;
CREATE TABLE IF NOT EXISTS `snippet_snippet_stories` (
  `snippet_snippet_id` int(11) NOT NULL,
  `snippet_stories_story_title` varchar(50) NOT NULL,
  KEY `FKgw7lpx75c75ke5yqp8yffrmgm` (`snippet_stories_story_title`),
  KEY `FK288dicutgd3avq86ne1ja8q2j` (`snippet_snippet_id`),
  CONSTRAINT `FK288dicutgd3avq86ne1ja8q2j` FOREIGN KEY (`snippet_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FKgw7lpx75c75ke5yqp8yffrmgm` FOREIGN KEY (`snippet_stories_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.snippet_snippet_stories: ~6 rows (approximately)
/*!40000 ALTER TABLE `snippet_snippet_stories` DISABLE KEYS */;
INSERT INTO `snippet_snippet_stories` (`snippet_snippet_id`, `snippet_stories_story_title`) VALUES
	(11, 'user1story1'),
	(11, 'user1story2'),
	(12, 'user1story1'),
	(12, 'user1story2'),
	(13, 'user1story1'),
	(13, 'user1story2');
/*!40000 ALTER TABLE `snippet_snippet_stories` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.story
DROP TABLE IF EXISTS `story`;
CREATE TABLE IF NOT EXISTS `story` (
  `story_title` varchar(50) NOT NULL,
  `story_rating` int(11) NOT NULL,
  `story_time_posted` datetime(6) NOT NULL,
  `story_author` varchar(20) NOT NULL,
  PRIMARY KEY (`story_title`),
  KEY `FKqoeyrmlby49tnmutul4ptileu` (`story_author`),
  CONSTRAINT `FKqoeyrmlby49tnmutul4ptileu` FOREIGN KEY (`story_author`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.story: ~9 rows (approximately)
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` (`story_title`, `story_rating`, `story_time_posted`, `story_author`) VALUES
	('user1story1', 1, '2001-01-01 00:00:00.000000', 'user1'),
	('user1story2', 2, '2001-01-02 00:00:00.000000', 'user1'),
	('user1story3', 3, '2001-01-03 00:00:00.000000', 'user1'),
	('user2story1', 1, '2002-02-01 00:00:00.000000', 'user2'),
	('user2story2', 2, '2002-02-02 00:00:00.000000', 'user2'),
	('user2story3', 3, '2002-02-03 00:00:00.000000', 'user2'),
	('user3story1', 1, '2003-03-01 00:00:00.000000', 'user3'),
	('user3story2', 2, '2003-03-02 00:00:00.000000', 'user3'),
	('user3story3', 3, '2003-03-03 00:00:00.000000', 'user3');
/*!40000 ALTER TABLE `story` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.story_story_comments
DROP TABLE IF EXISTS `story_story_comments`;
CREATE TABLE IF NOT EXISTS `story_story_comments` (
  `story_story_title` varchar(50) NOT NULL,
  `story_comments_comment_id` int(11) NOT NULL,
  UNIQUE KEY `UK_k2fmc44p3hsm3au2g6u8pcjeq` (`story_comments_comment_id`),
  KEY `FKjphhdrhqr5u7sccobm6v18y2s` (`story_story_title`),
  CONSTRAINT `FK5fvrgoejov4si51hjd4250w17` FOREIGN KEY (`story_comments_comment_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FKjphhdrhqr5u7sccobm6v18y2s` FOREIGN KEY (`story_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.story_story_comments: ~5 rows (approximately)
/*!40000 ALTER TABLE `story_story_comments` DISABLE KEYS */;
INSERT INTO `story_story_comments` (`story_story_title`, `story_comments_comment_id`) VALUES
	('user2story2', 15),
	('user2story3', 16),
	('user3story1', 17),
	('user3story2', 18),
	('user3story3', 19);
/*!40000 ALTER TABLE `story_story_comments` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.story_story_text
DROP TABLE IF EXISTS `story_story_text`;
CREATE TABLE IF NOT EXISTS `story_story_text` (
  `story_story_title` varchar(50) NOT NULL,
  `story_text_snippet_id` int(11) NOT NULL,
  KEY `FK91tvh6f5yt4ox2fk471md07jt` (`story_text_snippet_id`),
  KEY `FKd3ibbvlub2u9jl235g6ix933k` (`story_story_title`),
  CONSTRAINT `FK91tvh6f5yt4ox2fk471md07jt` FOREIGN KEY (`story_text_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FKd3ibbvlub2u9jl235g6ix933k` FOREIGN KEY (`story_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.story_story_text: ~6 rows (approximately)
/*!40000 ALTER TABLE `story_story_text` DISABLE KEYS */;
INSERT INTO `story_story_text` (`story_story_title`, `story_text_snippet_id`) VALUES
	('user1story1', 11),
	('user1story1', 12),
	('user1story1', 13),
	('user1story2', 11),
	('user1story2', 12),
	('user1story2', 13);
/*!40000 ALTER TABLE `story_story_text` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  `password` varchar(20) NOT NULL,
  `profile_image` blob DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user: ~11 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `date_joined`, `password`, `profile_image`) VALUES
	('', '2000-01-01 00:00:00.000000', '', NULL),
	('user1', '2001-01-01 00:00:00.000000', 'pass1', NULL),
	('user10', '2010-10-10 00:00:00.000000', 'pass10', NULL),
	('user2', '2002-02-02 00:00:00.000000', 'pass2', NULL),
	('user3', '2003-03-03 00:00:00.000000', 'pass3', NULL),
	('user4', '2004-04-04 00:00:00.000000', 'pass4', NULL),
	('user5', '2005-05-05 00:00:00.000000', 'pass5', NULL),
	('user6', '2006-06-06 00:00:00.000000', 'pass6', NULL),
	('user7', '2007-07-07 00:00:00.000000', 'pass7', NULL),
	('user8', '2008-08-08 00:00:00.000000', 'pass8', NULL),
	('user9', '2009-09-09 00:00:00.000000', 'pass9', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user_favorite_stories
DROP TABLE IF EXISTS `user_favorite_stories`;
CREATE TABLE IF NOT EXISTS `user_favorite_stories` (
  `user_username` varchar(20) NOT NULL,
  `favorite_stories_story_title` varchar(50) NOT NULL,
  UNIQUE KEY `UK_o47uhwt5epowxi0n5dxid2s6y` (`favorite_stories_story_title`),
  KEY `FKg19gjgeub451rhic2o6t01ncl` (`user_username`),
  CONSTRAINT `FKg19gjgeub451rhic2o6t01ncl` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKrr1uv1qfbscn22xtjicslu7on` FOREIGN KEY (`favorite_stories_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user_favorite_stories: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_favorite_stories` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favorite_stories` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user_published_snippets
DROP TABLE IF EXISTS `user_published_snippets`;
CREATE TABLE IF NOT EXISTS `user_published_snippets` (
  `user_username` varchar(20) NOT NULL,
  `published_snippets_snippet_id` int(11) NOT NULL,
  UNIQUE KEY `UK_96m9ngieqquuscd54a10a79u1` (`published_snippets_snippet_id`),
  KEY `FKor7ildfk4ged4ta7fqmmdatpi` (`user_username`),
  CONSTRAINT `FK7ywbcv5eec993psoqr1wx4ii4` FOREIGN KEY (`published_snippets_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FKor7ildfk4ged4ta7fqmmdatpi` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user_published_snippets: ~10 rows (approximately)
/*!40000 ALTER TABLE `user_published_snippets` DISABLE KEYS */;
INSERT INTO `user_published_snippets` (`user_username`, `published_snippets_snippet_id`) VALUES
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
/*!40000 ALTER TABLE `user_published_snippets` ENABLE KEYS */;

-- Dumping structure for table patchworknovels.user_published_stories
DROP TABLE IF EXISTS `user_published_stories`;
CREATE TABLE IF NOT EXISTS `user_published_stories` (
  `user_username` varchar(20) NOT NULL,
  `published_stories_story_title` varchar(50) NOT NULL,
  UNIQUE KEY `UK_9i2xjv1g31t44hm7hs7d6yg4g` (`published_stories_story_title`),
  KEY `FKlrc269g52efalqgfp3an34fcr` (`user_username`),
  CONSTRAINT `FKjaf6kldeamf4q5e7h0lajyu17` FOREIGN KEY (`published_stories_story_title`) REFERENCES `story` (`story_title`),
  CONSTRAINT `FKlrc269g52efalqgfp3an34fcr` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table patchworknovels.user_published_stories: ~9 rows (approximately)
/*!40000 ALTER TABLE `user_published_stories` DISABLE KEYS */;
INSERT INTO `user_published_stories` (`user_username`, `published_stories_story_title`) VALUES
	('user1', 'user1story1'),
	('user1', 'user1story2'),
	('user1', 'user1story3'),
	('user2', 'user2story1'),
	('user2', 'user2story2'),
	('user2', 'user2story3'),
	('user3', 'user3story1'),
	('user3', 'user3story2'),
	('user3', 'user3story3');
/*!40000 ALTER TABLE `user_published_stories` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
