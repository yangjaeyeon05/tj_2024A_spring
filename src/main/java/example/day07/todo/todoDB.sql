drop database if exists todolist;
create database todolist;
use todolist;

drop table if exists todo;
create table todo(
	tno int not null auto_increment , 
    content varchar(20) not null, 
    state boolean not null default 0 ,  
    primary key(tno)
);
select * from todo;

insert into todo(content) values('밥먹기');