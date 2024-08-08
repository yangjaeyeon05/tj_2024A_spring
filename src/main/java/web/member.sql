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

-- 게시물의 댓글
drop table if exists breply;
create table breply(
	brno bigint unsigned auto_increment, -- 댓글번호 [pk]
    brindex int , -- 댓글 인덱스(댓글 위치 분류) , 0 : 최상위 댓글 , 1이상 : PK(brno)참조하는 상위 댓글 번호 (댓글답변 , 친구추가 기능의 경우 사용됨)
	brcontent varchar(255) , -- 댓글내용
    brdate datetime default now() , -- 댓글작성일
    no bigint , -- 작성자
    bno bigint unsigned, -- 댓글 위치한 게시물번호 [fk]
    primary key(brno) , 
    foreign key(no) references member(no) on delete cascade on update cascade , 
    foreign key(bno) references board(bno) on delete cascade on update cascade
);
select * from breply;

-- 제품테이블			: 1 , 제품 1개(PK) 당 이미지 여러개(FK)
drop table if exists product;
create table product(
	pno int auto_increment , 		-- 제품번호
    ptitle varchar(100) ,			-- 제품명 
    pcontent varchar(255) ,			-- 제품설명 
    pprice int ,					-- 제품가격 
    pdate datetime default now() , 	-- 제품등록일(테이블당 권장)
    pview int default 0 ,			-- 제품조회수
    primary key(pno)				-- pk 설정
);
select * from product;

-- 제품 이미지 테이블		: N
drop table if exists productimg;
create table productimg(
	pimgno int auto_increment , 		-- 제품이미지번호
    pimgname text ,						-- 제품이미지이름
    pimgdate datetime default now() , 	-- (관례적으로 레코드 등록일은 필수)
    pno int , 
    primary key(pimgno) , 
    foreign key(pno) references product(pno)
    on update cascade on delete cascade -- 만약에 pk가 삭제되면 참조하는 fk도 같이 삭제
);
select * from productimg;
select * from product inner join productimg on product.pno = productimg.pno;

-- 게시물 개별조회
-- 3개 테이블 조인
select bc.bcno , bcname , bno , btitle , bcontent , id , bdate , bview , bfile
	from board b 
		inner join member m 
        inner join bcategory bc 
			on b.no = m.no and b.bcno = bc.bcno where bno = 4;

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

-- 게시물 전체 출력
select * from board;

-- 5. limit 연산자 이용한 레코드 제한 출력
	-- limit 개수 : 개수만큼의 레코드 조회
    -- limit 시작 레코드 , 개수 : 시작레코드(0~)부터 개수만큼의 레코드 조회
select * from board limit 0;
select * from board limit 1;
select * from board limit 1 , 2;
select * from board limit 1 , 3;
select * from board limit 0 , 3;
	# 페이징 처리 활용, 가정 : 하나의 페이지당 게시물 표시 수는 5개씩
    # 1페이지 limit 0 , 5;			1페이지일때 시작인덱스 : 0
select * from board limit 0 , 5;
    # 2페이지 limit 5 , 5;			2페이지일때 시작인덱스 : 5
select * from board limit 5 , 5;    
    # 3페이지 limit 10 , 5;			3페이지일때 시작인덱스 : 10
select * from board limit 10 , 5;        
	# 시작인덱스의 계산식 : (현재페이지번호-1) * 페이지당 게시물수
    
-- 활용 
#1페이지   
select *
	from board inner join member 
    on board.no = member.no
    limit 0 , 5;
#2페이지   
select *
	from board inner join member 
    on board.no = member.no
    limit 5 , 10;
#JDBC  
select *
	from board inner join member 
    on board.no = member.no
    limit ? , ?;
    
-- 정렬 : 작성일 순으로
select *
	from board inner join member 
    on board.no = member.no
    order by board.bno desc
    limit 0 , 5;
    
-- [1] 레코드 총 개수 세기
	-- count(*) 	: 조회 한 레코드의 총 개수 반환하는 함수 , 필드가 null 포함해서 개수 계산
    -- count(필드명)	: 조회 한 레코드의 총 개수 반환하는 함수 , 특정 필드의 null 제외한 개수 계산
# 1. 레코드 조회
select * from board;
# 2. 레코드 총 개수 조회
select count(*) from board;		-- 12 , null 포함 레코드 총 개수
# 2. 레코드 총 개수 조회
select count(bno) from board;	-- 12 , 해당 필드의 null 제외한 레코드 총 개수
select count(bfile) from board;	-- 2 , 해당 필드의 null 제외한 레코드 총 개수

-- 활용1. 전체 게시물 수
select count(*) as 총게시물수 from board;
-- 활용2. 카테고리별 게시물 수
	# 1. (자유) 1번카테고리의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 1;
	# 2. (노하우) 2번카테고리의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 2;
	# 3. (판매) 1번카테고리의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 3;
	# 4. (구매) 2번카테고리의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 4;
	# JDBC
    # select count(*) as 총게시물수 from board where bcno = ?;
-- 활용3. 전체 게시물 페이징 처리
select * from board inner join member on board.no = member.no order by board.bno desc limit 0 , 5;
	
-- 활용4. 카테고리별 게시물의 페이징 처리
	-- select 필드명 from 테이블명1 inner join 테이블명2 on 조인조건 where 일반조건 order by 정렬조건 limit 레코드제한;
select * from board inner join member 
	on board.no = member.no 
    where bcno = 1
    order by board.bno desc limit 0 , 5;
    # JDBC
    # select * from board inner join member on board.no = member.no where bcno = ? order by board.bno desc limit ? , ?;

-- 20240731 [검색/조회]
	-- 1. 전체 레코드 조회
select * from board;
	-- 2. 특정 필드값의 레코드 조회
select * from board where btitle = 'a';			# 'a'검색
	-- 3. 특정 필드값이 포함된 레코드 조회
select * from board where btitle like '%a';		# 'a'로 시작하는 값 검색
select * from board where btitle like 'a%';		# 'a'로 끝나는 값 검색
select * from board where btitle like '%a%';	# 'a'가 포함된 값 검색
	-- 활용
select * 									-- 1. 조회
	from board inner join member			-- 2. 테이블 조인
	on board.no = member.no					-- 3. 테이블 조인 조건
    where bcno = 1 and btitle like '%테%'	-- 4. 일반조건 : 1. 카테고리조건 2. 검색조건
    order by bno desc						-- 5. 정렬 조건
    limit 0 , 5;						-- 레코드제한
	-- 활용2
select count(*) as 총게시물수
	from board
    where bcno = 1 and btitle like '%테%';

select * from breply inner join member on breply.no = member.no;
