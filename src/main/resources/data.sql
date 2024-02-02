INSERT INTO tags (tag_id, tag_value) VALUES (1, 'spring');
INSERT INTO tags (tag_id, tag_value) VALUES (2, 'java');
INSERT INTO tags (tag_id, tag_value) VALUES (3, 'core');
INSERT INTO tags (tag_id, tag_value) VALUES (4, 'hibernate');
INSERT INTO tags (tag_id, tag_value) VALUES (5, 'sql');
INSERT INTO tags (tag_id, tag_value) VALUES (6, 'data');
INSERT INTO tags (tag_id, tag_value) VALUES (7, 'kafka');
INSERT INTO tags (tag_id, tag_value) VALUES (8, 'security');
INSERT INTO tags (tag_id, tag_value) VALUES (9, 'bean');

INSERT INTO posts (post_id, post_title, post_content) VALUES ( 1, 'java talk', 'we going to talk about java core' );
INSERT INTO posts (post_id, post_title, post_content) VALUES ( 2, 'spring security', 'to configure spring security just use java and bean' );
INSERT INTO posts (post_id, post_title, post_content) VALUES ( 3, 'spring sql', 'do you know something about spring sql' );

INSERT INTO posts_tags (post_id, tag_id) VALUES ( 1, 2 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 1, 3 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 2, 1 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 2, 8 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 2, 2 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 2, 9 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 3, 1 );
INSERT INTO posts_tags (post_id, tag_id) VALUES ( 3, 5 );