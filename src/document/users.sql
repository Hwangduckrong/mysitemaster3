# web
use web_db;

-- 테이블생성
create table users(
    no 			integer 	auto_increment primary key,
    id 			varchar(20) unique not null,
    password 	varchar(20) not null,
    name 		varchar(20),
    gender 		varchar(10)
);

insert into users
value (null, 'aaa', 'aaa', '정우성', 'male');

select * from users;