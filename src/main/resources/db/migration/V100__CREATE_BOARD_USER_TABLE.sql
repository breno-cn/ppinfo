CREATE TABLE board_user (
    id BIGINT PRIMARY KEY,
    username VARCHAR(64) UNIQUE,
    email VARCHAR(64) UNIQUE,
    password TEXT UNIQUE,
    firstname VARCHAR(32),
    lastname VARCHAR(64),
    created_at DATE,
    is_banned BOOLEAN
);