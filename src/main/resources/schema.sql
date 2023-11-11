CREATE TABLE IF NOT EXISTS to_do
(
    id          VARCHAR(36)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    created     TIMESTAMP    NOT NULL,
    modified    TIMESTAMP    NOT NULL,
    completed   BOOLEAN      NOT NULL,
    PRIMARY KEY (id)
);