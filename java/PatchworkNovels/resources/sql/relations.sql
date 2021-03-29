INSERT INTO user_publishedstories (User_userid, publishedStories_storyId) VALUES
(11, 11),
(11, 12),
(11, 13),
(12, 14),
(12, 15),
(12, 16),
(13, 17),
(13, 18),
(13, 19);
INSERT INTO user_publishedsnippets (User_userid, publishedSnippets_snippetId) VALUES
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
INSERT INTO story_storytext (Story_storyId, storyText_snippetId) VALUES
(11, 11),
(11, 12),
(11, 13),
(12, 11),
(12, 12),
(12, 13);
INSERT INTO snippet_snippetcomments (Snippet_snippetId, snippetComments_commentId) VALUES
(11, 11),
(12, 12),
(13, 13),
(14, 14);
INSERT INTO snippet_snippetstories (snippet_snippetId, snippetStories_storyId) VALUES
(11, 11),
(11, 12),
(12, 11),
(12, 12),
(13, 11),
(13, 12);
INSERT INTO story_storycomments (Story_storyId, storyComments_commentId) VALUES
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19);