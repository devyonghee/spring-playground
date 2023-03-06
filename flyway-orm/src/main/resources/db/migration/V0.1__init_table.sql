CREATE TABLE article
(
    slug        VARCHAR(255) NOT NULL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    body        TEXT         NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL
);

CREATE TABLE tag
(
    article_slug VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    PRIMARY KEY (article_slug, name),
    FOREIGN KEY (article_slug) REFERENCES article (slug)
);

CREATE TABLE users
(
    username  VARCHAR(255) NOT NULL PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    bio       VARCHAR(255) NOT NULL,
    image     VARCHAR(255) NULL,
    following BIT          NOT NULL DEFAULT FALSE
);
