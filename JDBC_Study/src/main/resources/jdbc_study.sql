
create database jdbcStudy character set utf8 collate utf8_general_ci;
use jdbcStudy;

create table users(
    id int primary key,
    name varchar(40),
    password varchar(40),
    email varchar(60),
    birthday date
);

insert into users(id,name,password,email,birthday) values(1,'zhansan','123456','zs@sina.com','1980-12-04');
insert into users(id,name,password,email,birthday) values(2,'lisi','123456','lisi@sina.com','1981-12-04');
insert into users(id,name,password,email,birthday) values(3,'wangwu','123456','wangwu@sina.com','1979-12-04');

create Table testbatch(
	id int primary key,
	name varchar(20)
);



create table testclob(
    id int primary key auto_increment,
    resume text
 );

 create table testblob (
      id int primary key auto_increment,
      image longblob
);

create table test1(
	id int primary key auto_increment,
	name varchar(20)
);


/*创建账户表*/
create table account(
    id int primary key auto_increment,
    name varchar(40),
    money float
);

/*插入测试数据*/
insert into account(name,money) values('A',1000);
insert into account(name,money) values('B',1000);
insert into account(name,money) values('C',1000);