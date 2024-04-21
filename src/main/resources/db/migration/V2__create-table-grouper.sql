CREATE TABLE grouper
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    user_id    SERIAL       NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted    BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
