CREATE TABLE task
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    grouper_id    SERIAL       NOT NULL,
    expiration_at TIMESTAMP    NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    updated_at    TIMESTAMP    NOT NULL,
    deleted       BOOLEAN,
    FOREIGN KEY (grouper_id) REFERENCES grouper (id)
);
