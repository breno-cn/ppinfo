CREATE TABLE comment (
    id BIGINT PRIMARY KEY,
    content VARCHAR(4096) NOT NULL,
    created_at DATE,
    post_id BIGINT REFERENCES post(id),
    board_user_id BIGINT REFERENCES board_user(id)
);