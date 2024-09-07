# guestbook
use web_db;

-- 테이블생성
create table guestbook(
    no 			integer 	auto_increment primary key,
    name 		varchar(80) ,
    password 	varchar(20) ,
    content 	text ,
    reg_date    datetime
);

insert into guestbook
value (null, '이효리', '111', '이효리방문', now());

select * from guestbook;