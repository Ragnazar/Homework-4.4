CREATE TABLE student
(
    id         BIGINT NOT NULL
        PRIMARY KEY,
    age        INTEGER NOT NULL
        CHECK ( age >= 16 )
        DEFAULT 20,
    name       VARCHAR(255) NOT NULL
        UNIQUE,
    faculty_id BIGINT
        CONSTRAINT fk6geq7tnjed7u4hvgv1ac6lyh
            REFERENCES faculty
);

CREATE TABLE faculty
(
    id    BIGINT NOT NULL
        PRIMARY KEY,
    color VARCHAR(255),
    name  VARCHAR(255)
);

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);