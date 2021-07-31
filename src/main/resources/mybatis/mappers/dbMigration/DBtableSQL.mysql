-- auto-generated definition
create table users
(
    id       varchar(255) not null
        primary key,
    username varchar(255) null,
    password varchar(255) null,
    email    varchar(255) null,
    bio      text null,
    image    varchar(511) null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table follows
(
    user_id   varchar(255) not null,
    follow_id varchar(255) not null
);

create table articles
(
    articleId  varchar(255) not null primary key,
    authorId     varchar(255) not null,
    slug        varchar(255) UNIQUE,
    title       varchar(255),
    description text,
    body        text,
    createdAt    TIMESTAMP    not null,
    updatedAt    TIMESTAMP    not null
);

create table article_tag
(
    articleId varchar(255) not null,
    tagId     varchar(255) not null
);

create table tags
(
    tagId varchar(255) not null primary key,
    name   varchar(255) not null
);

create table articleFavorites
(
    userId    varchar(255) not null,
    articleId varchar(255) not null,
    primary key (userId, articleId)
);

