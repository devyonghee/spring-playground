CREATE TABLE payment
(
    id         INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status     VARCHAR(20) NOT NULL,
    amount     INTEGER     NOT NULL,
    member_id  INTEGER     NOT NULL,
    created_at DATETIME    NOT NULL,
    updated_at DATETIME    NOT NULL
);

CREATE TABLE settlement
(
    id         INTEGER  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    amount     INTEGER  NOT NULL,
    member_id  INTEGER  NOT NULL,
    created_at DATETIME NOT NULL
);

INSERT INTO payment(status, amount, member_id, created_at, updated_at)
VALUES ('READY', 100, 1, now(), now()),
       ('READY', 200, 1, now(), now()),
       ('READY', 300, 1, now(), now()),
       ('READY', 400, 2, now(), now()),
       ('READY', 500, 2, now(), now()),
       ('READY', 600, 2, now(), now()),
       ('READY', 700, 3, now(), now()),
       ('READY', 800, 3, now(), now()),
       ('READY', 900, 3, now(), now()),
       ('READY', 100, 4, now(), now()),
       ('READY', 200, 4, now(), now()),
       ('READY', 300, 4, now(), now()),
       ('READY', 400, 5, now(), now()),
       ('READY', 500, 5, now(), now()),
       ('READY', 600, 5, now(), now()),
       ('READY', 700, 6, now(), now()),
       ('READY', 800, 6, now(), now()),
       ('READY', 900, 6, now(), now()),
       ('READY', 100, 7, now(), now()),
       ('READY', 200, 7, now(), now()),
       ('READY', 300, 7, now(), now()),
       ('READY', 400, 8, now(), now()),
       ('READY', 500, 8, now(), now()),
       ('READY', 600, 8, now(), now()),
       ('READY', 700, 9, now(), now()),
       ('READY', 800, 9, now(), now()),
       ('READY', 900, 9, now(), now()),
       ('READY', 100, 10, now(), now()),
       ('READY', 200, 10, now(), now()),
       ('READY', 300, 10, now(), now()),
       ('READY', 400, 11, now(), now()),
       ('READY', 500, 11, now(), now()),
       ('READY', 600, 11, now(), now()),
       ('READY', 700, 12, now(), now()),
       ('READY', 800, 12, now(), now()),
       ('READY', 900, 12, now(), now());




