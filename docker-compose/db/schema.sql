CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(200),
    version integer
);