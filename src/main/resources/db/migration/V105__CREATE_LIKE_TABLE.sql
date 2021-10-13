CREATE TABLE post_like (
    id BIGINT PRIMARY KEY,
    board_user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL
);