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

-- Dumping data for table patchworknovels.comment: ~9 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`commentId`, `commentRating`, `commentText`, `commentTimePosted`, `commentAuthorId`) VALUES
	(11, 1, 'user1comment1', '2001-01-01 00:00:00', 11),
	(12, 2, 'user1comment2', '2001-01-02 00:00:00', 11),
	(13, 3, 'user1comment3', '2001-01-03 00:00:00', 11),
	(14, 1, 'user2comment1', '2002-02-01 00:00:00', 12),
	(15, 2, 'user2comment2', '2002-02-02 00:00:00', 12),
	(16, 3, 'user2comment3', '2002-02-03 00:00:00', 12),
	(17, 1, 'user3comment1', '2003-03-01 00:00:00', 13),
	(18, 2, 'user3comment2', '2003-03-02 00:00:00', 13),
	(19, 3, 'user3comment3', '2003-03-03 00:00:00', 13);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- Dumping data for table patchworknovels.hibernate_sequence: ~4 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1),
	(1),
	(1),
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping data for table patchworknovels.snippet: ~10 rows (approximately)
/*!40000 ALTER TABLE `snippet` DISABLE KEYS */;
INSERT INTO `snippet` (`snippetId`, `snippetText`, `snippetTimePosted`, `snippetAuthorId`) VALUES
	(11, 'snippetTest1', '2001-01-01 00:00:00', 11),
	(12, 'snippetTest2', '2002-02-02 00:00:00', 12),
	(13, 'snippetTest3', '2003-03-03 00:00:00', 13),
	(14, 'snippetTest4', '2004-04-04 00:00:00', 14),
	(15, 'snippetTest5', '2005-05-05 00:00:00', 15),
	(16, 'snippetTest6', '2006-06-06 00:00:00', 16),
	(17, 'snippetTest7', '2007-07-07 00:00:00', 17),
	(18, 'snippetTest8', '2008-08-08 00:00:00', 18),
	(19, 'snippetTest9', '2009-09-09 00:00:00', 19),
	(20, 'snippetTest10', '2010-10-10 00:00:00', 20);
/*!40000 ALTER TABLE `snippet` ENABLE KEYS */;

-- Dumping data for table patchworknovels.snippet_snippetcomments: ~0 rows (approximately)
/*!40000 ALTER TABLE `snippet_snippetcomments` DISABLE KEYS */;
/*!40000 ALTER TABLE `snippet_snippetcomments` ENABLE KEYS */;

-- Dumping data for table patchworknovels.snippet_snippetstories: ~0 rows (approximately)
/*!40000 ALTER TABLE `snippet_snippetstories` DISABLE KEYS */;
/*!40000 ALTER TABLE `snippet_snippetstories` ENABLE KEYS */;

-- Dumping data for table patchworknovels.story: ~9 rows (approximately)
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` (`storyId`, `storyRating`, `storyTimePosted`, `storyTitle`, `storyAuthorId`) VALUES
	(11, 1, '2001-01-01 00:00:00', 'user1story1', 11),
	(12, 2, '2001-01-02 00:00:00', 'user1story2', 11),
	(13, 3, '2001-01-03 00:00:00', 'user1story3', 11),
	(14, 1, '2002-02-01 00:00:00', 'user2story1', 12),
	(15, 2, '2002-02-02 00:00:00', 'user2story2', 12),
	(16, 3, '2002-02-03 00:00:00', 'user2story3', 12),
	(17, 1, '2003-03-01 00:00:00', 'user3story1', 13),
	(18, 2, '2003-03-02 00:00:00', 'user3story2', 13),
	(19, 3, '2003-03-03 00:00:00', 'user3story3', 13);
/*!40000 ALTER TABLE `story` ENABLE KEYS */;

-- Dumping data for table patchworknovels.story_storycomments: ~0 rows (approximately)
/*!40000 ALTER TABLE `story_storycomments` DISABLE KEYS */;
/*!40000 ALTER TABLE `story_storycomments` ENABLE KEYS */;

-- Dumping data for table patchworknovels.story_storytext: ~0 rows (approximately)
/*!40000 ALTER TABLE `story_storytext` DISABLE KEYS */;
/*!40000 ALTER TABLE `story_storytext` ENABLE KEYS */;

-- Dumping data for table patchworknovels.user: ~11 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `dateJoined`, `password`, `username`) VALUES
	(-1, '2000-01-01 00:00:00', '', 'deleted'),
	(11, '2001-01-01 00:00:00', 'pass1', 'user1'),
	(12, '2002-02-02 00:00:00', 'pass2', 'user2'),
	(13, '2003-03-03 00:00:00', 'pass3', 'user3'),
	(14, '2004-04-04 00:00:00', 'pass4', 'user4'),
	(15, '2005-05-05 00:00:00', 'pass5', 'user5'),
	(16, '2006-06-06 00:00:00', 'pass6', 'user6'),
	(17, '2007-07-07 00:00:00', 'pass7', 'user7'),
	(18, '2008-08-08 00:00:00', 'pass8', 'user8'),
	(19, '2009-09-09 00:00:00', 'pass9', 'user9'),
	(20, '2010-10-10 00:00:00', 'pass10', 'user10');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping data for table patchworknovels.user_favoritestories: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_favoritestories` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favoritestories` ENABLE KEYS */;

-- Dumping data for table patchworknovels.user_publishedsnippets: ~10 rows (approximately)
/*!40000 ALTER TABLE `user_publishedsnippets` DISABLE KEYS */;
INSERT INTO `user_publishedsnippets` (`User_userId`, `publishedSnippets_snippetId`) VALUES
	(11, 11),
	(12, 12),
	(13, 13),
	(14, 14),
	(15, 15),
	(16, 16),
	(17, 17),
	(18, 18),
	(19, 19),
	(20, 20);
/*!40000 ALTER TABLE `user_publishedsnippets` ENABLE KEYS */;

-- Dumping data for table patchworknovels.user_publishedstories: ~9 rows (approximately)
/*!40000 ALTER TABLE `user_publishedstories` DISABLE KEYS */;
INSERT INTO `user_publishedstories` (`User_userId`, `publishedStories_storyId`) VALUES
	(11, 11),
	(11, 12),
	(11, 13),
	(12, 14),
	(12, 15),
	(12, 16),
	(13, 17),
	(13, 18),
	(13, 19);
/*!40000 ALTER TABLE `user_publishedstories` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
