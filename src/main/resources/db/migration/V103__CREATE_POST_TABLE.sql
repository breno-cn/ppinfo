CREATE TABLE post (
    id BIGINT PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    content VARCHAR(4096) NOT NULL,
    created_at DATE,
    board_id BIGINT REFERENCES board(id),
    board_user_id BIGINT REFERENCES board_user(id)
);