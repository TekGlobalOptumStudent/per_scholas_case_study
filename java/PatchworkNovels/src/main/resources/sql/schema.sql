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

-- Dumping structure for table patchworknovels.comment
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

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.hibernate_sequence
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

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.snippet
CREATE TABLE IF NOT EXISTS `snippet` (
  `snippet_id` int(11) NOT NULL,
  `snippet_text` text NOT NULL,
  `snippet_time_posted` datetime(6) NOT NULL,
  `snippet_author` varchar(20) NOT NULL,
  PRIMARY KEY (`snippet_id`),
  KEY `FKbhifepcwlrdhmww65hhfrky6r` (`snippet_author`),
  CONSTRAINT `FKbhifepcwlrdhmww65hhfrky6r` FOREIGN KEY (`snippet_author`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.snippet_snippet_comments
CREATE TABLE IF NOT EXISTS `snippet_snippet_comments` (
  `snippet_snippet_id` int(11) NOT NULL,
  `snippet_comments_comment_id` int(11) NOT NULL,
  UNIQUE KEY `UK_iltr0kv761v10ivsj359ekd9b` (`snippet_comments_comment_id`),
  KEY `FK329e7lsuujq7ltiksr7mbg463` (`snippet_snippet_id`),
  CONSTRAINT `FK329e7lsuujq7ltiksr7mbg463` FOREIGN KEY (`snippet_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FK3c962ypleoaynklqnk0k1etnb` FOREIGN KEY (`snippet_comments_comment_id`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.snippet_snippet_stories
CREATE TABLE IF NOT EXISTS `snippet_snippet_stories` (
  `snippet_snippet_id` int(11) NOT NULL,
  `snippet_stories_story_title` varchar(50) NOT NULL,
  KEY `FKgw7lpx75c75ke5yqp8yffrmgm` (`snippet_stories_story_title`),
  KEY `FK288dicutgd3avq86ne1ja8q2j` (`snippet_snippet_id`),
  CONSTRAINT `FK288dicutgd3avq86ne1ja8q2j` FOREIGN KEY (`snippet_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FKgw7lpx75c75ke5yqp8yffrmgm` FOREIGN KEY (`snippet_stories_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.story
CREATE TABLE IF NOT EXISTS `story` (
  `story_title` varchar(50) NOT NULL,
  `story_rating` int(11) NOT NULL,
  `story_time_posted` datetime(6) NOT NULL,
  `story_author` varchar(20) NOT NULL,
  PRIMARY KEY (`story_title`),
  KEY `FKqoeyrmlby49tnmutul4ptileu` (`story_author`),
  CONSTRAINT `FKqoeyrmlby49tnmutul4ptileu` FOREIGN KEY (`story_author`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.story_story_comments
CREATE TABLE IF NOT EXISTS `story_story_comments` (
  `story_story_title` varchar(50) NOT NULL,
  `story_comments_comment_id` int(11) NOT NULL,
  UNIQUE KEY `UK_k2fmc44p3hsm3au2g6u8pcjeq` (`story_comments_comment_id`),
  KEY `FKjphhdrhqr5u7sccobm6v18y2s` (`story_story_title`),
  CONSTRAINT `FK5fvrgoejov4si51hjd4250w17` FOREIGN KEY (`story_comments_comment_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FKjphhdrhqr5u7sccobm6v18y2s` FOREIGN KEY (`story_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.story_story_text
CREATE TABLE IF NOT EXISTS `story_story_text` (
  `story_story_title` varchar(50) NOT NULL,
  `story_text_snippet_id` int(11) NOT NULL,
  KEY `FK91tvh6f5yt4ox2fk471md07jt` (`story_text_snippet_id`),
  KEY `FKd3ibbvlub2u9jl235g6ix933k` (`story_story_title`),
  CONSTRAINT `FK91tvh6f5yt4ox2fk471md07jt` FOREIGN KEY (`story_text_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FKd3ibbvlub2u9jl235g6ix933k` FOREIGN KEY (`story_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  `password` varchar(20) NOT NULL,
  `profile_image` blob DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user_favorite_stories
CREATE TABLE IF NOT EXISTS `user_favorite_stories` (
  `user_username` varchar(20) NOT NULL,
  `favorite_stories_story_title` varchar(50) NOT NULL,
  UNIQUE KEY `UK_o47uhwt5epowxi0n5dxid2s6y` (`favorite_stories_story_title`),
  KEY `FKg19gjgeub451rhic2o6t01ncl` (`user_username`),
  CONSTRAINT `FKg19gjgeub451rhic2o6t01ncl` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKrr1uv1qfbscn22xtjicslu7on` FOREIGN KEY (`favorite_stories_story_title`) REFERENCES `story` (`story_title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user_published_snippets
CREATE TABLE IF NOT EXISTS `user_published_snippets` (
  `user_username` varchar(20) NOT NULL,
  `published_snippets_snippet_id` int(11) NOT NULL,
  UNIQUE KEY `UK_96m9ngieqquuscd54a10a79u1` (`published_snippets_snippet_id`),
  KEY `FKor7ildfk4ged4ta7fqmmdatpi` (`user_username`),
  CONSTRAINT `FK7ywbcv5eec993psoqr1wx4ii4` FOREIGN KEY (`published_snippets_snippet_id`) REFERENCES `snippet` (`snippet_id`),
  CONSTRAINT `FKor7ildfk4ged4ta7fqmmdatpi` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user_published_stories
CREATE TABLE IF NOT EXISTS `user_published_stories` (
  `user_username` varchar(20) NOT NULL,
  `published_stories_story_title` varchar(50) NOT NULL,
  UNIQUE KEY `UK_9i2xjv1g31t44hm7hs7d6yg4g` (`published_stories_story_title`),
  KEY `FKlrc269g52efalqgfp3an34fcr` (`user_username`),
  CONSTRAINT `FKjaf6kldeamf4q5e7h0lajyu17` FOREIGN KEY (`published_stories_story_title`) REFERENCES `story` (`story_title`),
  CONSTRAINT `FKlrc269g52efalqgfp3an34fcr` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
