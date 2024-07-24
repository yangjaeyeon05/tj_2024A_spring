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
delete from member where no = 2;
delete from member where no = 2 and pw = '1234';	-- 패스워드는 중복이 가능하므로 식별 역할이 불가능하다
	-- JDBC : delete from member where no = ? and pw = ?;
    
-- [6] 수정
	-- 1. 회원번호를 식별해서 수정
update member set name = '유재석2' , pw = '1234' , phone = '010-1234-5678' where no = 4;
	-- 2. 회원번호와 비밀번호가 데이터와 일치했을 때 수정
update member set name = '유재석2' , pw = '1234' , phone = '010-1234-5678' where no = 4 and pw = '1234';
	-- JDBC : update member set name = ? , pw = ? , phone = ? where no = ? and pw = ?






