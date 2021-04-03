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

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `dateJoined` datetime NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

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

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
