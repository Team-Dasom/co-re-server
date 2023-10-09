DROP TABLE IF EXISTS `MEMBER`;

CREATE TABLE `MEMBER`
(
    `MEMBER_ID`         bigint auto_increment primary key,
    `NICKNAME`       varchar(50)  NOT NULL,
    `PROFILE_IMAGE_URL` varchar(200) NOT NULL,
    `MEMBER_ROLE` varchar(30) NOT NULL,
    `CAREER`            int NULL,
    `SOCIAL_ID` varchar(300) NOT NULL,
    `SOCIAL_TYPE` varchar(30) NOT NULL,
    `CREATED_AT`     datetime    NOT NULL,
    `MODIFIED_AT`    datetime    NOT NULL
);