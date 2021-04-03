INSERT INTO user_publishedstories (User_username, publishedStories_storyTitle) VALUES
('user1', 'user1story1'),
('user1', 'user1story2'),
('user1', 'user1story3'),
('user2', 'user2story1'),
('user2', 'user2story2'),
('user2', 'user2story3'),
('user3', 'user3story1'),
('user3', 'user3story2'),
('user3', 'user3story3');
INSERT INTO user_publishedsnippets (User_username, publishedSnippets_snippetId) VALUES
('user1', 11),
('user2', 12),
('user3', 13),
('user4', 14),
('user5', 15),
('user6', 16),
('user7', 17),
('user8', 18),
('user9', 19),
('user10', 20);
INSERT INTO story_storytext (Story_storyTitle, storyText_snippetId) VALUES
('user1story1', 11),
('user1story1', 12),
('user1story1', 13),
('user1story2', 11),
('user1story2', 12),
('user1story2', 13);
INSERT INTO snippet_snippetcomments (Snippet_snippetId, snippetComments_commentId) VALUES
(11, 11),
(12, 12),
(13, 13),
(14, 14);
INSERT INTO snippet_snippetstories (snippet_snippetId, snippetStories_storyTitle) VALUES
(11, 'user1story1'),
(11, 'user1story2'),
(12, 'user1story1'),
(12, 'user1story2'),
(13, 'user1story1'),
(13, 'user1story2');
INSERT INTO story_storycomments (Story_storyTitle, storyComments_commentId) VALUES
('user2story2', 15),
('user2story3', 16),
('user3story1', 17),
('user3story2', 18),
('user3story3', 19);