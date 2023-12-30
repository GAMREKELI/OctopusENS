CREATE TABLE users (
         user_id SERIAL PRIMARY KEY,
         login VARCHAR(50) NOT NULL,
         password VARCHAR(100) NOT NULL,
         first_name VARCHAR(50) NOT NULL,
         last_name VARCHAR(50) NOT NULL,
         email VARCHAR(100) NOT NULL,
         phone_number VARCHAR(20) NOT NULL,
         role VARCHAR(20) NOT NULL,
         CONSTRAINT unique_email UNIQUE (email)
);

INSERT INTO users (login, password, first_name, last_name, email, phone_number, role) VALUES
        ('Ivan', '$2y$10$7EvloDO2IuIeqJ82kMfKleK14iJL95wFVhvPY2rAxljpOV2Ih9sky', 'Иван', 'Тестовый', 'test@gmail.com', '+79105988089', 'USER'),
        ('Pet', '$2y$10$7EvloDO2IuIeqJ82kMfKleK14iJL95wFVhvPY2rAxljpOV2Ih9sky', 'Пётр', 'Тестовый', 'test2@gmail.com', '+79105988089', 'USER'),
        ('Vas', '$2y$10$7EvloDO2IuIeqJ82kMfKleK14iJL95wFVhvPY2rAxljpOV2Ih9sky', 'Василий', 'Тестовый', 'test3@gmail.com', '+79105988089', 'USER'),
        ('Nek', '$2y$10$7EvloDO2IuIeqJ82kMfKleK14iJL95wFVhvPY2rAxljpOV2Ih9sky', 'Никита', 'Тестовый', 'test4@gmail.com', '+79105988089', 'USER'),
        ('Bor', '$2y$10$7EvloDO2IuIeqJ82kMfKleK14iJL95wFVhvPY2rAxljpOV2Ih9sky', 'Борис', 'Тестовый', 'test5@gmail.com', '+79105988089', 'USER');