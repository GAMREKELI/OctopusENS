-- CREATE TABLE friendship (
--     friend_ship_id BIGINT PRIMARY KEY,
--     user_id BIGINT NOT NULL,
--     friend_id BIGINT NOT NULL,
--     status VARCHAR(50)
-- );
CREATE TABLE IF NOT EXISTS friendship (
    friend_ship_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status VARCHAR(50)
    );

INSERT INTO friendship (friend_ship_id, user_id, friend_id, status) VALUES
     (1, 1, 2, 'YES'),
     (2, 1, 3, 'YES'),
     (3, 2, 3, 'NO'),
     (4, 4, 2, 'NO'),
     (5, 5, 4, 'NO');