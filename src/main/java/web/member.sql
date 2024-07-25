drop database if exists springweb;
create database springweb;
use springweb;

drop tables if exists member;
create table member(
	no bigint auto_increment ,            		-- 회원번호
	id varchar(30) not null unique ,            -- 회원 아이디
	pw varchar(30) not null ,            		-- 회원 비밀번호
	name varchar(20) not null ,            		-- 회원 이름
	email varchar(50) ,               			-- 회원 이메일
	phone varchar(13) not null unique,         	-- 회원 핸드폰 번호
	constraint member_no_pk primary key(no )    -- 회원 번호 pk
);

select * from member;

-- [2] 로그인 
select * from member where id = 'qwe' and pw ='qwe';

-- [4] 아이디 중복검사
select * from member where id = 'yang2701';
select * from member where id = 'YANG2701';
	-- 만일 대소문자를 구분하는 데이터 검색할때는 binary(필드) 사용
    -- binary(필드) : 문자가 아닌 바이트를 기준으로 비교, 검색한다.
    
select * from member where binary(id) = 'yang2701';	-- 소문자 yang
select * from member where binary(id) = 'YANG2701'; -- 대문자 YANG  

-- [5] 탈퇴
-- delete from member where no = 2;
-- delete from member where no = 2 and pw = '1234';	-- 패스워드는 중복이 가능하므로 식별 역할이 불가능하다
	-- JDBC : delete from member where no = ? and pw = ?;
    
-- [6] 수정
	-- 1. 회원번호를 식별해서 수정
-- update member set name = '유재석2' , pw = '1234' , phone = '010-1234-5678' where no = 4;
	-- 2. 회원번호와 비밀번호가 데이터와 일치했을 때 수정
-- update member set name = '유재석2' , pw = '1234' , phone = '010-1234-5678' where no = 4 and pw = '1234';
	-- JDBC : update member set name = ? , pw = ? , phone = ? where no = ? and pw = ?
    
# 1. 게시물 카테고리
drop table if exists bcategory;
create table  bcategory(
   bcno int unsigned auto_increment ,
    bcname varchar( 30 ) not null unique,
   bcdate datetime default now() not null  ,
    constraint bcategory_bcno_pk primary key ( bcno )
);
insert into bcategory( bcname ) values ( '자유' ) , ( '노하우' ) , ( '판매' ) , ( '구매') ;
select * from bcategory;

# 2. 게시물
drop table if exists board;
create table board(
   bno bigint unsigned auto_increment ,
    btitle varchar( 255 ) not null ,
    bcontent longtext ,
    bfile longtext ,
    bview int unsigned default 0 not null ,
    bdate datetime  default now() not null  ,
    no  bigint ,
    bcno int unsigned,
    constraint board_bno_pk primary key( bno ) ,
    constraint board_no_fk foreign key( no) references member( no ) on update cascade on delete cascade ,
    constraint board_bcno_fk foreign key( bcno ) references bcategory( bcno ) on update cascade on delete cascade
);
select *from board;

select * from board inner join member on board.no = member.no inner join bcategory on board.bcno = bcategory.bcno where bno = 4;

-- 글쓰기
insert into board(btitle,bcontent,no,bcno) values("테스트제목1","테스트내용1",1,1);
-- 1번 회원이 1번 카테고리에 테스트제목1 의 제목과 테스트내용1 의 내용 작성
-- JDBC : insert into board(btitle,bcontent,no,bcno) values(?,?,?,?);

insert into board(btitle,bcontent,no,bcno) values("테스트제목2","테스트내용2",1,2);
insert into board(btitle,bcontent,no,bcno) values("테스트제목3","테스트내용3",1,3);
insert into board(btitle,bcontent,no,bcno) values("테스트제목4","테스트내용4",2,4);

insert into member(id , pw , name , email , phone) values("1234" , "1234" , "유재석" , "123@123" , "010-0000-0000");
insert into member(id , pw , name , email , phone) values("4567" , "4567" , "유재석2" , "123@123" , "010-0000-1111");

select * from member;

-- 카테고리 출력
select * from bcategory;

delete from board where bno = 16;

update board set btitle = ? , bcontent = ? , bcno = ?







