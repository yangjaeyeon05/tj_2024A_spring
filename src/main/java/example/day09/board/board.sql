drop database if exists board;
create database board;

use board;

drop table if exists boardlist;

create table boardlist(
	bno int auto_increment , 
    btitle varchar(20) , 
    bcontent text , 
    bdate datetime default now() , 
    bview int default 0,
    bpwd varchar(20) , 
    primary key(bno)
);

insert into boardlist(btitle , bcontent , bpwd) values ('제목1' , '내용1' , '1234' );

select * from boardlist;
