-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
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
CREATE TABLE IF NOT EXISTS `comment` (
  `commentId` int(11) NOT NULL,
  `commentRating` int(11) NOT NULL,
  `commentText` text NOT NULL,
  `commentTimePosted` datetime NOT NULL,
  `commentAuthorId` int(11) NOT NULL,
  PRIMARY KEY (`commentId`),
  KEY `FKjs0d5fixybw7at832lnfx5yts` (`commentAuthorId`),
  CONSTRAINT `FKjs0d5fixybw7at832lnfx5yts` FOREIGN KEY (`commentAuthorId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.snippet
CREATE TABLE IF NOT EXISTS `snippet` (
  `snippetId` int(11) NOT NULL,
  `snippetText` text NOT NULL,
  `snippetTimePosted` datetime NOT NULL,
  `snippetAuthorId` int(11) NOT NULL,
  PRIMARY KEY (`snippetId`),
  KEY `FK4m2hqkpdaq4lmv02h3pbkjrs2` (`snippetAuthorId`),
  CONSTRAINT `FK4m2hqkpdaq4lmv02h3pbkjrs2` FOREIGN KEY (`snippetAuthorId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.snippet_snippetcomments
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
CREATE TABLE IF NOT EXISTS `snippet_snippetstories` (
  `Snippet_snippetId` int(11) NOT NULL,
  `snippetStories_storyId` int(11) NOT NULL,
  KEY `FK46ulwasuo9ovcxemyfujaj4kr` (`snippetStories_storyId`),
  KEY `FKmqmrbvr2n82yiwrv9xpnjkhkt` (`Snippet_snippetId`),
  CONSTRAINT `FK46ulwasuo9ovcxemyfujaj4kr` FOREIGN KEY (`snippetStories_storyId`) REFERENCES `story` (`storyId`),
  CONSTRAINT `FKmqmrbvr2n82yiwrv9xpnjkhkt` FOREIGN KEY (`Snippet_snippetId`) REFERENCES `snippet` (`snippetId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.story
CREATE TABLE IF NOT EXISTS `story` (
  `storyId` int(11) NOT NULL,
  `storyRating` int(11) NOT NULL,
  `storyTimePosted` datetime NOT NULL,
  `storyTitle` varchar(50) NOT NULL,
  `storyAuthorId` int(11) NOT NULL,
  PRIMARY KEY (`storyId`),
  KEY `FKg1p4gioudhqqfa1nxccwnio47` (`storyAuthorId`),
  CONSTRAINT `FKg1p4gioudhqqfa1nxccwnio47` FOREIGN KEY (`storyAuthorId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.story_storycomments
CREATE TABLE IF NOT EXISTS `story_storycomments` (
  `Story_storyId` int(11) NOT NULL,
  `storyComments_commentId` int(11) NOT NULL,
  UNIQUE KEY `UK_xhiclvlnkic6ca2sr3hi2t6` (`storyComments_commentId`),
  KEY `FK9w68iaul5qoeefg1fivrpxc1` (`Story_storyId`),
  CONSTRAINT `FK9w68iaul5qoeefg1fivrpxc1` FOREIGN KEY (`Story_storyId`) REFERENCES `story` (`storyId`),
  CONSTRAINT `FKknqmosxolrve1y7mewtns8kss` FOREIGN KEY (`storyComments_commentId`) REFERENCES `comment` (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.story_storytext
CREATE TABLE IF NOT EXISTS `story_storytext` (
  `Story_storyId` int(11) NOT NULL,
  `storyText_snippetId` int(11) NOT NULL,
  UNIQUE KEY `UK_obbql4ot5ghixbtxiae6ygblr` (`storyText_snippetId`),
  KEY `FKbankio1c4xqkie3qbjwc614sy` (`Story_storyId`),
  CONSTRAINT `FKaqy1ywo3u4gv9ugs1u3vpxxrl` FOREIGN KEY (`storyText_snippetId`) REFERENCES `snippet` (`snippetId`),
  CONSTRAINT `FKbankio1c4xqkie3qbjwc614sy` FOREIGN KEY (`Story_storyId`) REFERENCES `story` (`storyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(11) NOT NULL,
  `dateJoined` datetime NOT NULL,
  `password` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user_favoritestories
CREATE TABLE IF NOT EXISTS `user_favoritestories` (
  `User_userId` int(11) NOT NULL,
  `favoriteStories_storyId` int(11) NOT NULL,
  UNIQUE KEY `UK_3ecvonf93c11j0mrwt7tycckc` (`favoriteStories_storyId`),
  KEY `FK7phu0xcc8u51w6hf0ydsifpno` (`User_userId`),
  CONSTRAINT `FK7phu0xcc8u51w6hf0ydsifpno` FOREIGN KEY (`User_userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FKhi09j6qafloop1yikn270q26s` FOREIGN KEY (`favoriteStories_storyId`) REFERENCES `story` (`storyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user_publishedsnippets
CREATE TABLE IF NOT EXISTS `user_publishedsnippets` (
  `User_userId` int(11) NOT NULL,
  `publishedSnippets_snippetId` int(11) NOT NULL,
  UNIQUE KEY `UK_s3bl2hpm5i7c3clw24fkr8c5v` (`publishedSnippets_snippetId`),
  KEY `FK92bsk5t8pm90f62qrjq3cw0sp` (`User_userId`),
  CONSTRAINT `FK92bsk5t8pm90f62qrjq3cw0sp` FOREIGN KEY (`User_userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FKadh1a69xihw8ki3ca6vjd9ddi` FOREIGN KEY (`publishedSnippets_snippetId`) REFERENCES `snippet` (`snippetId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table patchworknovels.user_publishedstories
CREATE TABLE IF NOT EXISTS `user_publishedstories` (
  `User_userId` int(11) NOT NULL,
  `publishedStories_storyId` int(11) NOT NULL,
  UNIQUE KEY `UK_gl98hr8p6ouwv1mx8qvpc8gyd` (`publishedStories_storyId`),
  KEY `FKqoljdql60ltoqvxrmi850kwh0` (`User_userId`),
  CONSTRAINT `FKbja90lsp7y84sb3isov00uo1j` FOREIGN KEY (`publishedStories_storyId`) REFERENCES `story` (`storyId`),
  CONSTRAINT `FKqoljdql60ltoqvxrmi850kwh0` FOREIGN KEY (`User_userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
