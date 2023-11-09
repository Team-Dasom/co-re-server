DROP TABLE IF EXISTS `MEMBER`;
DROP TABLE IF EXISTS `FAVORITE`;

CREATE TABLE `MEMBER`
(
    `MEMBER_ID`         bigint auto_increment primary key,
    `NICKNAME`          varchar(50)  NOT NULL,
    `PROFILE_IMAGE_URL` varchar(200) NOT NULL,
    `MEMBER_ROLE`       varchar(30)  NOT NULL,
    `CAREER`            int NULL,
    `SOCIAL_ID`         varchar(300) NOT NULL,
    `SOCIAL_TYPE`       varchar(30)  NOT NULL,
    `CREATED_AT`        datetime     NOT NULL,
    `MODIFIED_AT`       datetime     NOT NULL
);

CREATE TABLE `FAVORITE`
(
    `FAVORITE_ID`   bigint auto_increment primary key,
    `MEMBER_ID`     bigint   NOT NULL,
    `FUNCTION_TYPE`  varchar(30)   NOT NULL,
    `QUESTION`      text     NOT NULL,
    `ANSWER`        text     NOT NULL,
    `QUESTIONED_AT` datetime NOT NULL,
    `CREATED_AT`    datetime NOT NULL,
    `MODIFIED_AT`   datetime NOT NULL
);
