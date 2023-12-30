CREATE TABLE friendship (
    friend_ship_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status VARCHAR(50)
);