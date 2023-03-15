CREATE TABLE article
(
    slug            VARCHAR(255) NOT NULL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL UNIQUE,
    description     VARCHAR(255) NOT NULL,
    body            TEXT         NOT NULL,
    favorites_count INT          NOT NULL DEFAULT 0,
    created_at      DATETIME     NOT NULL,
    updated_at      DATETIME     NOT NULL
);

CREATE TABLE tag
(
    id           VARCHAR(36)  NOT NULL PRIMARY KEY,
    article_slug VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    unique (article_slug, name),
    FOREIGN KEY (article_slug) REFERENCES article (slug)
);

CREATE TABLE users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    bio      VARCHAR(255) NOT NULL,
    image    VARCHAR(255) NULL
);
