CREATE TABLE board (
    id BIGINT PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL,
    description VARCHAR(512) NOT NULL,
    created_at DATE
);
