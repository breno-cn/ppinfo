CREATE TABLE board_follow (
    id BIGINT PRIMARY KEY,
    board_user_id BIGINT NOT NULL,
    board_id BIGINT NOT NULL
);