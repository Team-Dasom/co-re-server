DROP TABLE IF EXISTS `MEMBER`;

CREATE TABLE `MEMBER`
(
    `MEMBER_ID`         bigint auto_increment primary key,
    `MEMBER_NAME`       varchar(50)  NOT NULL,
    `PROFILE_IMAGE_URL` varchar(200) NOT NULL,
    `CAREER`            int NULL,
    `CREATED_AT`     datetime    NOT NULL,
    `MODIFIED_AT`    datetime    NOT NULL
);