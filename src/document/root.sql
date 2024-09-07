# root
-- 계정 생성
create user 'web'@'%' identified by 'web';

-- 권한 설정
grant all privileges on web_db.* to 'web'@'%' ;

-- database 생성
create database web_db
    default character set utf8mb4
    collate utf8mb4_general_ci
    default encryption='n'
;

flush privileges;

# web
use web_db;

create table users(
    no 		integer auto_increment primary key,
    id 		varchar(20) unique not null,
    password 	varchar(20) not null,
    name 		varchar(20),
    gender 	varchar(20)
);