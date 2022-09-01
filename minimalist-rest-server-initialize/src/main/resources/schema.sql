DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name   VARCHAR,
    last_name    VARCHAR,
    created_time TIMESTAMPTZ,
    updated_time TIMESTAMPTZ
);
DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    author_id    BIGINT,
    name    VARCHAR,
    created_time TIMESTAMPTZ,
    updated_time TIMESTAMPTZ
);
