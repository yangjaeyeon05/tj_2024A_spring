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
# delete from todo where tno = 1 ;
insert into todo(content) values('밥먹기');
insert into todo(content) values('식사하기');
insert into todo(content) values('디저트');
insert into todo(content) values('과자먹기');
update todo set state= 1 where tno=1;
