CREATE TABLE IF NOT EXISTS todos
(
    id SERIAL PRIMARY KEY,
    name varchar(100),
    active boolean,
    priority varchar(200),
    version integer
);